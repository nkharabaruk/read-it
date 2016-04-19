mainModule.controller('SearchController', ['$scope', 'SearchService',
    function ($scope, searchService) {
        $scope.books = [];
        $scope.authors = [];

        $scope.searchBooksByTitle = function () {
            $scope.authors = [];
            $scope.books = [];
            searchService.searchBooksByTitle($scope.bookTitle).then(function (data) {
                $scope.books = data;
            });
        };

        $scope.searchBooksByTag = function (tag) {
            window.location = "#search";
            $scope.authors = [];
            $scope.books = [];
            searchService.searchBooksByTag(tag).then(function (data) {
                $scope.books = data;
            });
        };

        $scope.searchAuthorsByName = function () {
            $scope.authors = [];
            $scope.books = [];
            searchService.searchAuthorsByName($scope.authorName).then(function (data) {
                $scope.authors = data;
            });
        };

        // TODO: avoid duplicates when tag and book title are equal
        $scope.searchAll = function () {
            $scope.authors = [];
            $scope.books = [];
            searchService.searchBooksByTitle($scope.keyWord).then(function (data) {
                $scope.books = $scope.books.concat(data);
            });
            searchService.searchAuthorsByName($scope.keyWord).then(function (data) {
                $scope.authors = data;
            });
            searchService.searchBooksByTag($scope.keyWord).then(function (data) {
                $scope.books = $scope.books.concat(data);
            });
        };
}]);