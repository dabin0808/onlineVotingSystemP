<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在线投票系统——登录</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/register.css" />
    <script src="js/jquery.min.js"></script>
    <script>
        function changeImg(img) {
            img.src = "checkCode?time="+new Date().getTime();
        }

        function closeRegister() {
            location.href="index.jsp";
        }
    
        $(function () {
            // 给登录按钮绑定单击事件
            $("#btn_submit").click(function () {
                if ($("#username").val().trim().length == 0) {
                    $("#msg").html("用户名不能为空");
                } else if ($("#password").val().trim().length == 0) {
                    $("#msg").html("密码不能为空");
                } else if ($("#checkcode").val().trim().length == 0) {
                    $("#msg").html("验证码不能为空");
                } else {
                    $.post("loginServlet",$("#loginForm").serialize(),function (data) {
                        if (data.checkFlag == false) {
                            $("#msg").html("验证码输入错误");
                        } else if (data.loginFlag == false) {
                            $("#msg").html("用户名或密码错误");
                        } else {
                            location.href = "index.jsp";
                        }
                    });
                }
                return false;
            }); 
        });    
    </script>
</head>
<body>
    <div class="register">
        <div class="register_title">
            <h2>登录</h2>
            <i class="glyphicon glyphicon-remove close_register" onclick="closeRegister();"></i>
        </div>
        <form id="loginForm" method="post" action="">
            <input type="hidden" name="action" value="login" />
            <div class="form-group">
                <label for="username">用户名</label>
                <input id="username" name="username" type="text" placeholder="请输入用户名" />
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input id="password" name="password" type="password" placeholder="请输入密码" />
            </div>
            <div class="form-group">
                <label for="checkcode">验证码</label>
                <input id="checkcode" name="checkcode" class="checkcode" type="text" placeholder="请输入验证码" />
                <img src="checkCode" alt="看不清" onclick="changeImg(this);" style="width: 110px; height: 35px; margin-left: 5px; vertical-align: top;" />
            </div>
            <div id="msg" class="msg" style="color: red;margin-left: 115px; padding-bottom: 5px;"></div>
            <div>
                <input id="btn_submit" class="btn_submit" type="submit" value="登 录" />
            </div>
        </form>
    </div>
</body>
</html>
