var app = angular.module('hage');

app.controller('ProfileCtrl', ['$scope', '$routeParams', '$http', 'API_URL', '$modal', 'ModelService', 'NetworkService', function ($scope, $routeParams, $http, API_URL, $modal, model, network) { 
        
        $scope.model.user.username = $routeParams.username;
        console.log($routeParams.username);
        network.getUser($routeParams.username).success(function(user){
            $scope.user = user;
        });
 
  }]);
