var app = angular.module('hage');

app.controller('PostCtrl', ['$scope', 'ModelService', function ($scope, model) {
    $scope.posts = model.posts;
}]);