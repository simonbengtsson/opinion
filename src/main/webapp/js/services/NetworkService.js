var app = angular.module('hage');

app.service('NetworkService', ['$http', 'API_URL', 'ModelService', function ($http, API_URL, model) {

        var dummyUser = {
            username: "adalove",
            first_name: 'Ada',
            last_name: 'Lovelace',
            description: 'This is a personal description',
            picture: 'https://lh4.googleusercontent.com/-q-IPv9Ub8eY/AAAAAAAAAAI/AAAAAAAAAb4/36Ea1HIRW0c/photo.jpg'
        };
        
        var dummyUser2 = {
            username: "test123",
            first_name: 'Testare',
            last_name: 'TestTest',
            description: 'This is a personal test description',
            picture: 'http://www.horizon-properties.com/assets/test-user-7bc7f39edf559e62535e37437b232f46.png'
        };
        
        var dummyPosts = [
            {
                text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
                hatingUsers: [angular.copy(dummyUser2)],
                author: angular.copy(dummyUser),
                time: new Date(2013, 2, 1, 1, 10),
                comments: [{id: 1, author: angular.copy(dummyUser2), text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", time: new Date()}]
            },
            {
                text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim adminse incididunt ut labore incididunt ut labo.',
                hatingUsers: [angular.copy(dummyUser), angular.copy(dummyUser2)],
                author: angular.copy(dummyUser),
                time: new Date(2014, 2, 1, 1, 10),
                comments: [{id: 1, author: angular.copy(dummyUser2), text: "Test comment", time: new Date()}]
            },
            {
                text: "BILJETTKONTROLLANTER >:((((",
                hatingUsers: [],
                author: angular.copy(dummyUser),
                time: new Date(),
                picture: 'http://www.nhs.uk/Conditions/stress-anxiety-depression/PublishingImages/E%20to%20I/expert-tips-on-child-anger_364x200_107668795.jpg',
                comments: []
            },
            {
                text: "When you change to autumn jacket and forget your västtrafikkort in your summer jacket... at home",
                hatingUsers: [],
                time: new Date(),
                author: angular.copy(dummyUser),
                comments: []
            },
            {
                text: "Hello world! :D ..... not.",
                hatingUsers: [],
                time: new Date(),
                author: angular.copy(dummyUser),
                comments: []
            }
        ];
        
        var dummyPosts2 = [
            {
                text: 'Test test test test test test test test',
                hatingUsers: [angular.copy(dummyUser)],
                time: new Date(),
                author: angular.copy(dummyUser2),
                comments: [{id: 2, author: angular.copy(dummyUser), text: "Test tes test"}]
            }
        ];
        
        dummyUser.posts = dummyPosts;
        dummyUser2.posts = dummyPosts2;

        // Temporary. Returns a dummy promise which mocks a real api result.
        var dummyPromise = function (data) {
            return {
                success: function (func) {
                    func(data);
                },
                fail: function (func) {
                    func('Tmp error message');
                }
            };
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
        
        this.getLoggedInUser = function (username) {
            return dummyPromise(dummyUser);
            //return $http.get(API_URL + '/users/' + user.id);
        };
        
        this.getFeaturedUsers = function (username) {
            return dummyPromise([dummyUser, dummyUser2]);
            //return $http.get(API_URL + '/users/' + user.id);
        };

        this.getUser = function (username) {
            if (username === 'adalove') {
                return dummyPromise(dummyUser);
            } else if(username === 'test123') {
                return dummyPromise(dummyUser2);
            } else {
                console.log(username);
            }
                
            //return $http.get(API_URL + '/users/' + user.id);
        };

        this.updateUser = function (user) {
            return dummyPromise("Success!");
            //return $http.put(API_URL + '/users/' + user.id, user);
        };

        // Hates

        this.createHate = function (post) {
            return dummyPromise("Success!");
            //return $http.post(API_URL + '/hate/' + post.id);
        };

        this.deleteHate = function (post) {
            return dummyPromise("Success!");
            //return $http.delete(API_URL + '/hate/' + post.id);
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
            //return $http.post(API_URL + '/hate/' + post.id + '/comments/');
        };

        this.deleteHate = function (post, comment) {
            return dummyPromise("Success!");
            return $http.delete(API_URL + '/hate/' + post.id + '/comments/' + comment.id);
        };

    }]);