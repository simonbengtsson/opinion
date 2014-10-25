var app = angular.module('hage', ['ui.bootstrap', 'ngRoute', 'ngSanitize', 'monospaced.elastic', 'angularMoment', 'infinite-scroll', 'angularFileUpload']);

app.constant('BASE_URL', '/');
app.constant('API_URL', '' + '/api');

app.config(['$routeProvider', '$locationProvider',
    function ($routeProvider, $locationProvider) {
        $routeProvider
            .when('/hashtags/:hashtag', {
                templateUrl: 'partials/posts.html',
                controller: 'PostCtrl'
            })
            .when('/404', {
                templateUrl: 'partials/404.html',
            })
            .when('/search/:searchTerm', {
                templateUrl: 'partials/posts.html',
                controller: 'PostCtrl'
            })
            .when('/:username', {
                templateUrl: 'partials/profile.html',
                controller: 'ProfileCtrl',
                resolve: {
                    user: ['ModelService', 'NetworkService', '$q', '$route', '$location',
                        function (model, network, $q, $route, $location) {
                            var deferred = $q.defer();

                            var username = $route.current.params.username;

                            var post = {
                                text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. #awesome #test',
                                hatingUsers: [], time: new Date(2013, 2, 1, 1, 10), comments: []
                            };

                            var user = {
                                username: username, followers: [], posts: [], following: [], firstName: 'First', lastName: 'Lastname',
                                description: 'This is a personal test description that is way to short for showing any information what so ever that is relevant.',
                                picture: 'http://www.horizon-properties.com/assets/test-user-7bc7f39edf559e62535e37437b232f46.png'
                            };

                            post.author = angular.copy(user);
                            user.posts.push(post);


                            if (model.user && username === model.user.username) {
                                deferred.resolve(model.user);
                            } else {
                                network.getUser(username).then(function (res) {
                                    deferred.resolve(res.data);
                                }, function (res) {
                                    if (username === 'adalove') {
                                        deferred.resolve(user);
                                    } else {
                                        $location.path('/404');
                                        $location.replace();
                                        deferred.reject(res);
                                    }
                                });
                            }

                            return deferred.promise;
                        }
                    ]
                }
            })
            .when('/', {
                templateUrl: 'partials/posts.html',
                controller: 'PostCtrl'
            })
            .otherwise({
                templateUrl: 'partials/404.html'
            });
    }
]);