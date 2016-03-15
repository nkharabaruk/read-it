mainModule.controller('AllAuthorsController', ['$scope', 'AuthorService', function ($scope, authorService) {

    $scope.authors = [];

    $scope.showAllAuthors = function () {
        authorService.getAllAuthors().then(function (data) {
            $scope.authors = data;
        });
    };

    $scope.showAllAuthors();
}]);