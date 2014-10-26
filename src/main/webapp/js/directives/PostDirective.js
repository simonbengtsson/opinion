var app = angular.module('opinion');

app.directive('opinionPost', ['ModelService', 'NetworkService', function (model, network) {

        return {
            restrict: 'E',
            scope: {
                post: '=opinionPost'
            },
            templateUrl: 'partials/post-directive.html',
            link: function (scope) {
                scope.model = model;
                
                var busy = false;

                scope.opine = function (post) {
                    if(busy) return;
                    busy = true;
                };
 
                scope.isPostLong = function (post) {
                    return post.text.length > 180;
                };

                scope.comment = function () {
                    network.createComment(scope.post, {text: scope.newComment}).then(function (res) {
                        scope.post.comments.push(res.data);
                        scope.newComment = '';
                    });
                };
            }
        };

    }
]);