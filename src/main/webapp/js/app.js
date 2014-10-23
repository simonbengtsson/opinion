var app = angular.module('hage', ['ui.bootstrap', 'ngRoute', 'ngSanitize', 'monospaced.elastic', 'angularMoment', 'infinite-scroll']);

app.constant('API_URL', 'http://localhost:8080/api');

app.config(['$routeProvider', '$locationProvider',
    function ($routeProvider) {
        $routeProvider
                .when('/hashtags/:hashtag', {
                    templateUrl: 'partials/posts.html',
                    controller: 'PostCtrl'
                })
                .when('/search/:searchTerm', {
                    templateUrl: 'partials/posts.html',
                    controller: 'PostCtrl'
                })
                .when('/:username', {
                    templateUrl: 'partials/profile.html',
                    controller: 'ProfileCtrl'
                })
                .when('/', {
                    templateUrl: 'partials/posts.html',
                    controller: 'PostCtrl'
                })
                .otherwise({
                    templateUrl: 'partials/404.html',
                    redirectTo: '/404'
                });
    }
]);