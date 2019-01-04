<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登录</title>
</head>
<body>
	<form:form action="check" modelAttribute="userinfo">
		<fieldset>
			<legend>welcome</legend>
			<p>
				<label for="username">用户名：</label>
				<form:input path="username" size="50" />
				<form:errors path="username" cssClass="error"></form:errors>
			</p>
			<p>
				<label for="password">密码：</label>
				<form:input path="password" />
				<form:errors path="password" cssClass="error"></form:errors>
			</p>
			<p>
				<input type="submit" value="登录" class="btn out">
			</p>
		</fieldset>
	</form:form>
</body>
</html>