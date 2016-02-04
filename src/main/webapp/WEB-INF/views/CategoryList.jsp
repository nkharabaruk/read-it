<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nataliia
  Date: 03.02.16
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
<c:forEach var="category" items="${categoryList}" varStatus="status">
  <tr>
    <td><a href="parent/${category.key.id}">${category.key.name}</a></td>
    <td>
      <c:forEach var="book" items="${category.value}">
        <a href="book/${book.id}">${book.title}</a>
      </c:forEach>
    </td>
  </tr>
</c:forEach>
</table>
</body>
</html>
