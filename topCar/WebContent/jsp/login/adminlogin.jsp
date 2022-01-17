<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="<%=request.getContextPath()%>/" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Login</title>

    <!-- Custom fonts for this template-->
    <link href="<%=request.getContextPath()%>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/css/sb-admin-2.min.css" rel="stylesheet">
    
    <script type="text/javascript">
	function check(form) {
		if (form.user.value == "") {
			alert("请输入用户账号!");
			form.name.focus();//让管理员姓名这个文本框获取焦点
			return false;//程序到此结束
		}
		if (form.pass.value == "") {
			alert("请输入密码!");
			form.pass.focus();
			return false;
		}
	}
</script>

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">管理员登录</h1>
                                    </div>
                                    <form name="managerForm" class="user" action="<%=request.getContextPath()%>/manage/adminlogin.do?method=login"
                                    	method="post" >
                                        
                                        <div class="form-group">
                                            <input name="adminname"  type="text" class="form-control form-control-user"
                                               >
                                        </div>
                                        
                                        <div class="form-group">
                                            <input name="adminpass" type="password" class="form-control form-control-user"
                                                id="exampleInputPassword" placeholder="密码">
                                        </div>
                                        
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" class="custom-control-input" id="customCheck">
                                                <label class="custom-control-label" for="customCheck">记住密码</label>
                                            </div>
                                        </div>
                                        
                                     <input type="submit" value="登录" class="btn btn-primary btn-user btn-block">
                                        <hr>
                                        <a href="index.jsp" class="btn btn-google btn-user btn-block">
                                            <i class="fab fa-google fa-fw"></i> 使用谷歌登录
                                        </a>
                                        <a href="index.jsp" class="btn btn-facebook btn-user btn-block">
                                            <i class="fab fa-facebook-f fa-fw"></i> 使用脸书登录
                                        </a>
                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="<%=request.getContextPath() %>/jsp/login/userlogin.jsp">返回用户登录</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="<%=request.getContextPath() %>/jsp/Register/adminRegister.jsp">创建一个账户!</a>
                                    </div>
                                </div>
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