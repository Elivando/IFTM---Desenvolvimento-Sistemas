angular.module("teethUpApp").controller('dentistaController',
    ['$routeParams', '$location', 'ajaxService', 'dataGridService', 'alertService', '$rootScope', '$filter', '$sce',
        function ($routeParams, $location, ajaxService, dataGridService, alertService, $rootScope, $filter, $sce) {

            "use strict";

            var vm = this;
            vm.alerts = [];

            this.initializeController = function () {
             
                dataGridService.initializeTableHeaders();
                dataGridService.addHeader("Codigo", "Id");
                dataGridService.addHeader("CPF", "CPF");
                dataGridService.addHeader("Nome", "Nome");                
                dataGridService.addHeader("Telefone", "Telefone");
                dataGridService.addHeader("Data Cadastro", "DataCadastro");
                dataGridService.addHeader("Ativo", "Ativo");;

                vm.tableHeaders = dataGridService.setTableHeaders();
                vm.defaultSort = dataGridService.setDefaultSort("Id");
                vm.currentPageNumber = 1;
                vm.sortExpression = "Id";
                vm.sortDirection = "ASC";
                vm.pageSize = 5;
                this.executeInquiry();

            };
          
            this.executeInquiry = function () {
                ajaxService.ajaxGet("rs/dentista", this.getOnSuccess, this.onError);
            };

            this.getOnSuccess = function (response) {              
                vm.dentistas = response.data;
            };
                       
            this.onError = function (response) {
                vm.clearValidationErrors();             
                alertService.renderErrorMessage("Error.");
                vm.messageBox = $sce.trustAsHtml(alertService.returnFormattedMessage());
                vm.alerts = alertService.returnAlerts();
            };

            this.clearValidationErrors = function () {
                vm.codigoPadraoInputError = false;
                vm.nomeInputError = false;
                vm.ativoInputError = false;
            };

            this.closeAlert = function (index) {
                vm.alerts.splice(index, 1);
            };
           
        }]);