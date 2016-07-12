mainModule.service('CategoryService', ['$http', function ($http) {
    return {
        getRootCategories: function () {
            return $http.get("getRootCategories")
                .then(function (response) {
                    return response.data;
                });
        },

        getBookCategories: function (bookId) {
            return $http.get("getBookCategories/" + bookId)
                .then(function (response) {
                    return response.data;
                });
        },

        getCategoryWithChildren: function (categoryId) {
            return $http.get("getCategoryWithChildren/" + categoryId)
                .then(function (response) {
                    return response.data;
                });
        },

        getCategoryWithParent: function (categoryId) {
            return $http.get("getCategoryWithParent/" + categoryId)
                .then(function (response) {
                    return response.data;
                });
        }

    }
}]);