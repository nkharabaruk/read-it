mainModule.controller('AuthorController', ['$scope', '$routeParams', 'AuthorService', 'BookService', function ($scope, $routeParams, authorService, bookService) {

    $scope.author = {};
    $scope.books = [];

    $scope.loadThisAuthor = function (authorId) {
        authorService.getAuthor(authorId).then(function (data) {
            $scope.author = data;
        });
    };

    $scope.getBooksByAuthor = function (authorId) {
        bookService.getBooksByAuthor(authorId).then(function (data) {
            $scope.books = data;
        })
    };

    $scope.loadThisAuthor($routeParams.id);
    $scope.getBooksByAuthor($routeParams.id);
}]);