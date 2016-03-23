mainModule.service('BookService', ['$http', function ($http) {
    return {
        getAllBooks: function () {
            return $http.get("getAllBooks")
                .then(function (response) {
                    return response.data;
                });
        },

        getBook: function (bookId) {
            return $http.get("getBookById/" + bookId)
                .then(function (response) {
                    return response.data;
                });
        },

        getBooksByAuthor: function (authorId) {
            return $http.get("getBooksByAuthor/" + authorId)
                .then(function (response) {
                    return response.data;
                });
        }

    }
}]);