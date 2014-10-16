var app = angular.module('hage');

app.controller('MainCtrl', ['$scope', 'ModelService', 'NetworkService', '$http', '$modal', function ($scope, model, network, $http, $modal) {

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
                $scope.post = {};
                $scope.create = function () {
                    $scope.$close($scope.post);
                }
            }
        });

        mi.result.then(function (res) {
            model.posts.push(res);
        });
    }
}]);