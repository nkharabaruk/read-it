mainModule.service('AuthorService', ['$http', function ($http) {
    return {
        getAllAuthors: function () {
            return $http.get("getAllAuthors")
                .then(function (response) {
                    return response.data;
                });
        },
        getAuthor: function (authorId) {
            return $http.get("getAuthorById/" + authorId)
                .then(function (response) {
                    return response.data;
                });
        }
    }
}]);