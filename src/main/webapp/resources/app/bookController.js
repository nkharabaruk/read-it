angular.module('mainModule')
    .controller('BookController', ['$scope', 'BookService', function ($scope, bookService) {
    $scope.string = "Hello from Angular Controller";
    $scope.books = [];

    $scope.showAllBooks = function () {
        bookService.getAllBooks().then(function(data) {
            $scope.books = data;
        });
    };
}]);