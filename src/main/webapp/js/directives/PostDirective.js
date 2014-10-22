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

                scope.hate = function (post) {
                    if (model.isHated(post)) {
                        network.deleteHate().success(function () {
                            var index = model.indexOfUser(post.hatingUsers, model.user);
                            post.hatingUsers.splice(index, 1);
                            console.log('Successfully deleted hate on post');
                        });
                    } else {
                        network.createHate().success(function () {
                            post.hatingUsers.push(model.user);
                            console.log('Successfully created hate on post');
                        });
                    }
                };

                scope.isPostLong = function (post) {
                    return post.text.length > 180;
                };

                scope.comment = function () {
                    network.createComment(scope.post, scope.newComment).success(function (res) {
                        console.log(res);
                        scope.post.comments.push(res);
                        scope.newComment = '';
                    });
                };
            }
        };

    }
]);