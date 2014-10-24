var app = angular.module('hage');

app.directive('hageUserBadge', ['ModelService', 'NetworkService', function (model, network) {

        return {
            restrict: 'E',
            scope: {
                user: '=hageUser'
            },
            templateUrl: 'partials/user-badge-directive.html',
            link: function (scope) {
                scope.follow = function() {
                    model.follow(scope.user);
                };

            }
        };

    }
]);