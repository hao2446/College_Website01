<%--
  Created by IntelliJ IDEA.
  User: 邓旭东
  Date: 2020/8/26
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    pageContext.setAttribute("ctp",request.getContextPath());
%>
<body>
<form action="${ctp}/Admin/login" method="post">
    请输入用户名：<input type="text" name="loginName"><br>
    请输入密码：<input type=password name="AdminPassword"><br>
    提交：<input type="submit" value="登录">
</form>
</body>
</html>
