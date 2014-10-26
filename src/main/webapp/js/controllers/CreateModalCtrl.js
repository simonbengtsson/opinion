var app = angular.module('opinion');

app.controller('CreateModalCtrl', ['$scope', '$timeout', '$upload', 'NetworkService',
    function ($scope, $timeout, $upload, network) {

        $scope.posMessage = '';
        $scope.post = {};

        $scope.onFileSelect = function (file) {
            $scope.upload = $upload.upload({
                url: '/uploads',
                file: file
            }).success(function (data) {
                $scope.post.picture = '/uploads/' + data.filename;
            });
        };

        $scope.removeImage = function () {
            // Fix for selecting the same file twice in a row
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
                    $timeout(function () {
                        $scope.post.lat = res.coords.latitude;
                        $scope.post.lon = res.coords.longitude;
                        $scope.posMessage = 'Position attached';
                    });
                }, function () {
                    $timeout(function () {
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
    }
]);