<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="mainModule">
<head>

</head>
<body ng-controller="BookController">
<h2>Hello World!</h2>
<img src="images/logo.png" alt="">

<h3><a href="list">All books</a></h3>

<h3><a href="authors">All authors</a></h3>

<h3><a href="categories">All categories</a></h3>
<h4>{{ 1 + 1 }}</h4>
<h4>{{ string }}</h4>
<button ng-click="showAllBooks()">Show all books</button>
<button ng-click="books = []">Clear books</button>
<p>{{books}}</p>
<table>
    <tr>
        <td ng-repeat="book in books">
            <img src="images/{{book.image}}" height="100px"/>
            <h4>{{book.title}}</h4>
            <h5 ng-repeat="author in book.authors">{{author.firstName + " " + author.lastName}}</h5>
        </td>
    </tr>
</table>
</body>
<script src="resources/assets/bower_components/angular/angular.min.js" type="text/javascript"></script>
<script src="resources/app/mainModule.js"></script>
<script src="resources/app/bookService.js"></script>
<script src="resources/app/bookController.js"></script>
<%--<script src="resources/assets/bower_components/requirejs/require.js"></script>--%>
<%--<script src="resources/app/runApp.js"></script>--%>
</html>
