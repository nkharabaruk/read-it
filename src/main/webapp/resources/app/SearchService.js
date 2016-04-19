mainModule.service('SearchService', ['$http', function ($http) {
    return {
        searchBooksByTitle: function (title) {
            return $http.get("searchBooksByTitle/" + title)
                .then(function (response) {
                    return response.data;
                });
        },
        searchBooksByTag: function (tag) {
            return $http.get("searchBooksByTag/" + tag)
                .then(function (response) {
                    return response.data;
                });
        },
        searchAuthorsByName: function (name) {
            return $http.get("searchAuthorsByName/" + name)
                .then(function (response) {
                    return response.data;
                });
        }
    }
}]);