var app = angular.module('opinion');

app.controller('MainCtrl', ['$scope', 'ModelService', 'NetworkService', '$http', '$modal', '$location',
    function ($scope, model, network, $http, $modal, $location) {

        $scope.model = model;

        network.initTestData().then(function(res) {
            console.log('Seeded!');
        });

        network.getLoggedInUser().then(function (res) {
            model.user = res.data;
        });
        
        $scope.isLoggedIn = function() {
            return !!model.user;
        };
        
        $scope.openCreateModal = function () {
            var mi = $modal.open({
                templateUrl: 'partials/create-modal.html',
                controller: ['$scope', '$timeout', '$upload', function ($scope, $timeout, $upload) {
                        
                        $scope.posMessage = '';
                        $scope.post = {};

                        $scope.onFileSelect = function(file) {
                            $scope.upload = $upload.upload({
                                url: '/uploads',
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
                            delete $scope.post.lat;
                            delete $scope.post.lon;

                            if (newVal) {
                                $scope.posMessage = 'Loading...';
                                navigator.geolocation.getCurrentPosition(function (res) {
                                    $timeout(function() {
                                        $scope.post.lat = res.coords.latitude;
                                        $scope.post.lon = res.coords.longitude;
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
                            network.createPost($scope.post).then(function (res) {
                                $scope.$close(res.data);
                            });
                        };
                    }]
            });

            mi.result.then(function (post) {
                if(!model.posts) {
                    model.posts = [];
                }
                model.posts.unshift(post);
                if(!model.user.posts) {
                    model.user.posts = [];
                }
                model.user.posts.unshift(post);
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