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

app.controller('MainCtrl', ['$scope', 'Model', 'Network', '$http', '$modal', 'API_URL', function ($scope, model, network, $http, $modal, API_URL) {

    $scope.openCreateModal = function () {
        var mi = $modal.open({
            templateUrl: 'partials/create-modal.html',
            controller: function ($scope) {
                $scope.post = {};
                $scope.create = function () {
                    $scope.$close($scope.post);
                }
            }
        });

        mi.result.then(function (res) {
            model.posts.push(res);
        });
    }
}]);

app.controller('PostCtrl', ['$scope', 'Model', function ($scope, model) {
    $scope.posts = model.posts;
}]);

app.controller('ProfileCtrl', ['$scope', '$http', 'API_URL', '$modal', function ($scope, $http, API_URL, $modal) {

}]);

app.service('Model', [function () {

    this.posts = [
        {content: 'lskdjf'}
    ];

}]);

app.service('Network', ['Model', '$http', 'API_URL', function (model, $http, API_URL) {

    this.posts = [
        {content: 'lskdjf'}
    ];

    this.getPosts = function () {
        return $http.get(API_URL + '/posts');
    };

}]);