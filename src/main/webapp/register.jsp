<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在线投票系统——注册</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/register.css" />
    <script src="js/jquery.min.js"></script>
    <script>

        function changeImg(img) {
            img.src = "checkCode?time="+new Date().getTime();
        }

        function closeRegister() {
            location.href="${pageContext.request.contextPath}/index.jsp";
        }

        // 校验用户名，单词字符，长度8到16位
        function checkUername() {
            var username = $("#username").val();
            var reg_username = /^\w{2,16}$/;
            var flag = reg_username.test(username);
            if (flag) {
                $("#username").css("border","3px solid #eeeeee");
            } else {
                $("#username").css("border","3px solid red");
            }
            return flag;
        }

        // 校验密码
        function checkPassword() {
            var password = $("#password").val();
            var reg_password = /^\w{6,16}$/;
            var flag = reg_password.test(password);
            if (flag) {
                $("#password").css("border","3px solid #eeeeee");
            } else {
                $("#password").css("border","3px solid red");
            }
            return flag;
        }

        // 校验确认密码
        function checkConfirmPwd() {
            var confirmPwd = $("#confirmPwd").val().trim();
            var password = $("#password").val().trim();
            var flag = false;
            if (confirmPwd != password || confirmPwd.length == 0) {
                $("#confirmPwd").css("border","3px solid red");
            } else {
                flag = true;
                $("#confirmPwd").css("border","3px solid #eeeeee");
            }
            return flag;
        }

        // 校验验证码不为空
        function checkCode() {
            var checkCode = $("#checkcode").val().trim();
            if (checkCode.length == 0) {
                $("#checkcode").css("border","3px solid red");
                return false;
            }
            $("#checkcode").css("border","3px solid #eee");
            return true;
        }

        $(function () {
            // 当表单提交时，调用所有的校验方法
            $("#registerForm").submit(function () {
                if (checkUername() && checkPassword() && checkConfirmPwd() && checkCode()) {
                    // 校验通过，发送ajax请求，提交表单数据 username=123456789&password=123456789
                    $.post("registerServlet",$(this).serialize(),function (data) {
                        // 处理服务器响应的数据 data {checkFlag:true,userExist:false}
                        if (data.checkFlag == false) {
                            $("#msg").html("验证码错误");
                        } else if (data.userExist == false) {
                            $("#msg").html("用户名已存在，可直接登录");
                        } else {
                            alert("恭喜您，注册成功！");
                            location.href = "index.jsp";
                        }
                    });
                }
                return false;
            });

            // 当某个组件失去焦点时，调用对应的校验方法
            $("#username").blur(checkUername);
            $("#password").blur(checkPassword);
            $("#confirmPwd").blur(checkConfirmPwd);
            $("#checkcode").blur(checkCode);
        });
    </script>
</head>
<body>
    <div class="register" id="register">
        <div class="register_title">
            <h2>用 户 注 册</h2>
            <i class="glyphicon glyphicon-remove close_register" onclick="closeRegister();"></i>
        </div>
        <form action="" id="registerForm">
            <input type="hidden" name="action" value="register" />
            <div class="form-group">
                <label for="username">用户名</label>
                <input id="username" name="username" type="text" placeholder="请输入用户名" />
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input id="password" name="password" type="password" placeholder="请输入密码" />
            </div>
            <div class="form-group">
                <label for="confirmPwd">确认密码</label>
                <input id="confirmPwd" type="password" placeholder="请确认密码" />
            </div>
            <div class="form-group">
                <label for="checkcode">验证码</label>
                <input id="checkcode" class="checkcode" name="checkcode" type="text" placeholder="请输入验证码" />
                <img src="checkCode" alt="看不清" onclick="changeImg(this);" style="width: 110px; height: 35px; margin-left: 5px; vertical-align: top;" />
            </div>
            <div id="msg" style="color: red;margin-left: 115px; padding-bottom: 5px;"></div>
            <div>
                <input class="btn_submit" type="submit" value="注 册" />
            </div>
        </form>
    </div>
</body>
</html>

