<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nataliia
  Date: 05.02.16
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul>
<c:forEach var="node" items="${node.children}">
  <li><a href="category/${node.id}">${node.name}</a></li>
  <c:set var="node" value="${node}" scope="request"/>
  <jsp:include page="node.jsp"/>
</c:forEach>
</ul>