var app = angular.module('opinion');

app.directive('opinionUserBadge', ['ModelService', 'NetworkService', function (model, network) {

        return {
            restrict: 'E',
            scope: {
                user: '=opinionUser'
            },
            templateUrl: 'partials/user-badge-directive.html',
            link: function (scope) {
                scope.model = model;
                scope.follow = function() {
                    if(scope.user.isFollowing) {
                        network.unfollowUser(scope.user.username).then(function (res) {
                            console.log('unfollowed!');
                            scope.user.isFollowing = false;
                        });
                    } else {
                        network.followUser(scope.user.username).then(function (res) {
                            scope.user.isFollowing = true;
                            network.getUser(scope.user.username).then(function(res) {
                                console.log(res);
                            });
                        });
                    }
                };

            }   
        };

    }
]);