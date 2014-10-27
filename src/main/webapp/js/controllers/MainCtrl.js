var app = angular.module('opinion');

app.controller('MainCtrl', ['$scope', 'ModelService', 'NetworkService', '$http', '$modal', '$location',
    function ($scope, model, network, $http, $modal, $location) {

        $scope.model = model;

        network.getLoggedInUser().then(function (res) {
            model.user = res.data;
        });

        $scope.isLoggedIn = function () {
            return !!model.user;
        };

        $scope.openCreateModal = function () {
            var mi = $modal.open({
                templateUrl: 'partials/create-modal.html',
                controller: 'CreateModalCtrl'
            });

            // On close
            mi.result.then(function (post) {
                if (!model.posts) {
                    model.posts = [];
                }
                model.posts.unshift(post);
                if (!model.user.posts) {
                    model.user.posts = [];
                }
                model.user.posts.unshift(post);
            });
        };

        $scope.goToProfile = function () {
            $location.path(model.user.username);
        };

    }
]);