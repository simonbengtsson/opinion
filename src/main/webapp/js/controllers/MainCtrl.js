var app = angular.module('hage');

app.controller('MainCtrl', ['$scope', 'ModelService', 'NetworkService', '$http', '$modal', '$location', function ($scope, model, network, $http, $modal, $location) {

    $scope.model = model;

    network.getPosts().success(function (data) {
        model.posts = data;
    });

    network.getUser().success(function (data) {
        model.user = data;
    });


    $scope.openCreateModal = function () {
        var mi = $modal.open({
            templateUrl: 'partials/create-modal.html',
            controller: function ($scope) {
                $scope.post = {
                    text: '',
                    picture: null,
                    hatingUsers: []
                };
                $scope.create = function () {
                    $scope.$close($scope.post);
                }
            }
        });

        mi.result.then(function (res) {
            model.posts.push(res);
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