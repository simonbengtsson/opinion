var app = angular.module('hage');

app.controller('MainCtrl', ['$scope', 'ModelService', 'NetworkService', '$http', '$modal', '$location', function ($scope, model, network, $http, $modal, $location) {

    $scope.model = model;
    $scope.updating = true;

    network.getPosts().success(function (data) {
        model.posts = data;
        $scope.updating = false;
    });
    
    network.getLoggedInUser().success(function (data) {
        model.user = data;
    });


    $scope.openCreateModal = function () {
        var mi = $modal.open({
            templateUrl: 'partials/create-modal.html',
            controller: function ($scope) {
                $scope.create = function () {
                    network.createPost($scope.post).success(function(post) {
                        $scope.$close(post);
                    });
                };
            }
        });

        mi.result.then(function (res) {
            model.posts.unshift(res);
        });
    };

    $scope.goToProfile = function () {
        if (model.user) {
            $location.path(model.user.username);
        } else {
            $modal.open({
                templateUrl: 'partials/login-modal.html'
            });
        }
    };

}]);