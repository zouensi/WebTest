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
 * 编码转换过滤器
 * @author DELL
 *
 */
public class MyFilter implements Filter {

    public MyFilter() {
    	
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//由于ServletRequest没有getMethod方法所以要强转为request
		HttpServletRequest req = (HttpServletRequest) request;
		String method = req.getMethod();
		System.out.println(method);
		if("post".equalsIgnoreCase(method)) {//如果是post方法
			req.setCharacterEncoding("utf-8");
			chain.doFilter(req, response);
		}else if("get".equalsIgnoreCase(method)) {//如果是get请求,用动态代理进行转码
			Class<?>[] interfaces = req.getClass().getInterfaces();
			//获取通过代理得到的新的request对象
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
				String info = (String)method.invoke(obj, args);//获取参数信息
				if(info!=null) {
					return new String(info.getBytes("iso8859-1"),"utf-8");
				}
				
			}else if("getParameterMap".equals(method.getName())) {
				//获取对应的map
				@SuppressWarnings("unchecked")
				Map<String, String[]> parameterMap = (Map<String, String[]>) method.invoke(obj, args);
				//遍历map
				for (Map.Entry<String, String[]> entry: parameterMap.entrySet()) {
					String[] value = entry.getValue();
					//只考虑有一个值，也就是没必要考虑多选框
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
