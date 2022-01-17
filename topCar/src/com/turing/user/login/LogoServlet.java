package com.turing.user.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/user/login.do",asyncSupported=true)
public class LogoServlet  extends HttpServlet{

	LogoService loginservice = new LogoService();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String method = request.getParameter("method");
			if ("login".equals(method)) {
				this.login(request,response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.getRequestDispatcher("/404.jsp").forward(request, response);
		}
	}

	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException, SQLException {
		// 1.接值
		String name = request.getParameter("user");
		String pass = request.getParameter("pass");
		// 2.调用M层中的方法查询该条数据
		Map<String, Object> map = loginservice.queryByNameAndPass(name, pass);
//		session = request.getSession();
//		String sessionId=session.getId();
		if (map != null) {
			// 3.存值
			request.setAttribute("map", map);
//			session.setAttribute("user", map);
//			response.getWriter().print("session创建成功，session的id是："+sessionId);
			//4.转向
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
//			request.setAttribute("errorMsg", "用户名或者密码有误，请重新输入");
			//错误页面
//			request.getRequestDispatcher("/error-404.jsp").forward(request, response);
		}

	}
	
}
