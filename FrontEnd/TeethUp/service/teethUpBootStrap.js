//
//  angular bootup and routing table
//


console.log("teethUpApp Project Bootstrap");

(function () {

    var app = angular.module('teethUpApp', ['ngRoute', 'ui.bootstrap', 'ngSanitize', 'blockUI', 'inform','ui.select','ngMaterial']);

    app.config(['$controllerProvider', '$provide', '$routeProvider', function ($controllerProvider, $provide, $routeProvider) {
        app.register =
          {
              controller: $controllerProvider.register,
              service: $provide.service
           };
    }]);

    app.directive('bootstrapSwitch', [
        function () {
            return {
                restrict: 'A',
                require: '?ngModel',
                link: function (scope, element, attrs, ngModel) {
                    element.bootstrapSwitch();

                    element.on('switchChange.bootstrapSwitch', function (event, state) {
                        if (ngModel) {
                            scope.$apply(function () {
                                ngModel.$setViewValue(state);
                            });
                        }
                    });

                    scope.$watch(attrs.ngModel, function (newValue, oldValue) {
                        if (newValue) {
                            element.bootstrapSwitch('state', true, true);
                        } else {
                            element.bootstrapSwitch('state', false, true);
                        }
                    });
                }
            };
        }
    ]);

})();

console.log("Code Project Bootstrap FINISHED 2");




