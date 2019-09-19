<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>在线投票系统——发起投票</title>
	<link rel="stylesheet" href="css/common.css" />
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
		function addItem(btn) {
			var $voteContent = $("<input type='text' name='voteContent' class='form-control' placeholder='输入选项内容' />");
			$(btn).before($voteContent);
		}

		function checkVoteTitle() {
			var length = $("#voteTitle").val().trim().length;
			if (length < 2) {
				$("#voteTitle").css("borderColor","red");
				return false;
			} else {
				$("#voteTitle").css("borderColor","#ccc");
				return true;
			}
		}
		function checkVoteContent() {
			var length = $("#voteContent").val().trim().length;
			if (length == 0) {
				$("#voteContent").css("borderColor","red");
				return false;
			}
			$("#voteContent").css("borderColor","#ccc");
			return true;
		}
		function checkVoteContent2() {
			var voteContentVal = $("#voteContent").val().trim();
			var voteContent2Val = $("#voteContent2").val().trim();
			if (voteContent2Val.length == 0 || voteContent2Val == voteContentVal) {
				$("#voteContent2").css("borderColor","red");
				return false;
			}
			$("#voteContent2").css("borderColor","#ccc");
			return true;
		}

		function showPollVoteContent() {
			$('#pollVote-content-item').show();
			$('#pollVote-content-item-img').hide();
		}

		function showPollVoteContentImg() {
			$('#pollVote-content-item').hide();
			$('#pollVote-content-item-img').show();
		}

		/**
		 * 从file域获取本地图片url
		 */
		function getFileUrl(sourceId) {
			var url;
			if (navigator.userAgent.indexOf("MSIE")>=1) { // IE
				url = document.getElementById(sourceId).value;
			} else if (navigator.userAgent.indexOf("Firefox")>0) { // Firefox
				url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
			} else if (navigator.userAgent.indexOf("Chrome")>0) { // Chrome
				url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
			}
			return url;
		}

		/**
		 * 将本地图片 显示到浏览器上
		 */
		function preImg(sourceId, targetId) {
			var url = getFileUrl(sourceId);
			var $img = document.getElementById(sourceId);
			var imgFile = document.getElementById(sourceId).files[0];
			/*图片类型正则验证*/
			var imgStr = /\.(jpg|jpeg|png|bmp|BMP|JPG|PNG|JPEG)$/;
			if (!imgStr.test(imgFile.name)) {
				alert("文件不是图片类型");
			} else {
				/*图片大小*/
				var imgSize = imgFile.size;
				if (imgSize < (1024*1024*1)) {
					var imgPre = document.getElementById(targetId);
					imgPre.src = url;
				} else {
					alert(imgFile.name+"大小不能超过1M");
				}
			}
		}

		function addImgItem() {
			var time = new Date().getTime();
			$('#imgContentContainer').append('<div class="col-md-4">\n' +
					'                        <input type="file" name="imgOne" value="" id="imgfile'+time+'" style="color: #fff; border: none; width: 72px;" onchange="preImg(this.id,\'imgPre'+time+'\');" />\n' +
					'                        <img id="imgPre'+time+'" src="" width="166px" height="166px" style="display: block; border: none; margin: 5px 0;" />\n' +
					'                        <input type="text" style="margin-bottom: 5px;" class="form-control" placeholder="选项描述" />\n' +
					'                    </div>');
		}

		$(function () {
			if (${loginUser==null}) {
				$("#login_out").show();
				$("#login_in").hide();
			} else {
				$("#login_out").hide();
				$("#login_in").show();
			}

			$("#voteSend").click(function () {
				if (checkVoteTitle() && checkVoteContent() && checkVoteContent2()) {
					$.post("pollVoteServlet",$("#voteSendForm").serialize(),function (data) {
						alert("发布成功");
						location.href = "index.jsp";
					});
				}
				return false;
			});

			$("#voteTitle").blur(checkVoteTitle);
			$("#voteContent").blur(checkVoteContent);
			$("#voteContent2").blur(checkVoteContent2);
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
				<li class="options-active"><a href="pollVote.jsp"><span class="glyphicon glyphicon-pencil"></span> 发起投票</a></li>
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

<div class="pollvote-content-btn container" style="width: 600px; margin-top: 20px;">
	<div class="row">
		<div class="col-md-6">
			<button onclick="showPollVoteContent()" class="btn btn-success form-control">文字投票</button>
		</div>
		<div class="col-md-6">
			<button onclick="showPollVoteContentImg()" class="btn btn-primary form-control">图片投票</button>
		</div>
	</div>
</div>

<div class="pollVote-content container" style="width: 600px; margin-top: 20px; padding: 20px; border: 1px solid #2e9057; border-radius: 5px;">
	<form id="voteSendForm" action="" method="post">
		<input type="hidden" value="${loginUser.userId}" name="userId" />
		<div class="form-group">
			<label for="voteTitle">投票主题</label>
			<input type="text" name="voteTitle" id="voteTitle" class="form-control" placeholder="输入投票主题，2~200字之间" />
		</div>
		<div class="form-group">
			<label for="voteDiscription">投票主题描述</label>
			<textarea name="voteDiscription" id="voteDiscription" class="form-control" placeholder="输入投票描述（可不写）"></textarea>
		</div>

		<div id="pollVote-content-item" class="form-group" <%--id="voteContents"--%> >
			<label for="voteContent">投票选项</label>
			<input type="text" name="voteContent" id="voteContent" class="form-control" placeholder="输入选项内容" />
			<input type="text" name="voteContent" id="voteContent2" class="form-control" placeholder="输入选项内容" />
			<button onclick="addItem(this);" id="addVoteContent" class="form-control btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span> 添加选项</button>
		</div>

		<div style="display: none;" id="pollVote-content-item-img" class="form-group">
			<label for="voteContent">投票选项</label>
			<div class="row" id="imgContentContainer">
				<div class="col-md-4">
					<input style="color: #fff; border: none; width: 72px;" type="file" name="imgOne" value="" id="img1" onchange="preImg(this.id,'imgPre1');" />
					<img id="imgPre1" src="" width="166px" height="166px" style="display: block; border: none; margin: 5px 0;" />
					<input type="text" style="margin-bottom: 5px;" class="form-control" placeholder="选项描述" />
				</div>
				<div class="col-md-4">
					<input type="file" style="color: #fff; border: none; width: 72px;" name="imgOne" id="img2" value="" onchange="preImg(this.id,'imgPre2');" />
					<img id="imgPre2" src="" width="166px" height="166px" style="display: block; border: none; margin: 5px 0;" />
					<input type="text" style="margin-bottom: 5px;" class="form-control" placeholder="选项描述" />
				</div>
			</div>
			<button onclick="addImgItem()" id="addVoteImgContent" class="form-control btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span> 添加选项</button>
		</div>

		<div class="form-group">
			<label>投票类型</label>
			<select class="form-control" name="select">
				<option value="0" selected>单选</option>
				<option value="1">多选</option>
			</select>
		</div>
		<div class="form-group">
			<div class="row">
				<div class="col-md-6">
					<label for="startTime">开始时间</label>
					<input class="form-control" type="datetime-local" name="startTime" id="startTime" />
				</div>
				<div class="col-md-6">
					<label for="endTime">结束时间</label>
					<input type="datetime-local" id="endTime" class="form-control" name="endTime" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<button id="voteSend" class="btn btn-primary form-control" type="submit"><span class="glyphicon glyphicon-check"></span> 发布</button>
		</div>
	</form>
</div>
</body>
</html>
