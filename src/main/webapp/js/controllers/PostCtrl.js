var app = angular.module('hage');

app.controller('PostCtrl', ['$scope', 'NetworkService', 'ModelService', '$location', '$timeout', '$routeParams',
    function ($scope, network, model, $location, $timeout, $routeParams) {

        $scope.loadingPosts = false;
        $scope.postType = 'world';
        $scope.searchTerm = '';
        model.posts = [];
        
        if($routeParams.hashtag) {
            $scope.searchTerm = '#' + $routeParams.hashtag;
            network.getPosts().then(function (data) {
                model.posts = data; 
            });
        } else if($routeParams.searchTerm) {
            $scope.searchTerm = $routeParams.searchTerm;
            network.getPosts().then(function (data) {
                model.posts = data;
            });
        } else {
            network.getPosts().then(function (data) {
                model.posts = data;
            });
        }

        var page = 0;

        network.getFeaturedUsers().then(function (res) {
            model.featuredUsers = res;
        });

        network.getTrendingHashtags().then(function (res) {
            model.trendingHashtags = res;
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
                    network.getPosts(page, $scope.postType, geo.coords).then(function (res) {
                        $timeout(function () {
                            model.posts = model.posts.concat(res);
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
                network.getPosts(page, $scope.postType).then(function (res) {
                    model.posts = model.posts.concat(res);
                    page++;
                    $scope.loadingPosts = false;
                });
            }
        };
    }
]);