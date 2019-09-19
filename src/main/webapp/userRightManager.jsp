<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>在线投票系统——用户权限</title>
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <%--<link rel="stylesheet" href="css/index.css" />--%>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function () {
           $("#viewAllUser").click(function () {
               location.href = "${pageContext.request.contextPath}/findUserByPageServlet";
           });
        });
    </script>
</head>
<body>
    <div class="container" style="width: 600px; margin-top: 30px;">
        <table class="table table-bordered table-hover">
            <tr class="success">
                <td>申请权限者</td>
                <td>申请权限</td>
            </tr>
            <c:forEach items="${allRightBean}" var="item">
                <tr>
                    <td>${item.user.username}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?username=${item.user.username}">修改权限</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="form-group">
            <button id="viewAllUser" type="submit" class="btn btn-success form-control">查看所有用户信息</button>
        </div>
    </div>
</body>
</html>
