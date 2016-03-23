mainModule.controller('BookController', ['$scope', '$routeParams', 'BookService', 'CategoryService',
    function ($scope, $routeParams, bookService, categoryService) {
        $scope.book = {};
        $scope.categories = [];

        $scope.loadThisBook = function (bookId) {
            bookService.getBook(bookId).then(function (data) {
                $scope.book = data;
            });
        };

        $scope.loadThisBook($routeParams.id);

        $scope.getBookCategories = function (bookId) {
            categoryService.getBookCategories(bookId).then(function (data) {
                $scope.categories = data;
            });
        };
        $scope.getBookCategories($routeParams.id);
    }]);