var app = angular.module('opinion');

app.service('NetworkService', ['$http', 'API_URL', 'BASE_URL', 'ModelService', '$q', '$timeout',
    function ($http, API_URL, BASE_URL, model, $q, $timeout) {

        $http.defaults.headers.common.Authorization = localStorage.getItem('authKey');

        // Temporary. Returns a dummy promise which mocks a real api result.
        var dummyPromise = function (data) {
            var deferred = $q.defer();
            
            $timeout(function() {
                deferred.resolve({data: data});
            }, 500);
            
            return deferred.promise;
        };

        // Posts

        this.getPosts = function (params) {
            var def = $q.defer();
            $http({method: 'GET', params: params, url: API_URL + '/posts'}).then(function(res) {
                $timeout(function() {
                    def.resolve(res);
                }, 400);
            }, function(res) {
                def.reject(res);
            });
            return def.promise;
        };
        
        this.getUserPosts = function(username, to, from) {
            return $http.get(API_URL + '/users/' + username + '/posts');
        };

        this.updatePost = function (post) {
            return dummyPromise("Success!");
            //return $http.put(API_URL + '/posts/' + post.id, post);
        };

        this.deletePost = function (post) {
            return dummyPromise("Success!");
            //return $http.delete(API_URL + '/posts/' + post.id);
        };

        this.createPost = function (post) {
            return $http.post(API_URL + '/posts/', post);
        };

        // Users
        
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

        // Opinion

        this.opine = function (post) {
            return dummyPromise("Success!");
        };

        // Comments

        this.createComment = function (post, comment) {
            return $http.post(API_URL + '/posts/' + post.postId + '/comments', comment);
        };
        
        this.followUser = function (username){
            return $http.post(API_URL + '/users/' + username + '/followers');
        };

        this.unfollowUser = function (username){
            return $http.delete(API_URL + '/users/' + username + '/followers/me');
        };
        
        // Meta
        
        this.getTrendingHashtags = function() {
            return $http.get(API_URL + "/hashtags");
        };
        
        this.initTestData = function() {
            return $http.get(BASE_URL + 'seed-database');
        };

    }
]);
