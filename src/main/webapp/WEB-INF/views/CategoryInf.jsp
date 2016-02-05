<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nataliia
  Date: 04.02.16
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <ul>
    <c:forEach var="book" items="${books}">
      <li><a href="/book/${book.id}">${book.title}</a></li>
    </c:forEach>
  </ul>
</body>
</html>
