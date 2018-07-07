angular.module("teethUpApp").controller('consultaController',
    ['$routeParams', '$location', 'ajaxService', 'dataGridService', 'alertService', '$rootScope', '$filter',
        function ($routeParams, $location, ajaxService, dataGridService, alertService, $rootScope, $filter) {

            "use strict";

            var vm = this;
            vm.alerts = [];

            this.initializeController = function () {

                dataGridService.initializeTableHeaders();
                dataGridService.addHeader("Dia da Semana", "DataConsulta");
                dataGridService.addHeader("Data Consulta", "DataConsulta");
                dataGridService.addHeader("Hora", "Hora");

                vm.tableHeaders = dataGridService.setTableHeaders();
                vm.defaultSort = dataGridService.setDefaultSort("DataConsulta");
                vm.currentPageNumber = 1;
                vm.sortExpression = "DataConsulta";
                vm.sortDirection = "ASC";
                vm.pageSize = 5;
                this.executeInquiry();

            };

            this.executeInquiry = function () {
                //var inquiry = vm.prepareSearch();
                ajaxService.ajaxGet("rs/consulta", this.getOnSuccess, this.getOnError);
            };

            this.getOnSuccess = function (response) {
                vm.consultasData = response.data;
                var paginated = vm.getPaginatedItems(vm.consultasData, vm.currentPageNumber);
                vm.consultas = response.data;//paginated.data;
                vm.totalRows = paginated.total;
                vm.totalPages = paginated.total_pages;

            };

            this.pageChanged = function () {
                var paginated = vm.getPaginatedItems(vm.consultasData, vm.currentPageNumber);
                vm.consultas = paginated.data;
                vm.totalRows = paginated.total;
                vm.totalPages = paginated.total_pages;
            };

            this.getOnError = function (response) {
                vm.clearValidationErrors();
                alertService.renderErrorMessage(response.returnMessage);
                vm.messageBox = alertService.returnFormattedMessage();
                vm.alerts = alertService.returnAlerts();
                alertService.setValidationErrors(vm, response.validationErrors);
            };

            this.clearValidationErrors = function () {
                vm.codigoPadraoInputError = false;
                vm.nomeInputError = false;
                vm.ativoInputError = false;
            };

            this.closeAlert = function (index) {
                vm.alerts.splice(index, 1);
            };

            this.getPaginatedItems = function (items, page) {
                var page = page || 1,
                    per_page = 5,
                    offset = (page - 1) * per_page,
                    paginatedItems = _.tail(items, offset).slice(0, per_page);
                return {
                    page: page,
                    per_page: per_page,
                    total: items.length,
                    total_pages: Math.ceil(items.length / per_page),
                    data: paginatedItems
                };
            };

        }]);