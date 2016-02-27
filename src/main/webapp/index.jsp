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
<button ng-click="showAllBooks()">Show all books</button>
<button ng-click="books = {}">Clear books</button>
<p>{{books}}</p>
<table>
    <tr>
        <td ng-repeat="book in books">
            <img src="images/{{book.image}}" height="100px"/>
            <h4>{{book.title}}</h4>
        </td>
    </tr>
</table>
<script>
    angular.module('readit', [])
            .service('BookService', ['$http', '$q', function ($http, $q) {
                return {
                    getAllBooks: function () {
                        return $http.get("/getAllBooks")
                                .then(function(response) {
                                    return response.data;
                                });
                    }
                }
            }])
            .controller('Controller', ['$scope', 'BookService', function ($scope, bookService) {
                $scope.string = "Hello from Angular Controller";
                $scope.books = "boooooks";

                $scope.showAllBooks = function () {
                    bookService.getAllBooks().then(function(data) {
                        $scope.books = data;
                    });
                };

                $scope.clearBooks = function () {
                    $scope.books = "booooobs";
                }
            }]);
</script>
</body>

</html>
