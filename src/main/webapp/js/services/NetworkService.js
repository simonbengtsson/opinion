var app = angular.module('opinion');

app.service('NetworkService', ['$http', 'API_URL', 'BASE_URL', 'ModelService', '$q', '$timeout',
    function ($http, API_URL, BASE_URL, model, $q, $timeout) {

        //console.log(localStorage.authKey);
        $http.defaults.headers.common.Authorization = localStorage.getItem('authKey');

        var dummyUser = {
            username: "adalove",
            followers: [],
            following: [],
            name: 'Ada Lovelace',
            description: 'This is a personal description that is pretty long, but not TLDR',
            picture: 'https://lh4.googleusercontent.com/-q-IPv9Ub8eY/AAAAAAAAAAI/AAAAAAAAAb4/36Ea1HIRW0c/photo.jpg'
        };
        
        var dummyUser2 = {
            username: "test123",
            followers: [dummyUser],
            following: [],
            name: 'Test Testare',
            description: 'This is a personal test description',
            picture: 'http://www.horizon-properties.com/assets/test-user-7bc7f39edf559e62535e37437b232f46.png'
        };
        
        dummyUser.following.push(angular.copy(dummyUser2));
        
        var dummyPosts = [
            {
                text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. #awesome #test',
                disagreeingUsers: [angular.copy(dummyUser2)],
                author: angular.copy(dummyUser),
                time: new Date(2013, 2, 1, 1, 10),
                comments: [{id: 1, author: angular.copy(dummyUser2), text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", time: new Date()}]
            },
            {
                text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim adminse incididunt ut labore incididunt ut labo.',
                disagreeingUsers: [angular.copy(dummyUser), angular.copy(dummyUser2)],
                author: angular.copy(dummyUser),
                time: new Date(2014, 2, 1, 1, 10),
                comments: [{id: 1, author: angular.copy(dummyUser2), text: "Test comment", time: new Date()}]
            },
            {
                text: "BILJETTKONTROLLANTER >:((((",
                disagreeingUsers: [],
                author: angular.copy(dummyUser),
                time: new Date(),
                picture: 'http://www.nhs.uk/Conditions/stress-anxiety-depression/PublishingImages/E%20to%20I/expert-tips-on-child-anger_364x200_107668795.jpg',
                comments: []
            },
            {
                text: "When you change to autumn jacket and forget your västtrafikkort in your summer jacket... at home",
                disagreeingUsers: [],
                time: new Date(),
                author: angular.copy(dummyUser),
                comments: []
            },
            {
                text: "Hello world! :D ..... not.",
                disagreeingUsers: [],
                time: new Date(),
                author: angular.copy(dummyUser),
                comments: []
            }
        ];
        
        var dummyPosts2 = [
            {
                text: 'Test test test test test test test test',
                disagreeingUsers: [angular.copy(dummyUser)],
                time: new Date(),
                author: angular.copy(dummyUser2),
                comments: [{id: 2, author: angular.copy(dummyUser), text: "Test tes test"}]
            }
        ];
        
        dummyUser.posts = dummyPosts;
        dummyUser2.posts = dummyPosts2;

        // Temporary. Returns a dummy promise which mocks a real api result.
        var dummyPromise = function (data) {
            var deferred = $q.defer();
            
            $timeout(function() {
                deferred.resolve({data: data});
            }, 500);
            
            return deferred.promise;
        };

        // Posts

        this.getPosts = function (page, type, coords) {
            return dummyPromise(angular.copy(dummyPosts));
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
            var d = $q.defer();
            $http.post(API_URL + '/posts/', post).then(function(res) {
                if(!res.data.author) {
                    res.data.author = model.user;
                }
                d.resolve(res);
            }, function(res) {
                d.reject(res);
            });
            return d.promise;
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
            var c = {
                id: 15,
                text: comment,
                time: new Date(),
                author: model.user
            };
            return dummyPromise(c);
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
