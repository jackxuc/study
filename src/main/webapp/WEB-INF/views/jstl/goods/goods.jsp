<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>商品管理</title>
<script src="../js/jquery-3.3.1.js"></script>
<script src="../js/jquery.pagination.js"></script>
<link rel="stylesheet" type="text/css" href="../css/pagination.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
</head>
<body>
	<div class="main">
		<h2 class="title">
			<span>商品管理</span>
		</h2>
		<form action="deletes" method="post">
			<table border="1" width="100%" class="tab">
				<tr>
					<th><input type="checkbox" id="chbAll"></th>
					<th>编号</th>
					<th>产品名</th>
					<th>价格</th>
					<th>类型</th>
					<th>操作</th>
				</tr>
				<c:forEach var="goods" items="${goods}">
					<tr>
						<th><input type="checkbox" name="id" value="${goods.id}"></th>
						<td>${goods.id}</td>
						<td>${goods.name}</td>
						<td>${goods.price}</td>
						<td><img src="../images/${goods.picture}" height="50"
							width="50" /></td>
						<td><a href="delete/${goods.id}" class="abtn">删除</a> <a
							href="edit/${goods.id}" class="abtn">编辑</a> <a
							href="<c:url value="/goods/"/>uploadPicture/${goods.id}"
							class="abtn">上传</a></td>
					</tr>
				</c:forEach>
			</table>
			<div id="pager"></div>
			<p>
				<a href="openadd" class="abtn out">添加</a> <input type="submit"
					value="批量删除" class="btn out" /><a href="../chat/login" class="abtn out">在线聊天</a>
			</p>
			<script type="text/javascript">
				debugger;
				//初始化分页组件
				var count = eval('${count}');
				var size = eval('${size}');
				var pageNO = eval('${pageNO}');
				$("#pager").pagination(count, {
					items_per_page : size,
					current_page : pageNO - 1,
					next_text : "下一页",
					prev_text : "上一页",
					num_edge_entries : 2,
					load_first_page : false,
					callback : handlePaginationClick
				});

				//回调方法
				function handlePaginationClick(new_page_index,
						pagination_container) {
					location.href = "goodslist?page=" + (new_page_index + 1);
				}

				var defaultSrc = "<c:url value="../images/default.jpg"/>";
				$(".tab img").bind("error", function() {
					$(this).prop("src", defaultSrc);
				});
			</script>
		</form>

	</div>
</body>
</html>