<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="<%=request.getContextPath()%>/" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>注册管理员</title>

    <!-- Custom fonts for this template-->
    <link href="<%=request.getContextPath()%>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/css/sb-admin-2.min.css" rel="stylesheet">

	<script type="text/javascript">
	function imgPreview(fileDom) {
		
		//1.先判断当前浏览器是否支持reader这个特性
		if(window.FileReader){
			var fileReader = new FileReader();
		}else{
			alert("您的浏览器不支持该操作");
			return false;
		}
		
		//2.通过js的集合选择器获取到文件域
		var file = fileDom.files[0];
		
		//3.设置一个校验的正则表达式
		var imageType = /^image\//;
		if(!imageType.test(file.type)){
			 alert("请选择图片")
	         document.getElementById("spid").innerHTML="<input type=\"file\" name=\"image\" onchange=\"imgPreview(this)\" id=\"imgFile\" accept=\"image/gif,image/jpeg,image/jpg,image/png,image/svg\" />";
	         return false;	
		}
		
		 //读取完成
	    fileReader.onload = function() 
	    {
	        //获取图片dom
	        var img = document.getElementById("preview");
	        //图片路径设置为读取的图片
	        img.src = this.result;
	    };
	    fileReader.readAsDataURL(file);//将文件域当中的标签显示在浏览器上
	}
	
	 function checkForm()
	 {
		 
		 var name=document.getElementById("name").value;
		 var pass=document.getElementById("pass").value;
		 var CFpass=document.getElementById("CFpass").value;
		 var imgFile=document.getElementById("imgFile").value;
		 
		 if(name=="")
		 {
			 alert("请先输入管理员名字");
			 return false;
		 }
		 
		 if(pass=="")
		 {
			 alert("请先输入管理员密码");
			 return false;
		 }
		 
		 if(CFpass=="")
		 {
			 alert("请先输入重复密码");
			 return false;
		 }
		 
		 if(CFpass != pass)
		 {
			 alert("重复密码与密码不一致");
			 return false;
		 }
		 
		 
		 if(imgFile=="")
		 {
			 alert("请先选择本次上传的图片");
			 return false;
		 }
		 
	    //alert("2.图片大小的校验");
	    var	imgFileElement=document.getElementById("imgFile");
	    var fileData=imgFileElement.files[0];
	    var size=fileData.size; 
	    if(size>1024*1024)
		{
			alert("您本次上传的图片超过1MB了,请选择一个小点的再上传.....");
			return false;
		}
	 }
		

</script>


</head>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image">
                    <img id="preview"   style="width:453px;height:100%"  src="<%=request.getContextPath()%>/img/img-home-01.jpg"  />
                    </div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">管理员注册</h1>
                            </div>
                            <form class="user" name="form1" method="post"action="<%=request.getContextPath()%>/manage/register.do?method=add"onsubmit="return checkForm()" enctype="multipart/form-data">
                                <div class="form-group row">
                                    <div class="col-sm-12 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" id="name" name="admin_name"  placeholder="管理员姓名">
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="password" class="form-control form-control-user"
                                            id="pass"  name="admin_pass"  placeholder="密码">
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control form-control-user"
                                            id="CFpass"  placeholder="重复密码">
                                    </div>
                                </div>
                                
                                <br>
                                
                                 <div class="form-group">
                               		 添加管理员头像：
                                <input type="file"   name="image"  onchange="imgPreview(this)" id="imgFile"/>
                                </div>
                                
                                 <br>
                                 
                                <input  style="float: right" type="submit" class="btn btn-primary btn-user btn-block" value="点击注册"/>
                                <hr>
                                
                               
                                
                            </form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="<%=request.getContextPath() %>/jsp/login/userlogin.jsp">返回用户登录</a>
                            </div>
                            <div class="text-center">
                                <a class="small" href="<%=request.getContextPath() %>/jsp/login/adminlogin.jsp">已经有账号了? 登入!</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="<%=request.getContextPath()%>/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath()%>/js/sb-admin-2.min.js"></script>

</body>

</html>