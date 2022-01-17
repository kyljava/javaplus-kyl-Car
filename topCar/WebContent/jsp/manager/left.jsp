<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Upright</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/fontawesome/css/all.min.css">  <!-- https://fontawesome.com/-->  
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/magnific-popup.css">       <!-- https://dimsemenov.com/plugins/magnific-popup/ -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">        <!-- https://getbootstrap.com/ -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/slick/slick.min.css">          <!-- https://kenwheeler.github.io/slick/ -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/slick/slick-theme.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/templatemo-upright.css">
</head>
<body>    
    <div class="container-fluid">
        <div class="row">
            <div id="tm-sidebar" class="tm-sidebar"> 
                <nav class="tm-nav">
                    <button class="navbar-toggler" type="button" aria-label="Toggle navigation">
                        <i class="fas fa-bars"></i>
                    </button>
                    <div>
                        <div class="tm-brand-box" style="margin-bottom:70px;">
                            <h1 class="tm-brand">顶季淘车</h1>
                        </div>                
                        <ul class="list-group">
                            <li class="nav-item">                                
                                <a href="<%=request.getContextPath() %>/jsp/manager/transaction/list.jsp" class="nav-link">
                                    <i class="fas fa-home nav-icon"></i>
                                  交易记录
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<%=request.getContextPath() %>/jsp/manager/buycar/list.jsp" class="nav-link">
                                    <i class="fas fa-images nav-icon"></i>
                                   供应商信息
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<%=request.getContextPath() %>/jsp/manager/car/list.jsp" class="nav-link">
                                    <i class="fas fa-user-friends nav-icon"></i>
                                车辆基本信息
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<%=request.getContextPath() %>/jsp/manager/user/list.jsp" class="nav-link">
                                    <i class="fas fa-envelope nav-icon"></i>
                                 用户基本信息
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="<%=request.getContextPath() %>/jsp/manager/aftersale/list.jsp" class="nav-link" target="_parent" rel="sponsored">
                                    <i class="fas fa-external-link-alt nav-icon"></i>
                                   用户售后记录
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>          <!-- https://jquery.com/ -->
    <script src="<%=request.getContextPath()%>/js/jquery.singlePageNav.min.js"></script>  <!-- https://github.com/ChrisWojcik/single-page-nav -->
    <script src="<%=request.getContextPath()%>/js/parallax/parallax.min.js"></script>     <!-- https://pixelcog.github.io/parallax.js/ -->
    <script src="<%=request.getContextPath()%>/js/imagesloaded.pkgd.min.js"></script>     <!-- https://imagesloaded.desandro.com/ -->
    <script src="<%=request.getContextPath()%>/js/isotope.pkgd.min.js"></script>          <!-- https://isotope.metafizzy.co/ -->
    <script src="<%=request.getContextPath()%>/js/jquery.magnific-popup.min.js"></script> <!-- https://dimsemenov.com/plugins/magnific-popup/ -->
    <script src="<%=request.getContextPath()%>/slick/slick.min.js"></script>              <!-- https://kenwheeler.github.io/slick/ -->
    <script src="<%=request.getContextPath()%>/js/templatemo-script.js"></script>
</body>
</html> 