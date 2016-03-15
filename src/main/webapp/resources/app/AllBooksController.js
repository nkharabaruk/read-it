mainModule.controller('AllBooksController', ['$scope', 'BookService', function ($scope, bookService) {
    $scope.books = [];
    $scope.book = {};

    $scope.showAllBooks = function () {
        bookService.getAllBooks().then(function (data) {
            $scope.books = data;
        });
    };

    $scope.showAllBooks();


    $scope.loadThisBook = function (bookId) {
        bookService.getBook(bookId).then(function (data) {
            $scope.book = data;
        });
    };
}]);