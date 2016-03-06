mainModule.controller('AuthorController', ['$scope', 'AuthorService', function ($scope, authorService) {

    $scope.authors = [];

    $scope.showAllAuthors = function () {
        authorService.getAllAuthors().then(function (data) {
            $scope.authors = data;
        });
    };
}]);