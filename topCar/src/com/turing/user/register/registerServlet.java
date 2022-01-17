package com.turing.user.register;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/Register/register.do",asyncSupported=true)

public class registerServlet extends HttpServlet{
	registerService registerService = new registerService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		  response.setCharacterEncoding("utf-8");
		String method=request.getParameter("method");
		
		if (method.equals("add"))
			{
			  try {
				this.add(request,response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				request.getRequestDispatcher("/404.jsp").forward(request, response);
			}	
			}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException, SQLException {
		//1.接值
		 
		String  user_name=request.getParameter("user_name");
		String  user_password=request.getParameter("user_password");
		String  user_sex=request.getParameter("user_sex");
		String  user_age=request.getParameter("user_age");
		String  user_adress=request.getParameter("user_adress");
		System.out.println(user_name);
		//2.调用M层中的方法实现保存
		registerService.save(user_name,user_password,user_sex,user_age,user_adress);
		
		//3.重定向
		request.getRequestDispatcher("/jsp/login/userlogin.jsp").forward(request, response);
		
	}

		
}
