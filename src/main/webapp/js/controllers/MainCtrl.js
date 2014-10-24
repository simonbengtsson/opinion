var app = angular.module('hage');

app.controller('MainCtrl', ['$scope', 'ModelService', 'NetworkService', '$http', '$modal', '$location',
    function ($scope, model, network, $http, $modal, $location) {

        $scope.model = model;

        network.getLoggedInUser().then(function (data) {
            model.user = data;
        });

        $scope.openCreateModal = function () {
            var mi = $modal.open({
                templateUrl: 'partials/create-modal.html',
                controller: ['$scope', '$timeout', 'FileUploader', function ($scope, $timeout, FileUploader) {
                        
                        $scope.posMessage = '';
                        var coords = null;
                        
                        $scope.uploader = new FileUploader();
                        
                        $scope.$watch('localPost', function (newVal) {
                            $scope.posMessage = '';
                            if (newVal) {
                                $scope.posMessage = 'Loading...';
                                navigator.geolocation.getCurrentPosition(function (res) {
                                    $timeout(function() {
                                        coords = res.coords;
                                        $scope.posMessage = 'Position attached';
                                    });
                                }, function () {
                                    $timeout(function() {
                                        $scope.posMessage = 'Positioning failed';
                                        $scope.localPost = false;
                                    });
                                });
                            }
                        });

                        $scope.create = function () {
                            network.createPost($scope.post).then(function (post) {
                                $scope.$close(post);
                            });
                        };
                    }]
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

    }
]);