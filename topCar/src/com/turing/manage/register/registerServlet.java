package com.turing.manage.register;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/manage/register.do", asyncSupported = true)
public class registerServlet extends HttpServlet {
	
	registerService service = new registerService();
	String fileSaveName = null; //最终保存的名字

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String method=request.getParameter("method");
			System.out.println("method:"+method);
			if("add".equals(method)){
				this.add(request,response);
			}
			
		} catch ( FileUploadException | ClassNotFoundException | SQLException e) {
			System.out.println("1.打印错误"+e.getMessage());
			System.out.println("2.内存地址位置"+e.getStackTrace());
			System.out.println("3.转向错误页面");
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("/404.jsp").forward(request, response);
			}
	
	}

	/**
	 * @desc  注册管理员页面
	 * @param request
	 * @param response
	 * @throws FileUploadException 
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException, ClassNotFoundException, SQLException, ServletException {
		//0.设置类型和编码
		response.setContentType("text/html;charset=utf-8");
		
		//1.保存贼服务器硬盘的路径
		String path = this.getServletContext().getRealPath("/") + "WEB-INF/image";
		System.out.println(path);
		
		//2.创建服务器硬盘保存的路径
		File file =new File(path);
		if(!file.exists()){
			file.mkdir();
		}
		
		//3.利用commons-fileupload.jar实现上传
		//初始化其核心端
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//4.加载解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//5.设置解析器相关参数
		upload.setHeaderEncoding("utf-8");
		upload.setSizeMax(1024*1024);
		
		//6.解析request对象
		List<FileItem> formFileItemList= upload.parseRequest(request);
		
		//7.上传
		if ( formFileItemList.size()>0 && formFileItemList != null){
			for(FileItem everyFileItem : formFileItemList){
				if(!everyFileItem.isFormField()){
					String fileName = everyFileItem.getName();
					System.out.println("当前上传的文件的名字是："+fileName);
					String prefix = fileName.substring(fileName.lastIndexOf(".")+1);
					System.out.println("本次上传的后缀为："+prefix);
					fileSaveName = UUID.randomUUID().toString()+"."+prefix;
					System.out.println("保存的名字："+fileSaveName);
					
					//上传
					FileUtils.copyInputStreamToFile(everyFileItem.getInputStream(), new File(path+"/"+fileSaveName));
					System.out.println(path+"/"+fileSaveName);
				}
			}
		}
		
//		8.保存数据
		String virtualPath = fileSaveName;
		String admin_name = formFileItemList.get(0).getString("utf-8");
		System.out.println("admin_name:"+admin_name);
		String admin_pass = formFileItemList.get(1).getString("utf-8");
		System.out.println("admin_pass:"+admin_pass);
		

		// 2.调用StudentService中的方法调用sql语句实现将数据保存到mysql数据库当中
		service.save(admin_name, admin_pass,virtualPath);

//3.
		request.getRequestDispatcher("/jsp/login/adminlogin.jsp").forward(request, response);
		
	}
	
}
