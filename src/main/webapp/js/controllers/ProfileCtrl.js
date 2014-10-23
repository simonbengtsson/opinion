var app = angular.module('hage');

app.controller('ProfileCtrl', ['$scope', '$routeParams', '$http', 'API_URL', '$modal', 'ModelService', 'NetworkService', '$route', function ($scope, $routeParams, $http, API_URL, $modal, model, network, $route) { 
        
        network.getUser($routeParams.username).success(function(user){
            $scope.user = user;
        });
        
        $scope.logout = function() {
            model.user = null;
            localStorage.removeItem('authKey');
        };
        
        $scope.viewingSelf = function() {
            return model.user && model.user.username === $scope.user.username;
        };
        
        $scope.followUser = function() {
            //Add to following list.
            console.log("I want to follow you " + $scope.user.username);
            network.followUser($scope.user.username).success(function (res) {
           
            });
        };
        
        
        $scope.openEditModal = function () {
            $modal.open({
                templateUrl: 'partials/edit-profile-modal.html',
                controller: function ($scope, $modalInstance, user) {
                    $scope.user = angular.copy(user);
                    $scope.save = function () {
                        network.updateUser($scope.user).success(function () {
                            $route.reload();
                            $scope.$close();
                        });
                    };
                },
                resolve: {
                    user: function () {
                        return $scope.user;
                    }
                }
            });
        };
 
  }]);
