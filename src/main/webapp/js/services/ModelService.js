var app = angular.module('opinion');

/**
 * A service that keeps the model that is needed across the app.
 */
app.service('ModelService', [
    function () {

        this.posts = [];
        this.trendingHashtags = [];
        this.featuredUsers = [];
        this.user = null;

    }
]);