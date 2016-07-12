mainModule.controller('AllAuthorsController', ['$scope', 'AuthorService', function ($scope, authorService) {

    $scope.authors = [];

    authorService.getAllAuthors().then(function (data) {
        $scope.authors = data;
    });

}]);