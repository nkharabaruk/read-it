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
<ul>
<c:forEach var="node" items="${rootCategories}">
    <li><a href="category/${node.id}">${node.name}</a></li>
    <%--${node.children}--%>
    <c:set var="node" value="${node}" scope="request"/>
    <jsp:include page="node.jsp"/>
</c:forEach>
</ul>
</body>
</html>
