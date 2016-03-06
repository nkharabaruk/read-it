<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="mainModule">
<head>

</head>
<body>
<img src="images/logo.png" alt="">

<h3><a href="list">All books</a></h3>

<h3><a href="authors">All authors</a></h3>

<h3><a href="categories">All categories</a></h3>

<h4>{{ string }}</h4>
<p>{{books}}</p>
<div><a href="#books">Show Books View</a></div>
<div><a href="#authors">Show Authors View</a></div>
<ng-view></ng-view>
</body>
<script src="resources/assets/bower_components/angular/angular.min.js" type="text/javascript"></script>
<script src="resources/assets/bower_components/angular-route/angular-route.min.js" type="text/javascript"></script>
<script src="resources/app/mainModule.js"></script>
<script src="resources/app/BookService.js"></script>
<script src="resources/app/BookController.js"></script>
<script src="resources/app/AuthorService.js"></script>
<script src="resources/app/AuthorController.js"></script>
<%--<script src="resources/assets/bower_components/requirejs/require.js"></script>--%>
<%--<script src="resources/app/runApp.js"></script>--%>
</html>
