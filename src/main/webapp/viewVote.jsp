<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>在线投票系统——查询投票</title>
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/index.css" />
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/chart.min.js"></script>
    <script src="js/data_analysis.js"></script>
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

        function viewVotingResult(subjectId) {
            $.post("viewVotePanelServlet",{"subjectId":subjectId}, function (data) {
                // alert(data.counts);
                if (data.datas == false) {
                    location.href = "${pageContext.request.contextPath}/error/405.jsp";
                } else {
                    barLabels = data.labels;
                    barData = data.counts;
                    barLabel = data.title+"(单位：票)";

                    var html=" <canvas id='bar-chart' width='100%' height='80'></canvas>";
                    var cart = $("#cart-body").children("#bar-chart");
                    cart.remove();
                    $("#cart-body").append(html);
                    var barChart = $("#bar-chart");
                    if (barChart.length > 0) {
                        new Chart(barChart, {
                            type: 'bar',
                            data: {
                                labels: barLabels,
                                datasets: [{
                                    label: barLabel,
                                    data: barData,
                                    backgroundColor: [
                                        'rgba(244, 88, 70, 0.5)',
                                        'rgba(33, 150, 243, 0.5)',
                                        'rgba(0, 188, 212, 0.5)',
                                        'rgba(42, 185, 127, 0.5)',
                                        'rgba(156, 39, 176, 0.5)',
                                        'rgba(253, 178, 68, 0.5)',
                                        'rgba(244, 88, 70, 0.5)',
                                        'rgba(33, 150, 243, 0.5)',
                                        'rgba(0, 188, 212, 0.5)',
                                        'rgba(42, 185, 127, 0.5)',
                                        'rgba(33, 150, 243, 0.5)',
                                        'rgba(0, 188, 212, 0.5)',
                                        'rgba(42, 185, 127, 0.5)'
                                    ],
                                    borderColor: [
                                        '#F45846',
                                        '#2196F3',
                                        '#00BCD4',
                                        '#2ab97f',
                                        '#9C27B0',
                                        '#fdb244'
                                    ],
                                    borderWidth: 1
                                }]
                            },
                            options: {
                                legend: {
                                    display: false
                                },
                                scales: {
                                    yAxes: [{
                                        ticks: {
                                            beginAtZero: true
                                        }
                                    }]
                                }
                            }
                        });
                    }

                    var html2=" <canvas id='pie-chart' width='100%' height='80'></canvas>";
                    var cart2 = $("#cart-body2").children("#pie-chart");
                    cart2.remove();
                    $("#cart-body2").append(html2);
                    var pieChart = $("#pie-chart");
                    if (pieChart.length > 0) {
                        new Chart(pieChart, {
                            type: 'pie',
                            data: {
                                labels: barLabels,
                                datasets: [{
                                    label: barLabel,
                                    data: barData,
                                    backgroundColor: [
                                        'rgba(244, 88, 70, 0.5)',
                                        'rgba(33, 150, 243, 0.5)',
                                        'rgba(0, 188, 212, 0.5)',
                                        'rgba(42, 185, 127, 0.5)',
                                        'rgba(156, 39, 176, 0.5)',
                                        'rgba(253, 178, 68, 0.5)',
                                        'rgba(42, 185, 127, 0.5)',
                                        'rgba(156, 39, 176, 0.5)',
                                        'rgba(253, 178, 68, 0.5)',
                                        'rgba(42, 185, 127, 0.5)',
                                        'rgba(156, 39, 176, 0.5)',
                                        'rgba(253, 178, 68, 0.5)'
                                    ],
                                    borderColor: [
                                        'rgba(244, 88, 70, 0.5)',
                                        'rgba(33, 150, 243, 0.5)',
                                        'rgba(0, 188, 212, 0.5)',
                                        'rgba(42, 185, 127, 0.5)',
                                        'rgba(156, 39, 176, 0.5)',
                                        'rgba(253, 178, 68, 0.5)'
                                    ],
                                    borderWidth: 1
                                }]
                            }
                        });
                    }
                }

            });

        }
    </script>
