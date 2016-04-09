mainModule.controller('AllBooksController', ['$scope', 'BookService', 'CategoryService', function ($scope, bookService, categoryService) {
    $scope.books = [];

    categoryService.getRootCategories().then(function (data) {
        $scope.categories = data;
    });

    bookService.getAllBooks().then(function (data) {
        $scope.books = data;
    });

    $scope.getBooksFromCategoryAndDescendants = function (categoryId) {
        bookService.getBooksFromCategoryAndDescendants(categoryId).then(function (data) {
            $scope.books = data;
        });
    }
}]);