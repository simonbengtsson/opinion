var app = angular.module('hage');

app.service('NetworkService', ['$http', 'API_URL', function ($http, API_URL) {

    var dummyUser = {
        username: "adalove",
        first_name: 'Ada',
        last_name: 'Lovelace',
        description: 'This is a personal description',
        posts: [],
        picture: 'http://cpuboss.com/blog/wp-content/uploads/2013/10/Wikipedia_ada-lovelace_FullyC_001.jpg'
    };

    var dummyPosts = [
        {text: 'Long long long long long long long long long long long long long long long long long long long long long long long hate text for testing layout for long(er) text', hatingUsers: [dummyUser], author: dummyUser},
        {text: "BILJETTKONTROLLANTER >:((((", hatingUsers: [], author: dummyUser, picture: 'http://www.nhs.uk/Conditions/stress-anxiety-depression/PublishingImages/E%20to%20I/expert-tips-on-child-anger_364x200_107668795.jpg'},
        {text: "When you change to autumn jacket and forget your västtrafikkort in your summer jacket... at home", hatingUsers: [], author: dummyUser},
        {text: "Hello world! :D ..... not.", hatingUsers: [], author: dummyUser}
    ];

    dummyUser.posts = dummyPosts;

    // Temporary. Returns a dummy promise which mocks a real api result.
    var dummyPromise = function(data) {
        return {
            success: function(func) {
                func(data);
            },
            fail: function(func) {
                func('Tmp error message');
            }
        }
    };

    // Posts

    this.getPosts = function () {
        return dummyPromise(dummyPosts);
        //return $http.get(API_URL + '/posts');
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
        return dummyPromise("Success!");
        //return $http.post(API_URL + '/posts/');
    };

    // Users

    this.getUser = function (user) {
        return dummyPromise(dummyUser);
        //return $http.get(API_URL + '/users/' + user.id);
    };

    this.updateUser = function (user) {
        return dummyPromise("Success!");
        //return $http.put(API_URL + '/users/' + user.id, user);
    };

    // Hates

    this.createHate = function(post) {
        return dummyPromise("Success!");
        //return $http.post(API_URL + '/hate/' + post.id);
    };

    this.deleteHate = function(post) {
        return dummyPromise("Success!");
        //return $http.delete(API_URL + '/hate/' + post.id);
    };

}]);