</head>
<body>
    <div id="header" class="header">
        <div class="container">
            <div class="header-logo">OnlineVotingSystem</div>
            <div class="header-options">
                <ul>
                    <li><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
                    <%--<li><a href="${pageContext.request.contextPath}/findUserByPageServlet"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>--%>
                    <li><a href="${pageContext.request.contextPath}/userManagerServlet"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>
                    <li><a href="pollVote.jsp"><span class="glyphicon glyphicon-pencil"></span> 发起投票</a></li>
                    <li><a href="${pageContext.request.contextPath}/participateVoteServlet"><span class="glyphicon glyphicon-send"></span> 参与投票</a></li>
                    <li class="options-active"><a href="${pageContext.request.contextPath}/viewVoteServlet"><span class="glyphicon glyphicon-stats"></span> 查询投票</a></li>
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

    <div class="viewVote-content container" style="width: 1000px;">
        <div class="row">
            <div class="col-md-8 viewVote-content-left">
                <div class="row">
                    <form class="form-inline" action="${pageContext.request.contextPath}/viewVoteServlet" method="post" style="text-align: center; margin-top: 15px; margin-bottom: 15px;">
                        <input class="form-control" name="title" value="${viewVoteCondition.title[0]}" type="text" placeholder="主题搜索" />
                        <button class="form-control btn btn-success" type="submit"><span class="glyphicon glyphicon-search"></span> 搜索</button>
                    </form>
                </div>
                <div class="row">
                    <c:forEach items="${viewVoteSubject.list}" var="subject" varStatus="s">
                        <div class="col-md-4">
                            <a href="javascript:viewVotingResult(${subject.subjectId});" class="thumbnail">
                                <img src="images/${s.index%4+1}.jpg" />
                                <div class="caption">
                                    <h4>${subject.title}</h4>
                                    <ul type="circle">
                                        <li><span class="glyphicon glyphicon-star-empty"></span> ${subject.options[0].content}</li>
                                        <li><span class="glyphicon glyphicon-star-empty"></span> ${subject.options[1].content}</li>
                                    </ul>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
                <div class="row" style="text-align: center;">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <c:if test="${viewVoteSubject.currentPage == 1}">
                                <li class="disabled">
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${viewVoteSubject.currentPage != 1}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/viewVoteServlet?currentPage=${viewVoteSubject.currentPage-1}&rows=6&title=${viewVoteCondition.title[0]}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:forEach begin="1" end="${viewVoteSubject.totalPage}" var="i">
                                <c:if test="${viewVoteSubject.currentPage == i}">
                                    <li class="active"><a href="${pageContext.request.contextPath}/viewVoteServlet?currentPage=${i}&rows=6&title=${viewVoteCondition.title[0]}">${i}</a></li>
                                </c:if>
                                <c:if test="${viewVoteSubject.currentPage != i}">
                                    <li><a href="${pageContext.request.contextPath}/viewVoteServlet?currentPage=${i}&rows=6&title=${viewVoteCondition.title[0]}">${i}</a></li>
                                </c:if>
                            </c:forEach>

                            <c:if test="${viewVoteSubject.currentPage == viewVoteSubject.totalPage}">
                                <li class="disabled">
                                    <a href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${viewVoteSubject.currentPage != viewVoteSubject.totalPage}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/viewVoteServlet?currentPage=${viewVoteSubject.currentPage+1}&rows=6&title=${viewVoteCondition.title[0]}" aria-label="Previous">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <span style="font-size: 25px; margin-left: 8px;">共${viewVoteSubject.totalCount}个投票,共${viewVoteSubject.totalPage}页</span>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="col-md-4 viewVote-content-right">
                <h3>查看投票结果</h3>
                <div class="row" style="margin: 20px 0;">
                    <div class="card">
                        <div class="card-header bg-light">
                            <h4><span class="glyphicon glyphicon-chevron-right"></span> 投票条形图</h4>
                        </div>
                        <div class="card-body" id="cart-body">

                        </div>
                    </div>
                </div>
                <div class="row" style="margin: 20px 0;">
                    <div class="card">
                        <div class="card-header bg-light">
                            <h4><span class="glyphicon glyphicon-chevron-right"></span> 投票饼状图</h4>
                        </div>
                        <div class="card-body" id="cart-body2">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
