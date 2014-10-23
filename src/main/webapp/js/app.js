var app = angular.module('hage', ['ui.bootstrap', 'ngRoute', 'ngSanitize', 'monospaced.elastic', 'angularMoment', 'infinite-scroll']);

app.constant('API_URL', 'http://localhost:8080/api');

app.config(['$routeProvider', '$locationProvider',
    function ($routeProvider, $locationProvider) {
        $routeProvider
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

app.filter('hashTags', function () {
    return function (input) {
        return input.replace(/#(\S+)/g, '<a href="http://twitter.com/hashtag/$1">#$1</a>');
    };
});
