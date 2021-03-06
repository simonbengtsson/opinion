var app = angular.module('opinion');

app.controller('PostCtrl', ['$scope', 'NetworkService', 'ModelService', '$location', '$timeout', '$routeParams',
    function ($scope, network, model, $location, $timeout, $routeParams) {

        $scope.loadingPosts = false;
        $scope.postType = 'global';
        $scope.loadingError = '';
        $scope.searchTerm = '';
        model.posts = [];

        if ($routeParams.hashtag) {
            $scope.postType = 'hashtag';
            $scope.searchTerm = '#' + $routeParams.hashtag;
        }

        network.getFeaturedUsers().then(function (res) {
            model.featuredUsers = res.data;
        });

        network.getTrendingHashtags().then(function (res) {
            model.trendingHashtags = res.data;
        });

        $scope.changePostType = function (type) {
            noMorePosts = false;
            if(type === 'following' && !$scope.isLoggedIn()) {
                return;
            }
            $scope.postType = type;
            model.posts = [];
            $scope.loadMorePosts();
        };
        
        var noMorePosts = false;

        $scope.loadMorePosts = function () {
            if ($scope.loadingPosts || noMorePosts)
                return;
            $scope.loadingError = '';
            $scope.loadingPosts = true;
            
            if ($scope.postType === 'local') {
                navigator.geolocation.getCurrentPosition(function (geo) {
                    var params = {
                        type: 'local',
                        lat: geo.coords.latitude,
                        lon: geo.coords.longitude
                    };
                    network.getPosts(params).then(function (res) {
                        $timeout(function () {
                            if (res.data.length === 0) {
                                noMorePosts = true;
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
                if($scope.postType === 'hashtag') {
                    params.hashtag = $routeParams.hashtag;
                }
                network.getPosts(params).then(function (res) {
                    if (res.data.length === 0) {
                        noMorePosts = true;
                        $scope.loadingError = 'No more opinions';
                    }
                    model.posts = model.posts.concat(res.data);
                    $scope.loadingPosts = false;
                });
            }
        };

    }
]);