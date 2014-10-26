var app = angular.module('opinion', ['ui.bootstrap', 'ngRoute', 'ngSanitize', 'monospaced.elastic', 'angularMoment', 'infinite-scroll', 'angularFileUpload']);

app.constant('BASE_URL', '/');
app.constant('API_URL', '' + '/api');

app.config(['$routeProvider',
    function ($routeProvider) {
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
                            
                            // Checking if the user exist before going to it's profile page.
                            // If not found, load a 404 page
                            
                            var deferred = $q.defer();

                            var username = $route.current.params.username;
                            
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