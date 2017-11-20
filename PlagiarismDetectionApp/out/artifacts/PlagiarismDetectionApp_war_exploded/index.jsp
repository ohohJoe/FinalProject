<%--
  Created by IntelliJ IDEA.
  User: Joe
  Date: 2017/11/19
  Time: 下午9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>文件上传</title>
</head>
<body>
<form
        action="${pageContext.request.contextPath }/entry"
        enctype="multipart/form-data" method="post">
  上传用户：<input type="text" name="username"><br>
  上传文件1：<input type="file" name="file1"><br>
  上传文件2：<input type="file" name="file2"><br>
  <input type="submit" value="提交并检测">
</form>

</body>
</html>