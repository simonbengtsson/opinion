var app = angular.module('hage');

app.controller('MainCtrl', ['$scope', 'ModelService', 'NetworkService', '$http', '$modal', '$location',
    function ($scope, model, network, $http, $modal, $location) {

        $scope.model = model;
        
        network.initTestData().then(function(res) {
            console.log('success')
        });

        network.getLoggedInUser().then(function (data) {
            data = data.data;
            if(!data.picture) {
                data.picture = "assets/no-picture.png";
            }
            model.user = data;
        });

        $scope.openCreateModal = function () {
            var mi = $modal.open({
                templateUrl: 'partials/create-modal.html',
                controller: ['$scope', '$timeout', '$upload', function ($scope, $timeout, $upload) {
                        
                        $scope.posMessage = '';
                        $scope.post = {};
                        var coords = null;
                        
                        $scope.onFileSelect = function(file) {
                            $scope.upload = $upload.upload({
                                url: '/uploads',
                                //headers: {'Authenication': 'header-value'},
                                file: file
                            }).success(function (data, status, headers, config) {
                                $scope.post.picture = '/uploads/' + data.filename;
                            });
                        };
                        
                        $scope.removeImage = function() {
                            angular.element('.drop-zone :file')[0].value = null;
                            $scope.post.picture = '';
                        };
                        
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