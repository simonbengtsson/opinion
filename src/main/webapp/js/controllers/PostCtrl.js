var app = angular.module('hage');

app.controller('PostCtrl', ['$scope', 'NetworkService', 'ModelService', function ($scope, network, model) {
        
        $scope.featuredUsers = [];
        
        network.getFeaturedUsers().success(function(res) {
            $scope.featuredUsers = res;
        });

}]);