var app = angular.module('hage');

app.directive('hagePost', ['ModelService', function (model) {

    return {
        restrict: 'E',
        scope: {
            post: '='
        },
        templateUrl: 'partials/post-directive.html',
        link: function (scope, element) {
            scope.model = model;
        }
    };

}]);