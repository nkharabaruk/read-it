<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<div align="center">
  <h1>Books List</h1>
  <h2><a href="/new">New Book</a></h2>

  <c:out value="${hello}"></c:out>

  <table border="1">
    <th>No</th>
    <th>Author</th>
    <th>Name</th>
    <th>Year</th>
    <th>Description</th>

    <c:forEach var="book" items="${bookList}" varStatus="status">
      <tr>
        <td>${status.index + 1}</td>
        <td>
        <c:forEach var="author" items="${book.value}">
          <a href="author/${author.id}">${author.secondName} ${author.firstName}</a>
        </c:forEach>
        </td>
        <td><a href="book/${book.key.id}">${book.key.title}</a></td>
        <td>${book.key.year}</td>
        <td>${book.key.description}</td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>
