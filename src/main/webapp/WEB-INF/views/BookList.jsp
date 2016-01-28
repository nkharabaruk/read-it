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
        <td>${book.author}</td>
        <td><a href="book/${book.id}">${book.name}</a></td>
        <td>${book.year}</td>
        <td>${book.description}</td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>
