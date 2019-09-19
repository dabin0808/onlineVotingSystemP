<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在线投票系统</title>
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/index.css" />
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function () {
            if (${loginUser==null}) {
                $("#login_out").show();
                $("#login_in").hide();
            } else {
                $("#login_out").hide();
                $("#login_in").show();
            }
        });
    </script>
</head>
<body>
    <div id="header" class="header">
        <div class="container">
            <div class="header-logo">OnlineVotingSystem</div>
            <div class="header-options">
                <ul>
                    <li class="options-active"><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
                    <%--<li><a href="${pageContext.request.contextPath}/findUserByPageServlet"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>--%>
                    <li><a href="${pageContext.request.contextPath}/userManagerServlet"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>
                    <li><a href="pollVote.jsp"><span class="glyphicon glyphicon-pencil"></span> 发起投票</a></li>
                    <li><a href="${pageContext.request.contextPath}/participateVoteServlet"><span class="glyphicon glyphicon-send"></span> 参与投票</a></li>
                    <li><a href="${pageContext.request.contextPath}/viewVoteServlet"><span class="glyphicon glyphicon-stats"></span> 查询投票</a></li>
                </ul>
            </div>
            <div class="login_out" id="login_out">
                <a href="register.jsp">注册</a>
                <a href="login.jsp">登录</a>
            </div>
            <div class="login_in" id="login_in">
                <a href="userInfo.jsp" title="个人中心">${loginUser.username}</a>
                <a href="${pageContext.request.contextPath}/existUserServlet">退出登录</a>
            </div>
        </div>
    </div>

    <div class="home-content container" style="width: 750px;">
        <div class="row">
            <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="3000" style="margin-top: 15px;">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="images/10.jpg" alt="First slide" style="width: 100%; height: 320px;">
                    </div>
                    <div class="item">
                        <img src="images/10.jpg" alt="Second slide" style="width: 100%; height: 320px;">
                    </div>
                    <div class="item">
                        <img src="images/10.jpg" alt="Third slide" style="width: 100%; height: 320px;">
                    </div>
                </div>
                <!-- 轮播（Carousel）导航 -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6"><a class="content_main_block" href="${pageContext.request.contextPath}/findUserByPageServlet" style="background: #4095e8;">用户管理</a></div>
            <div class="col-md-6"><a class="content_main_block" href="pollVote.jsp" style="background: #185c37;">发起投票</a></div>
            <div class="col-md-6"><a class="content_main_block" href="${pageContext.request.contextPath}/participateVoteServlet" style="background: #185c37;">参与投票</a></div>
            <div class="col-md-6"><a class="content_main_block" href="${pageContext.request.contextPath}/viewVoteServlet" style="background: #4095e8;">查询投票</a></div>
        </div>
    </div>
</body>
</html>
