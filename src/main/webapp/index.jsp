<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="readit">
<head>
    <script src="resources/assets/bower_components/angular/angular.min.js" type="text/javascript"></script>
</head>
<body ng-controller="Controller">
<h2>Hello World!</h2>
<img src="images/logo.png" alt="">
<h3><a href="list">All books</a></h3>
<h3><a href="authors">All authors</a></h3>
<h3><a href="categories">All categories</a></h3>
<h4>{{ 1 + 1 }}</h4>
<h4>{{ string }}</h4>
<script>
    angular.module('readit', [])
            .controller('Controller', function($scope) {
                $scope.string = "Hello from Angular Controller";
            });
</script>
</body>

</html>
