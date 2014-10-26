var app = angular.module('opinion');

app.controller('ProfileCtrl', ['$scope', '$routeParams', '$modal', 'ModelService', 'NetworkService', '$route', 'user',
    function ($scope, $routeParams, $modal, model, network, $route, user) {
        
        $scope.user = user;

        $scope.logout = function () {
            model.user = null;
            localStorage.removeItem('authKey');
        };

        $scope.viewingSelf = function () {
            return $scope.user && model.user && model.user.username === $scope.user.username;
        };
        
        $scope.follow = function() {
            if($scope.user.isFollowing) {
                network.unfollowUser($scope.user.username).then(function (res) {
                    $scope.user.isFollowing = false;
                });
            } else {
                network.followUser($scope.user.username).then(function (res) {
                    $scope.user.isFollowing = true;
                });
            }
        };

        $scope.openEditModal = function () {
            var mi = $modal.open({
                templateUrl: 'partials/edit-profile-modal.html',
                controller: function ($scope, $modalInstance, user) {
                    $scope.user = angular.copy(user);
                    $scope.save = function () {
                        network.updateUser($scope.user).then(function (res) {
                            $scope.$close(res.data);
                        });
                    };
                },
                resolve: {
                    user: function () {
                        return $scope.user;
                    }
                }
            });

            mi.result.then(function (user) {
                model.user = user;
                $scope.user = user;
            });
        };

    }
]);
