var app = angular.module('hage', ['ui.bootstrap', 'ngRoute']);

app.constant('API_URL', 'http://localhost:8080/Hage-DAT076/api');

app.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $routeProvider
        .when('/profile', {
            templateUrl: 'partials/profile.html',
            controller: 'ProfileCtrl'
        })
        .when('/', {
            templateUrl: 'partials/posts.html',
            controller: 'PostCtrl'
        });

}]);