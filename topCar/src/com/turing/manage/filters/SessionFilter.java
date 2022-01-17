package com.turing.manage.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = "/manage/*")
public class SessionFilter implements Filter {

	@Override
	public void destroy() {

		System.out.println("过滤器的销毁");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 初始化request response
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse reponse = (HttpServletResponse) res;
		String path = request.getServletPath();
		System.out.println(path);
		if ("/manage/login.jsp".equals(path) | "/manage/manager.do".equals(path) | "/manage/login.do".equals(path)) {
			System.out.println("此为特权页面放行");
			chain.doFilter(request, reponse);
			return;// 终止本次程序
		}
	}

	// 进行session过滤
	// HttpSession session = request.getSession();
	// if (session.getAttribute("user") == null) {
	// System.out.println("您还没有登录");
	// String errorMessage = "您还没有登录，不能访问本次页面<br/>请登录后再试";
	// request.setAttribute("errorMsg", errorMessage);
	// request.getRequestDispatcher("/manage/error.jsp").forward(request,
	// reponse);
	// // 终止程序
	// return;
	// } else {
	// System.out.println("过滤通过");
	// chain.doFilter(request, reponse);
	// // 终止本次程序
	// }
	//
	// }
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("过滤器的初始化");
	}
}
