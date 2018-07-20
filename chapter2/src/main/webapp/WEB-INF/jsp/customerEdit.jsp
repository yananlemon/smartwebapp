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
<title>客户编辑</title>
</head>
<body>
	<form class="form-horizontal" action="customer_edit" method="put">
		<div class="form-group">
		    <label for="name" class="col-sm-2 control-label">姓名</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="name" name = "name" value=${customer.name}>
		    </div>
	  	</div>
	  	<div class="form-group">
		    <label for="contact" class="col-sm-2 control-label">联系人</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="contact" name = "contact"  value=${customer.contact}>
		    </div>
	  	</div>
	  	<div class="form-group">
		    <label for="telephone" class="col-sm-2 control-label">联系电话</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="telephone" name = "telephone"  value=${customer.telephone}>
		    </div>
	  	</div>
	  	<div class="form-group">
		    <label for="email" class="col-sm-2 control-label">电子邮件</label>
		    <div class="col-sm-10">
		      <input type="email" class="form-control" id="email" name = "email"  value=${customer.email}>
		    </div>
	  	</div>
	  	<div class="form-group">
		    <label for="remark" class="col-sm-2 control-label">备注</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="remark" name = "remark"  value=${customer.remark}>
		    </div>
	  	</div>
	  	
	  	<input type = "submit" class="btn btn-primary" value = "保存"/>
	  	<button type="button" class="btn btn-default">取消</button>
	</form>
	
</body>


</html>