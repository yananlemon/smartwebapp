<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous"></script>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>客户列表</title>
</head>
<body>
	<div class="container">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>姓名</th>
					<th>联系人</th>
					<th>联系电话</th>
					<th>电子邮件地址</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${customers }" var="obj">
					<tr>
						<td>${obj.name}</td>
						<td>${obj.contact}</td>
						<td>${obj.telephone }</td>
						<td>${obj.email }</td>
						<td>${obj.remark }</td>	
						<td><a href="customer_get?id=${obj.id }">编辑</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<a href="customer_preAdd" class="btn btn-primary"/>新增</a>
	
</body>
<script type="text/javascript">
$(document).ready(function(){
  	
	$("#goAddCustomerBtn").bind("click",function(){
		$.ajax({
            url:"customer_preAdd",
            type:"GET",
            success:function(result){
                alert(result);
            }
        })
	});

});
</script>
</html>