angular.module('mainModule')
    .service('BookService', ['$http', function ($http) {
        return {
            getAllBooks: function () {
                return $http.get("/getAllBooks")
                    .then(function(response) {
                        return response.data;
                    });
            }
        }
    }]);