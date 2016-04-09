mainModule.controller('AllBooksController', ['$scope', 'BookService', 'CategoryService', function ($scope, bookService, categoryService) {
    $scope.books = [];
    categoryService.getRootCategories().then(function (data) {
        $scope.categories = data;
    });

    $scope.showAllBooks = function () {
        bookService.getAllBooks().then(function (data) {
            $scope.books = data;
        });
    };

    $scope.showAllBooks();

    //$scope.loadCategories = function () {
    //
    //};
    //
    //$scope.loadCategories();

    $scope.getBooksFromCategoryAndDescendants = function (categoryId) {
        bookService.getBooksFromCategoryAndDescendants(categoryId).then(function (data) {
            $scope.books = data;
        });
    }
}]);