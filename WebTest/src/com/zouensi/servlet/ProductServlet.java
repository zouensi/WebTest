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
	 * 按照条件查询
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findByCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String findByShop_price = request.getParameter("findByShop_price");//按照价格查询
		String findByPdesc = request.getParameter("findByPdesc");//按照描述进行查找
		try {
			List<Product> listProduct = service.findByCondition(findByShop_price,findByPdesc);
			request.setAttribute("product", listProduct);
			request.getRequestDispatcher("/jsp/show.jsp").forward(request, response);
		} catch (SQLException e) {
			errorRequest(request,response,"亲,查询失败");
			e.printStackTrace();
		}
		
	}

	/**
	 * 删除所有信息
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
				//插入成功重新查新信息
				response.sendRedirect(request.getContextPath()+"/ProductServlet?myMethod=findAll");
			} catch (Exception e) {
				errorRequest(request, response,"亲,删除失败!");
				e.printStackTrace();
			}
		}
	}


	/**
	 * 更新
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
				//插入成功重新查新信息
				response.sendRedirect(request.getContextPath()+"/ProductServlet?myMethod=findAll");
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			errorRequest(request, response,"亲,更新失败!");
			e.printStackTrace();
		}
	}

	/**
	 * 查询单条数据
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
			errorRequest(request, response,"亲,查询失败!");
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有信息
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
			errorRequest(request, response,"亲,查询信息失败!");
			e.printStackTrace();
		}
		
	}
	/**
	 * 插入信息
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
				//插入成功重新查新信息
				response.sendRedirect(request.getContextPath()+"/ProductServlet?myMethod=findAll");
			}else {
				throw new RuntimeException("插入失败");
			}
			
		} catch (IllegalAccessException | InvocationTargetException | SQLException e) {
			errorRequest(request, response,"亲,添加信息失败!");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 删除信息
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
			errorRequest(request, response,"亲,删除信息失败!");
			e.printStackTrace();
		}
		
	}
	/**
	 * 错误信息提示和跳转
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
