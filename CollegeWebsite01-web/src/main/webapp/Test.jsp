<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-09-28
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${ctp}/message/getAllNews" method="post">
    请输入用户名：<input type="text" name="messagetype"><br>
    请输入用户名：<input type="text" name="page"><br>
    提交：<input type="submit" value="登录">
</form>
</body>
</html>
