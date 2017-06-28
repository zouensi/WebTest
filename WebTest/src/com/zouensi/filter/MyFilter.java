package com.zouensi.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * ����ת��������
 * @author DELL
 *
 */
public class MyFilter implements Filter {

    public MyFilter() {
    	
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//����ServletRequestû��getMethod��������ҪǿתΪrequest
		HttpServletRequest req = (HttpServletRequest) request;
		String method = req.getMethod();
		System.out.println(method);
		if("post".equalsIgnoreCase(method)) {//�����post����
			req.setCharacterEncoding("utf-8");
			chain.doFilter(req, response);
		}else if("get".equalsIgnoreCase(method)) {//�����get����,�ö�̬�������ת��
			Class<?>[] interfaces = req.getClass().getInterfaces();
			//��ȡͨ������õ����µ�request����
			HttpServletRequest requestProxy = (HttpServletRequest) Proxy.newProxyInstance(MyFilter.class.getClassLoader()
																	, interfaces, new MyInvocationHandler(req));
			chain.doFilter(requestProxy, response);
		}else {
			chain.doFilter(request, response);
		}
		
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	class MyInvocationHandler implements InvocationHandler {
		private Object obj = null;
		public  MyInvocationHandler(Object obj) {
			this.obj = obj;
		}
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			if("getParameter".equals(method.getName())) {
				String info = (String)method.invoke(obj, args);//��ȡ������Ϣ
				if(info!=null) {
					return new String(info.getBytes("iso8859-1"),"utf-8");
				}
				
			}else if("getParameterMap".equals(method.getName())) {
				//��ȡ��Ӧ��map
				@SuppressWarnings("unchecked")
				Map<String, String[]> parameterMap = (Map<String, String[]>) method.invoke(obj, args);
				//����map
				for (Map.Entry<String, String[]> entry: parameterMap.entrySet()) {
					String[] value = entry.getValue();
					//ֻ������һ��ֵ��Ҳ����û��Ҫ���Ƕ�ѡ��
					if(value!=null&&value.length==1) {
						value[0] = new String(value[0].getBytes("iso8859-1"),"utf-8");
					}
				}
				return parameterMap;
			}
			return method.invoke(obj, args);
		}
		
	}

}
