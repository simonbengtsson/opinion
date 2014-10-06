
var app = angular.module('hage', ['ui.bootstrap', 'ngRoute']);

app.constant('API_URL', 'http://localhost:8080/Hage-DAT076/api');

app.config(function($routeProvider, $locationProvider) {
  $routeProvider
   .when('/:profileId', {
    templateUrl: 'Hage-DAT076/partials/profile.html',
    controller: 'ProfileCtrl'
  })
  .when('/', {
    templateUrl: 'Hage-DAT076/partials/posts.html',
    controller: 'PostCtrl'
  });

  // configure html5 to get links working on jsfiddle
  $locationProvider.html5Mode(true);
});

app.controller('PostCtrl', ['$scope', '$http', 'API_URL', '$modal', function($scope, $http, API_URL, $modal) {
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

app.controller('ProfileCtrl', ['$scope', '$http', 'API_URL', '$modal', function($scope, $http, API_URL, $modal) {
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