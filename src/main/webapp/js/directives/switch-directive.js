var app = angular.module('hage');
app.directive('hageSwitch', ['$timeout',
    function ($timeout) {
        return {
            restrict: 'A',
            require: 'ngModel',
            scope: {
                switchActive: '@',
                switchOnText: '@',
                switchOffText: '@'
            },
            link: function link(scope, element, attrs, controller) {
                
                element.bootstrapSwitch('offText', scope.switchOffText);
                element.bootstrapSwitch('onText', scope.switchOnText);
                element.bootstrapSwitch('size', 'lg');
                
                controller.$formatters.push(function (newValue) {
                    if (newValue !== undefined) {
                        $timeout(function () {
                            element.bootstrapSwitch('state', newValue || false, true);
                        });
                    }
                });

                element.on('switchChange.bootstrapSwitch', function (e, data) {
                    scope.$apply(function () {
                        controller.$setViewValue(data);
                    });
                });

                element.bootstrapSwitch();
                
                $timeout(function () {
                    element.bootstrapSwitch('state', controller.$modelValue || false, true);
                });
            }
        };
    }
]);