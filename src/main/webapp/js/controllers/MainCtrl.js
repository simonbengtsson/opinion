var app = angular.module('hage');

app.controller('MainCtrl', ['$scope', 'ModelService', 'NetworkService', '$http', '$modal', 'API_URL', function ($scope, model, network, $http, $modal, API_URL) {

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