<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Hello World!</h2>
<img src="images/logo.png" alt="">
<c:set var="myName" value="Andrii + Nataliia = "/>
<h1>${myName}
<c:if test="${10 > 9}">
<span>Love</span>
    </c:if>
    <c:if test="${10 > 9}">
<span> + Sex</span>
    </c:if>
</h1>
</body>
</html>
