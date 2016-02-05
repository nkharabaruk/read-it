<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nataliia
  Date: 29.01.16
  Time: 9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<img src="../images/${author.image}" alt="">

<h1>${author.firstName} ${author.middleName} ${author.lastName}</h1>

<h2>${author.dateOfBirth} - ${author.dateOfDeath}</h2>
<h4>${author.biography}</h4>
<c:forEach var="book" items="${author.books}">
    <h3>${book.title}</h3>
</c:forEach>
</body>
</html>
