mainModule.controller('SearchController', ['$scope', 'SearchService',
    function ($scope, searchService) {
        $scope.books = [];
        $scope.authors = [];

        $scope.searchBooksByTitle = function () {
            searchService.searchBooksByTitle($scope.bookTitle).then(function (data) {
                $scope.books = data;
            });
        };

        $scope.searchAuthorsByName = function () {
            searchService.searchAuthorsByName($scope.authorName).then(function (data) {
                $scope.authors = data;
            });
        };
}]);