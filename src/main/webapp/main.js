
var app = angular.module('hage', ['ui.bootstrap']);

app.constant('API_URL', 'http://localhost:8080/Hage-DAT076/api');

app.controller('MainCtrl', ['$scope', '$http', 'API_URL', '$modal', function($scope, $http, API_URL, $modal) {
    $scope.posts = [];
    $http.get(API_URL + '/posts').success(function(res) {
        $scope.posts = res;
    });
    
    $scope.openCreateModal = function() {
        var mi = $modal.open({
            templateUrl: 'partials/create-modal.html',
            controller: function($scope, $modalInstance) {
                $scope.post = {};
                $scope.create = function() {
                    $scope.$close($scope.post);
                }
            }
        });
        
        mi.result.then(function(res) {
            $scope.posts.push(res);
        });
    }
    
    
    
}]);