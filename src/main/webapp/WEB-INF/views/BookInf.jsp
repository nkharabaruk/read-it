<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nataliia
  Date: 28.01.16
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <img src="../images/${book.image}" alt="">
  <h1>${book.title}</h1>
  <c:forEach var="author" items="${authors}">
      <h2>${author.firstName} ${author.lastName}</h2>
  </c:forEach>
  <h3>${book.year}</h3>
  <h4>${book.description}</h4>
  <c:forEach var="category" items="${book.category}">
    ${category.name}
  </c:forEach>

</body>
</html>
