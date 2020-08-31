<%--
  Created by IntelliJ IDEA.
  User: 邓旭东
  Date: 2020/8/23
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>新闻展示</title>
</head>
<body>
${getContent.messagecontent}
${news.get(0).messagecontent}
<img src="message/show/${image}"width="500" height="500">
</body>
</html>
