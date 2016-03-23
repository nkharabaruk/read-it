var mainModule = angular.module('mainModule', ['ngRoute']);

mainModule.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
        when('/books', {
            templateUrl: 'resources/app/AllBooksView.html',
            controller: 'AllBooksController'
        }).
        when('/authors', {
            templateUrl: 'resources/app/AllAuthorsView.html',
            controller: 'AllAuthorsController'
        }).
        when('/book/:id', {
            templateUrl: 'resources/app/BookInfView.html',
            controller: 'BookController'
        }).
        when('/author/:id', {
            templateUrl: 'resources/app/AuthorInfView.html',
            controller: 'AuthorController'
        });
    }]);


define([
    'resources/app/BookService',
    'resources/app/AllBooksController',
    'resources/app/BookController',
    'resources/app/AuthorService',
    'resources/app/AllAuthorsController',
    'resources/app/AuthorController',
    'resources/app/CategoryService'
]);
