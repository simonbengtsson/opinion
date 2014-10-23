var app = angular.module('hage');

app.directive('hagePost', ['ModelService', 'NetworkService', function (model, network) {

        return {
            restrict: 'E',
            scope: {
                post: '=hagePost'
            },
            templateUrl: 'partials/post-directive.html',
            link: function (scope) {
                scope.model = model;
                
                var busy = false;

                scope.hate = function (post) {
                    if(busy) return;
                    busy = true;
                    
                    if (model.isHated(post)) {
                        network.deleteHate().then(function () {
                            var index = model.indexOfUser(post.hatingUsers, model.user);
                            post.hatingUsers.splice(index, 1);
                            busy = false;
                        });
                    } else {
                        network.createHate().then(function () {
                            post.hatingUsers.push(model.user);
                            busy = false;
                        });
                    }
                };

                scope.isPostLong = function (post) {
                    return post.text.length > 180;
                };

                scope.comment = function () {
                    network.createComment(scope.post, scope.newComment).then(function (res) {
                        scope.post.comments.push(res);
                        scope.newComment = '';
                    });
                };
            }
        };

    }
]);