var app = angular.module('hage');

app.service('NetworkService', ['$http', 'API_URL', function ($http, API_URL) {

    // Posts
    this.getPosts = function () {
        return $http.get(API_URL + '/posts');
    };

    this.updatePost = function (post) {
        return $http.put(API_URL + '/posts/' + post.id, post);
    };

    this.deletePost = function (post) {
        return $http.delete(API_URL + '/posts/' + post.id);
    };

    this.createPost = function (post) {
        return $http.post(API_URL + '/posts/');
    };

    // Users
    this.getUser = function (user) {
        return $http.get(API_URL + '/users/' + user.id);
    };

    this.updateUser = function (user) {
        return $http.put(API_URL + '/users/' + user.id, user);
    };

    // Hates
    this.createHate = function(post) {
        return $http.post(API_URL + '/hate/' + post.id);
    };

    this.deleteHate = function(postId) {
        return $http.delete(API_URL + '/hate/' + post.id);
    };

}]);