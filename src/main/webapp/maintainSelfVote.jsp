<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>在线投票系统——维护</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/common.css" />
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        function deleteNotVote(subjectId) {
            if (confirm("你确定要删除该主题投票？")) {
                location.href = "${pageContext.request.contextPath}/deleteNotVoteServlet?subjectId="+subjectId;
            }
        }

        function backUserInfo() {
            location.href = "${pageContext.request.contextPath}/userInfo.jsp";
        }
    </script>
</head>
<body>
    <div class="container" style="width: 600px; margin-top: 30px;">
        <table class="table table-bordered table-hover">
            <tr class="success">
                <td>发起的投票名称</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${notVotedSubject}" var="subject">
                <tr>
                    <td>${subject.title}</td>
                    <td>
                        <a href="javascript:deleteNotVote(${subject.subjectId});">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="form-group">
            <button onclick="backUserInfo();" class="btn btn-success form-control">返回</button>
        </div>
    </div>
</body>
</html>
