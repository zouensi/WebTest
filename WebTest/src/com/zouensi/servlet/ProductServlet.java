package com.zouensi.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.zouensi.domain.Product;
import com.zouensi.service.ProductService;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService service = new ProductService();
    public ProductServlet() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("myMethod");
		System.out.println(method);
		if("findAll".equals(method)) {
			findAll(request,response);
		}else if("insert".equals(method)) {
			insert(request,response);
		}else if("delete".equals(method)) {
			delete(request,response);
		}else if("find".equals(method)) {
			find(request,response);
		}else if("update".equals(method)) {
			update(request,response);
		}else if("deleteAll".equals(method)) {
			deleteAll(request,response);
		}else if("findByCondition".equals(method)) {
			findByCondition(request,response);
		}
		else {
			findAll(request,response);
		}
	}
	/**
	 * ����������ѯ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findByCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String findByShop_price = request.getParameter("findByShop_price");//���ռ۸��ѯ
		String findByPdesc = request.getParameter("findByPdesc");//�����������в���
		try {
			List<Product> listProduct = service.findByCondition(findByShop_price,findByPdesc);
			request.setAttribute("product", listProduct);
			request.getRequestDispatcher("/jsp/show.jsp").forward(request, response);
		} catch (SQLException e) {
			errorRequest(request,response,"��,��ѯʧ��");
			e.printStackTrace();
		}
		
	}

	/**
	 * ɾ��������Ϣ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void deleteAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] pids = request.getParameterValues("checkbox");
		if(pids!=null) {
			try {
				service.deleteAll(pids);
				//����ɹ����²�����Ϣ
				response.sendRedirect(request.getContextPath()+"/ProductServlet?myMethod=findAll");
			} catch (Exception e) {
				errorRequest(request, response,"��,ɾ��ʧ��!");
				e.printStackTrace();
			}
		}
	}


	/**
	 * ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Product product = new Product();
		try {
			BeanUtils.populate(product, parameterMap);
			boolean b = service.update(product);
			if(b) {
				//����ɹ����²�����Ϣ
				response.sendRedirect(request.getContextPath()+"/ProductServlet?myMethod=findAll");
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			errorRequest(request, response,"��,����ʧ��!");
			e.printStackTrace();
		}
	}

	/**
	 * ��ѯ��������
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		try {
			Product product = service.find(pid);
			request.setAttribute("product", product);
			request.getRequestDispatcher("/jsp/update.jsp").forward(request, response);
		} catch (SQLException e) {
			errorRequest(request, response,"��,��ѯʧ��!");
			e.printStackTrace();
		}
	}

	/**
	 * ��ѯ������Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void findAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Product> listProduct = service.findAll();
			request.setAttribute("product", listProduct);
			request.getRequestDispatcher("/jsp/show.jsp").forward(request, response);
		} catch (SQLException e) {
			errorRequest(request, response,"��,��ѯ��Ϣʧ��!");
			e.printStackTrace();
		}
		
	}
	/**
	 * ������Ϣ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Product product = new Product();
		try {
			BeanUtils.populate(product, parameterMap);
			String pid = UUID.randomUUID().toString().replace("-", "");
			String image = "products/1/c_0001.jpg";
			product.setPid(pid);
			product.setPimage(image);
			boolean b = service.insert(product);
			if(b) {
				//����ɹ����²�����Ϣ
				response.sendRedirect(request.getContextPath()+"/ProductServlet?myMethod=findAll");
			}else {
				throw new RuntimeException("����ʧ��");
			}
			
		} catch (IllegalAccessException | InvocationTargetException | SQLException e) {
			errorRequest(request, response,"��,�����Ϣʧ��!");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ɾ����Ϣ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid  = request.getParameter("pid");
		System.out.println(pid);
		try {
			boolean b = service.delete(pid);
			if(b) {
				findAll(request,response);
			}
		} catch (SQLException e) {
			errorRequest(request, response,"��,ɾ����Ϣʧ��!");
			e.printStackTrace();
		}
		
	}
	/**
	 * ������Ϣ��ʾ����ת
	 * @param request
	 * @param response
	 * @param msg
	 * @throws ServletException
	 * @throws IOException
	 */
	private void errorRequest(HttpServletRequest request,
			HttpServletResponse response,String msg) throws ServletException, IOException {
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
	}
	

}
