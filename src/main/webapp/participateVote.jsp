<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>在线投票系统——参与投票</title>
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
                    <li><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
                    <%--<li><a href="${pageContext.request.contextPath}/findUserByPageServlet"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>--%>
                    <li><a href="${pageContext.request.contextPath}/userManagerServlet"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>
                    <li><a href="pollVote.jsp"><span class="glyphicon glyphicon-pencil"></span> 发起投票</a></li>
                    <li  class="options-active"><a href="${pageContext.request.contextPath}/participateVoteServlet"><span class="glyphicon glyphicon-send"></span> 参与投票</a></li>
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

    <div class="participateVote-content container" style="width: 900px;">
        <div class="row">
            <div class="col-md-8 participateVote-content-left">
                <div class="row">
                    <form class="form-inline" action="${pageContext.request.contextPath}/participateVoteServlet" method="post" style="text-align: center; margin-top: 15px; margin-bottom: 15px;">
                        <input class="form-control" name="title" value="${participateCondition.title[0]}" type="text" placeholder="投票主题搜索" />
                        <button class="form-control btn btn-success" type="submit"><span class="glyphicon glyphicon-search"></span> 搜索</button>
                    </form>
                </div>
                <div class="row">
                    <c:forEach items="${subjectPageBean.list}" var="subject" varStatus="s">
                        <div class="col-md-4">
                            <a href="${pageContext.request.contextPath}/participatePanelServlet?subjectId=${subject.subjectId}" class="thumbnail">
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
                            <c:if test="${subjectPageBean.currentPage == 1}">
                                <li class="disabled">
                                    <a href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${subjectPageBean.currentPage != 1}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/participateVoteServlet?currentPage=${subjectPageBean.currentPage-1}&rows=6&title=${participateCondition.title[0]}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <c:forEach begin="1" end="${subjectPageBean.totalPage}" var="i">
                                <c:if test="${subjectPageBean.currentPage == i}">
                                    <li class="active"><a href="${pageContext.request.contextPath}/participateVoteServlet?currentPage=${i}&rows=6&title=${participateCondition.title[0]}">${i}</a></li>
                                </c:if>
                                <c:if test="${subjectPageBean.currentPage != i}">
                                    <li><a href="${pageContext.request.contextPath}/participateVoteServlet?currentPage=${i}&rows=6&title=${participateCondition.title[0]}">${i}</a></li>
                                </c:if>
                            </c:forEach>

                            <c:if test="${subjectPageBean.currentPage == subjectPageBean.totalPage}">
                                <li class="disabled">
                                    <a href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${subjectPageBean.currentPage != subjectPageBean.totalPage}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/participateVoteServlet?currentPage=${subjectPageBean.currentPage+1}&rows=6&title=${participateCondition.title[0]}" aria-label="Previous">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <span style="font-size: 25px; margin-left: 8px;">共${subjectPageBean.totalCount}个投票,共${subjectPageBean.totalPage}页</span>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="col-md-4 participateVote-content-right">
                <h3>参与投票</h3>
                <div>
                    <c:if test="${participateSub.type == 0}">
                        <h6 id="typeBtn" style="color: #2e9057; font-weight: bold;">单选</h6>
                    </c:if>
                    <c:if test="${participateSub.type == 1}">
                        <h6 id="typeBtn" style="color: #2e9057; font-weight: bold;">多选</h6>
                    </c:if>
                    <h6>截止时间：<em>${participateSub.endTime}</em></h6>
                    <h6>主题描述：${participateSub.discription}</h6>
                </div>
                <c:if test="${participateSub.type == 0}">
                    <form action="${pageContext.request.contextPath}/countOneVotingServlet" method="post" id="oneSelectForm">
                        <table class="table table-bordered table-hover">
                            <tr class="success"><th>${participateSub.title}</th></tr>
                            <c:forEach items="${participateSub.options}" var="oneOption" varStatus="s">
                                <c:if test="${orders[0]==s.count}">
                                    <tr><td><div class="radio" style="color: #2b542c; font-weight: bolder;"><label><input type="radio" checked name="RadioGroup" value="${s.index}" /> ${oneOption.content}</label></div></td></tr>
                                </c:if>
                                <c:if test="${orders[0]!=s.count}">
                                    <tr><td><div class="radio"><label><input type="radio" name="RadioGroup" value="${s.index}" /> ${oneOption.content}</label></div></td></tr>
                                </c:if>
                            </c:forEach>
                        </table>
                        <div class="form-group">
                            <c:if test="${empty orders}">
                                <button type="submit" class="form-control btn btn-success">投 票</button>
                            </c:if>
                            <c:if test="${not empty orders}">
                                <button type="submit" style="color: #2e9057;" disabled class="form-control btn btn-success">投 票</button>
                            </c:if>
                        </div>
                    </form>
                </c:if>
                <c:if test="${participateSub.type == 1}">
                    <form action="${pageContext.request.contextPath}/countMoreVotingServlet" method="post" id="moreSelectForm">
                        <table class="table table-bordered table-hover">
                            <tr class="success"><th>${participateSub.title}</th></tr>

                            <c:set var="len" value="${fn:length(orders)}"></c:set>
                            <c:set var="i" value="0"></c:set>
                            <c:forEach items="${participateSub.options}" var="moreOption" varStatus="s">
                                <c:choose>
                                    <c:when test="${orders[i]==s.count}">
                                        <tr><td><div class="checkbox" style="color: #2b542c; font-weight: bolder;"><label><input checked type="checkbox" name="CheckboxGroup" value="${s.index}" /> ${moreOption.content}</label></div></td></tr>
                                        <c:if test="${i<len}" >
                                            <c:set var="i" value="${i+1}"></c:set>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <tr><td><div class="checkbox"><label><input type="checkbox" name="CheckboxGroup" value="${s.index}" /> ${moreOption.content}</label></div></td></tr>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        </table>
                        <div class="form-group">
                            <c:if test="${empty orders}">
                                <button type="submit" class="form-control btn btn-success">投 票</button>
                            </c:if>
                            <c:if test="${not empty orders}">
                                <button type="submit" disabled style="color: #2e9057;" class="form-control btn btn-success">投 票</button>
                            </c:if>
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
