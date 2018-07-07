// create the module and name it teethUpApp
var teethUpApp = angular.module('teethUpApp', ['ngRoute', 'blockUI', 'inform']);

// configure our routes
teethUpApp.config(function ($routeProvider, $locationProvider) {
    $routeProvider

        // route for the home page
        .when('/', {
            templateUrl: 'pages/home.html',
            controller: 'mainController'
        })

        .when('/consulta', {
            templateUrl: 'pages/consulta.html',
            controller: 'consultaController'
        })

        .when('/agendarConsulta', {
            templateUrl: 'pages/agendarConsulta.html',
            controller: 'agendarConsultaController'
        })

        .when('/dentista', {
            templateUrl: 'pages/dentista.html',
            controller: 'dentistaController'
        })

        .when('/paciente', {
            templateUrl: 'pages/paciente.html',
            controller: 'pacienteController'
        })
        .when('/clinica', {
            templateUrl: 'pages/clinica.html',
            controller: 'clinicaController'
        });

    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
    $locationProvider.hashPrefix('');

});


teethUpApp.directive('bootstrapSwitch', [
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

// Here is where the magic works
teethUpApp.directive('date', function (dateFilter) {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {

            var dateFormat = attrs['date'] || 'yyyy-MM-dd';

            ctrl.$formatters.unshift(function (modelValue) {
                return dateFilter(modelValue, dateFormat);
            });
        }
    };
});

teethUpApp.directive('hora', function (dateFilter) {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {

            var dateFormat = attrs['hora'] || 'hh:mm:ss';

            ctrl.$formatters.unshift(function (modelValue) {
                return dateFilter(modelValue, dateFormat);
            });
        }
    };
});

// create the controller and inject Angular's $scope
teethUpApp.controller('mainController', function ($scope) {
    // create a message to display in our view
    $scope.message = '';
});