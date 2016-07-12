mainModule.controller('BookController', ['$scope', '$routeParams', 'BookService', 'CategoryService',
    function ($scope, $routeParams, bookService, categoryService) {
        $scope.book = {};
        $scope.categories = [];

        bookService.getBook($routeParams.id).then(function (data) {
            $scope.book = data;
        });

        categoryService.getBookCategories($routeParams.id).then(function (data) {
            $scope.categories = data;
        });

    }]);