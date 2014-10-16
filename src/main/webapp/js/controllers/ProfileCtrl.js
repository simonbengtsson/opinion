var app = angular.module('hage');

app.controller('ProfileCtrl', ['$scope', '$http', 'API_URL', '$modal', 'ModelService', function ($scope, $http, API_URL, $modal, model) {
    $scope.users = model.users;
}]);