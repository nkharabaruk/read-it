var mainModule = angular.module('mainModule', ['ngRoute']);

mainModule.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
        when('/books', {
            templateUrl: 'resources/app/AllBooksView.html',
            controller: 'BookController'
        }).
        when('/authors', {
            templateUrl: 'resources/app/AllAuthorsView.html',
            controller: 'AuthorController'
        });
    }]);


//define([
//    'app/bookController',
//    'app/bookService'
//], function () {
//});
//})();
