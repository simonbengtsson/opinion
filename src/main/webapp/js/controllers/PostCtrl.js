var app = angular.module('opinion');

app.controller('PostCtrl', ['$scope', 'NetworkService', 'ModelService', '$location', '$timeout', '$routeParams',
    function ($scope, network, model, $location, $timeout, $routeParams) {

        $scope.loadingPosts = false;
        $scope.postType = 'global';
        $scope.loadingError = '';
        $scope.searchTerm = '';
        model.posts = [];

        if ($routeParams.hashtag) {
            $scope.searchTerm = '#' + $routeParams.hashtag;
            network.getPosts().then(function (res) {
                model.posts = res.data;
            });
        } else {
            network.getPosts().then(function (res) {
                if (res.data.length === 0) {
                    $scope.loadingError = 'No opinions';
                }
                model.posts = res.data;
            });
        }

        network.getFeaturedUsers().then(function (res) {
            model.featuredUsers = res.data;
        });

        network.getTrendingHashtags().then(function (res) {
            model.trendingHashtags = res.data;
        });

        $scope.changePostType = function (type) {
            if(type === 'following' && !$scope.isLoggedIn()) {
                return;
            }
            $scope.postType = type;
            model.posts = [];
            $scope.loadMorePosts();
        };

        $scope.loadMorePosts = function () {
            $scope.loadingError = '';
            if ($scope.loadingPosts)
                return;
            $scope.loadingPosts = true;

            if ($scope.postType === 'local') {
                navigator.geolocation.getCurrentPosition(function (geo) {
                    var params = {
                        type: 'local',
                        lat: geo.coords.latitude,
                        lon: geo.coords.longitude
                    };
                    console.log(params);
                    network.getPosts(params).then(function (res) {
                        $timeout(function () {
                            if (res.data.length === 0) {
                                $scope.loadingError = 'No more posts';
                            }
                            model.posts = model.posts.concat(res.data);
                            $scope.loadingPosts = false;
                        });
                    }, function() {
                        $scope.loadingPosts = false;
                        $scope.loadingError = "Couldn't load posts";
                    });
                }, function () {
                    $timeout(function () {
                        $scope.loadingError = "Couldn't load local posts";
                        $scope.loadingPosts = false;
                    });
                });
            } else {
                var params = {
                    type: $scope.postType
                };
                network.getPosts(params).then(function (res) {
                    if (res.data.length === 0) {
                        $scope.loadingError = 'No opinions';
                    }
                    model.posts = model.posts.concat(res.data);
                    $scope.loadingPosts = false;
                });
            }
        };

    }
]);