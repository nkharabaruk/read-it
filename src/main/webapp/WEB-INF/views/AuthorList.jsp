<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<div align="center">
    <h1>Authors List</h1>

    <table border="1">
        <th>No</th>
        <th>Name</th>
        <th>Year</th>
        <th>Biography</th>
        <th>Books</th>

        <c:forEach var="author" items="${authorList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td><a href="author/${author.id}">${author.firstName} ${author.secondName}</a></td>
                <td>${author.dateOfBirth} - ${author.dateOfDeath}</td>
                <td>${author.biography}</td>
                <td>
                    <c:forEach var="book" items="${author.books}">
                        <a href="book/${book.id}">${book.title}</a><br>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
