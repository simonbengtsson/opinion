var app = angular.module('hage');

app.controller('ProfileCtrl', ['$scope', '$http', 'API_URL', '$modal', 'ModelService', function ($scope, $routeParams, $http, API_URL, $modal, model) { 
        
        $scope.model.user.username = $routeParams.username;
 
  }]);
