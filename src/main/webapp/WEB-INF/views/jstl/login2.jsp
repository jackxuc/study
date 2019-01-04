<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="js/jquery-1.11.3.min.js"></script>
	</head>
	<body>
		<form id="userinfo">
			<label>账号：</label>
			<input type="text" id="userAcc"/>
			<label>密码：</label>
			<input type="password" id="userpsd">
			<input type="button" value="登录" id="submitBtn"></input>
		</form>
		<script>
			var submitBtn = $("#submitBtn");
			submitBtn.click(function() {
				var userAccount = $("#userAcc").val();
				var userpassword = $("#userpsd").val();
				$.ajax({
					url: "./check2",
					data: {username: userAccount, password: userpassword},
					type: "POST",
					success: function(data) {
						debugger;
						alert(data);
						var message = eval('('+data+')');
						alert(message);
						window.location.href = "./goods/goodslist";
					}
				})
			})
			
		</script>
	</body>
</html>