var app = angular.module('hage');

app.controller('PostCtrl', ['$scope', 'NetworkService', 'ModelService', '$timeout', function ($scope, network, model, $timeout) {

        $scope.featuredUsers = [];
        $scope.loadingPosts = false;
        $scope.postTypes = 'world';
        
        if(location.href.indexOf('hage') !== -1) {
            location.href = '/';
        }

        network.getFeaturedUsers().success(function (res) {
            $scope.featuredUsers = res;
        });

        $scope.loadMorePosts = function () {
            if ($scope.loadingPosts) return;
            $scope.loadingPosts = true;
            
            $timeout(function() {
                model.posts.push({
                    text: 'Loading more number ' + model.posts.length,
                    hatingUsers: [],
                    author: {
                        username: "adalove",
                        firstName: 'Ada',
                        lastName: 'Lovelace',
                        description: 'This is a personal description that is pretty long, but not TLDR',
                        picture: 'https://lh4.googleusercontent.com/-q-IPv9Ub8eY/AAAAAAAAAAI/AAAAAAAAAb4/36Ea1HIRW0c/photo.jpg'
                    },
                    time: new Date(2013, 2, 1, 1, 10),
                    comments: []
                });
                model.posts.push({
                    text: 'Loading more number ' + model.posts.length,
                    hatingUsers: [],
                    author: {
                        username: "adalove",
                        firstName: 'Ada',
                        lastName: 'Lovelace',
                        description: 'This is a personal description that is pretty long, but not TLDR',
                        picture: 'https://lh4.googleusercontent.com/-q-IPv9Ub8eY/AAAAAAAAAAI/AAAAAAAAAb4/36Ea1HIRW0c/photo.jpg'
                    },
                    time: new Date(2014, 2, 1, 1, 10),
                    comments: []
                });
                $scope.loadingPosts = false;
            }, 1000);
        };
    }]);