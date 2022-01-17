package com.turing.manage.login;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/manage/adminlogin.do", asyncSupported = true)
public class LogoServlet  extends HttpServlet{
	LogoService service = new LogoService();
	HttpSession session;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String method = request.getParameter("method");
			System.out.println("进入之前");
			if ("login".equals(method)) {
				System.out.println("进入之后");
				quearBylogo(request, response);
			} else if ("logout".equals(method)) {
				this.logout(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("1.打印出异常信息 " + e.getMessage());
			System.out.println("2.打印出异常信息的处理地址" + e.getStackTrace());
			System.out.println("转到公共页面");
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("/404.jsp").forward(request, response);
		}
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().print("要销毁的session对象：" + session.getId());
		session.invalidate();
		request.getRequestDispatcher("/manage/login.jsp").forward(request, response);
		System.out.println("session对象已销毁");

	}

	private void quearBylogo(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		// 传值
		String name = request.getParameter("adminname");
		String pass = request.getParameter("adminpass");
		// map
		Map<String, Object> map = service.quearLogo(name, pass);
		System.out.println("map="+map);
		// 创建一个session
					session = request.getSession();
					String sessionId = session.getId();
//		转向
		if (map != null) {
			request.setAttribute("map", map);
			System.out.println("map创建成功");
			
			session.setAttribute("user", map);
			
			// 新建一个页面输出session信息
//			response.getWriter().print("session创建成功，session的id" + session);
//			queryList(request, response);
//			request.getRequestDispatcher("/manage/manager/list.jsp").forward(request, response);
//			response.sendRedirect(request.getContextPath() + "/manage/manager.do?method=query");
			request.getRequestDispatcher("/jsp/manager/user/list.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "用户名或密码错误,请重新输入");
			request.getRequestDispatcher("/manage/error.jsp").forward(request, response);
		}
	}
}
