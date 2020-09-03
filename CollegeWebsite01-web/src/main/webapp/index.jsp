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
<form action="${ctp}/message/insertMessage" method="post" enctype="multipart/form-data">
    新闻名称：<input type="text" name="messagetitle"><br>
    新闻类型：<input type="text" name="messagetype"><br>
    上传图片：<input type="file" name="uploadImage"><br>
    新闻内容：<input type="text" name="messagecontent"><br>
    提交：<input type="submit" value="添加新闻">
</form>
<img src="http://localhost:8080/message/show/imagepsb.jpg" width="500" height="500">
</body>
</html>
