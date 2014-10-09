var app = angular.module('hage');

app.controller('PostCtrl', ['$scope', 'NetworkService', 'ModelService', function ($scope, network, model) {
    $scope.posts = model.posts;

    $scope.$watch('posts', function(newPost, oldPost) {
        if(newPost != oldPost) {
            //network.updatePost(newPost);
        }
    }, true);

    $scope.hate = function(post) {
        post.hates += 1;
    }
}]);