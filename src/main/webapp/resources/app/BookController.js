mainModule.controller('BookController', ['$scope', 'BookService', function ($scope, bookService) {
    $scope.books = [];

    $scope.showAllBooks = function () {
        bookService.getAllBooks().then(function (data) {
            $scope.books = data;
        });
    };
}]);