var app = angular.module('opinion');

app.controller('PostCtrl', ['$scope', 'NetworkService', 'ModelService', '$location', '$timeout', '$routeParams',
    function ($scope, network, model, $location, $timeout, $routeParams) {

        $scope.loadingPosts = false;
        $scope.postType = 'world';
        $scope.searchTerm = '';
        model.posts = [];
        
        if ($routeParams.hashtag) {
            $scope.searchTerm = '#' + $routeParams.hashtag;
            network.getPosts().then(function (res) {
                model.posts = res.data;
            });
        } else if ($routeParams.searchTerm) {
            $scope.searchTerm = $routeParams.searchTerm;
            network.getPosts().then(function (res) {
                model.posts = res.data;
            });
        } else {
            network.getPosts().then(function (res) {
                model.posts = res.data;
            });
        }

        var page = 0;

        network.getFeaturedUsers().then(function (res) {
            var data = res.data;
            model.featuredUsers = data;
        });

        network.getTrendingHashtags().then(function (res) {
            model.trendingHashtags = res.data;
        });

        $scope.changePostType = function (type) {
            $scope.postType = type;
            page = 0;
            model.posts = [];
            $scope.loadMorePosts();
        };

        $scope.loadMorePosts = function () {
            $scope.loadingError = '';
            if ($scope.loadingPosts)
                return;
            $scope.loadingPosts = true;

            if ($scope.postType === 'city') {
                navigator.geolocation.getCurrentPosition(function (geo) {
                    var params = {
                        type: 'local',
                        lat: geo.latitude,
                        lon: geo.longitude
                    };
                    network.getPosts(params).then(function (res) {
                        $timeout(function () {
                            model.posts = model.posts.concat(res.data);
                            page++;
                            $scope.loadingPosts = false;
                        });
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
                network.getPosts().then(function (res) {
                    model.posts = model.posts.concat(res.data);
                    page++;
                    $scope.loadingPosts = false;
                });
            }
        };
        
    }
]);