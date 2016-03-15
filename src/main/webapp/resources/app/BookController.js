mainModule.controller('BookController', ['$scope', '$routeParams', 'BookService', function ($scope, $routeParams, bookService) {
    $scope.book = {};

    $scope.loadThisBook = function (bookId) {
        bookService.getBook(bookId).then(function (data) {
            $scope.book = data;
        });
    };

    $scope.loadThisBook($routeParams.id);
}]);