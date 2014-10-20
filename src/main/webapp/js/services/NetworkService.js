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

        var dummyUser2 = {
            username: "test123",
            first_name: 'Testare',
            last_name: 'TestTest',
            description: 'This is a personal test description',
            posts: [],
            picture: 'http://www.horizon-properties.com/assets/test-user-7bc7f39edf559e62535e37437b232f46.png'
        };
        
        var dummyPosts = [
            {text: 'Long long long long long long long long long hate text for testing layout for long(er) text', 
                hatingUsers: [dummyUser, dummyUser2], 
                author: dummyUser, 
                comments: [{id: 1, author: {username: 'test123'}, text: "Test comment"}]},
            {text: "BILJETTKONTROLLANTER >:((((", 
                hatingUsers: [], 
                author: dummyUser, 
                picture: 'http://www.nhs.uk/Conditions/stress-anxiety-depression/PublishingImages/E%20to%20I/expert-tips-on-child-anger_364x200_107668795.jpg', 
                comments: []},
            {text: "When you change to autumn jacket and forget your västtrafikkort in your summer jacket... at home", 
                hatingUsers: [], 
                author: dummyUser, 
                comments: []},
            {text: "Hello world! :D ..... not.", 
                hatingUsers: [], 
                author: dummyUser, 
                comments: []}
        ];
        
        var dummyPosts2 = [
            {text: 'Test test test test test test test test', 
                hatingUsers: [dummyUser],
                author: dummyUser2,
                comments: [{id: 2, author: {username: 'adalove'}, text: "Test tes test"}]},
            {text: 'Hate hage... ', 
                hatingUsers: [],
                author: dummyUser2,
                comments: [{id: 3, author: {username: 'testuser'}, text: "Test tes test"}]},
            {text: 'Hate all!!', 
                hatingUsers: [],
                author: dummyUser2,
                comments: [{id: 4, author: {username: 'testuser'}, text: "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim."}]},
            {text: 'Test test tes. And test test test.', 
                hatingUsers: [dummyUser],
                author: dummyUser2,
                comments: [{id: 5, author: {username: 'adalove'}, text: "Test 123 123 test"}]},
            {text: 'test test tesssttststststst', 
                hatingUsers: [],
                author: dummyUser2,
                comments: []} 
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

        this.getUser = function (username) {
            if(username === 'adalove')
                return dummyPromise(dummyUser);
            else
                return dummyPromise(dummyUser2);
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

        // Hates

        this.createComment = function (post, comment) {
            return dummyPromise("Success!");
            //return $http.post(API_URL + '/hate/' + post.id + '/comments/');
        };

        this.deleteHate = function (post, comment) {
            return dummyPromise("Success!");
            return $http.delete(API_URL + '/hate/' + post.id + '/comments/' + comment.id);
        };

    }]);