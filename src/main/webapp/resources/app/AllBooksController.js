mainModule.controller('AllBooksController', ['$scope', 'BookService', 'CategoryService', function ($scope, bookService, categoryService) {
    $scope.books = [];

    $scope.showAllBooks = function () {
        bookService.getAllBooks().then(function (data) {
            $scope.books = data;
        });
    };

    $scope.showAllBooks();

    $scope.loadCategories = function () {
        categoryService.getRootCategories().then(function (data) {
            $scope.categories = data;
        })
    };

    $scope.loadCategories();
}]);