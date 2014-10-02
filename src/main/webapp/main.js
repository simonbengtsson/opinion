
var app = angular.module('hage', []);

app.constant('API_URL', 'http://localhost:8080/hage-1.0-SNAPSHOT/api');

app.controller('MainCtrl', ['$scope', '$http', 'API_URL', function($scope, $http, API_URL) {
    $scope.posts = [];
    $http.get(API_URL + '/posts').success(function(res) {
        $scope.posts = res;
    });
}]);