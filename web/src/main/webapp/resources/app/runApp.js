require.config({
    paths: {
        angular: 'resources/assets/bower_components/angular/angular.min',
        ngRoute: 'resources/assets/bower_components/angular-route/angular-route.min',
        mainModule: 'resources/app/mainModule'
    },
    shim: {

        angular: {
            exports: 'angular'
        },

        ngRoute: {
            deps : [ 'angular' ],
            exports: 'ngRoute'
        },

        mainModule: {
            deps: ['angular', 'ngRoute']
        }
    }
});

require(['mainModule'], function () {
    angular.bootstrap(document.getElementById('mainModule'), ['mainModule']);
});