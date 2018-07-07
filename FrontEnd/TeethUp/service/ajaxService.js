
console.log("ajax service");

angular.module('teethUpApp').service('ajaxService', ['$http', 'blockUI', '$location', 'inform', function ($http, blockUI, $location, inform) {

    "use strict";

    var baseSiteUrlPath = 'http://localhost:8080/TeethUpServer/';

    this.ajaxPost = function (data, route, successFunction, errorFunction) {

        blockUI.start("Aguarde...");
        route = baseSiteUrlPath + route;

        $http.post(route, data).then(function (response, status, headers, config) {
            blockUI.stop();
            successFunction(response, status);
        }, function (response) {
            blockUI.stop();
            errorFunction(response);
        });

    }

    this.ajaxPut = function (data, route, successFunction, errorFunction) {

        blockUI.start("Aguarde...");
        route = baseSiteUrlPath + route;

        $http.put(route, data).then(function (response, status, headers, config) {
            blockUI.stop();
            successFunction(response, status);
        }, function (response) {
            blockUI.stop();
            errorFunction(response);
        });

    }

    this.ajaxDelete = function (data, route, successFunction, errorFunction) {

        blockUI.start("Aguarde...");
        route = baseSiteUrlPath + route;

        $http.delete(route).then(function (response, status, headers, config) {
            blockUI.stop();
            successFunction(response, status);
        }, function (response) {
            blockUI.stop();
            errorFunction(response);
        });

    }

    this.ajaxGet = function (route, successFunction, errorFunction) {
        blockUI.start("Aguarde...");
        route = baseSiteUrlPath + route;
        setTimeout(function () {
            $http({ method: 'GET', url: route }).then(function (response, status, headers, config) {
                blockUI.stop();
                successFunction(response, status);
            }, function (response) {
                blockUI.stop();
                errorFunction(response);
            });
        }, 0);

    }

    this.ajaxGetWithData = function (data, route, successFunction, errorFunction) {
        blockUI.start("Aguarde...");
        route = baseSiteUrlPath + route;
        setTimeout(function () {
            $http({ method: 'GET', url: route, params: data }).success(function (response, status, headers, config) {
                blockUI.stop();
                successFunction(response, status);
            }).error(function (response) {
                blockUI.stop();
                errorFunction(response);
            });
        }, 0);
    }

}]);