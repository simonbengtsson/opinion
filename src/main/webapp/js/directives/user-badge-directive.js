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
                    model.follow(scope.user);
                };

            }
        };

    }
]);