mainModule.controller('AuthorController', ['$scope', '$routeParams', 'AuthorService', 'BookService', function ($scope, $routeParams, authorService, bookService) {

    $scope.author = {};
    $scope.books = [];

    authorService.getAuthor($routeParams.id).then(function (data) {
        $scope.author = data;
    });

    bookService.getBooksByAuthor($routeParams.id).then(function (data) {
        $scope.books = data;
    });

}]);