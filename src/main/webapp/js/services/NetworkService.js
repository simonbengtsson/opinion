var app = angular.module('opinion');

app.service('NetworkService', ['$http', 'API_URL', 'BASE_URL', 'ModelService', '$q', '$timeout',
    function ($http, API_URL, BASE_URL, model, $q, $timeout) {

        // Include the api key in every request
        $http.defaults.headers.common.Authorization = localStorage.getItem('authKey');

        this.getPosts = function (params) {
            var def = $q.defer();
            $http({method: 'GET', params: params, url: API_URL + '/posts'}).then(function (res) {
                // Delay for giving the user feedback that new posts has been loaded
                $timeout(function () {
                    def.resolve(res);
                }, 400);
            }, function (res) {
                def.reject(res);
            });
            return def.promise;
        };

        this.getUserPosts = function (username, to, from) {
            return $http.get(API_URL + '/users/' + username + '/posts');
        };

        this.createPost = function (post) {
            return $http.post(API_URL + '/posts/', post);
        };

        this.getLoggedInUser = function () {
            return $http.get(API_URL + '/users/me');
        };

        this.getFeaturedUsers = function () {
            return $http.get(API_URL + '/users');
        };

        this.getUser = function (username) {
            return $http.get(API_URL + '/users/' + username);
        };

        this.updateUser = function (user) {
            return $http.put(API_URL + '/users/me', user);
        };

        // Not yet supported
        this.opine = function (post) {
            return $q.defer().promise;
        };

        this.createComment = function (post, comment) {
            return $http.post(API_URL + '/posts/' + post.postId + '/comments', comment);
        };

        this.followUser = function (username) {
            return $http.post(API_URL + '/users/' + username + '/followers');
        };

        this.unfollowUser = function (username) {
            return $http.delete(API_URL + '/users/' + username + '/followers/me');
        };

        this.getTrendingHashtags = function () {
            return $http.get(API_URL + "/hashtags");
        };

        this.initTestData = function () {
            return $http.get(BASE_URL + 'seed-database');
        };

    }
]);
