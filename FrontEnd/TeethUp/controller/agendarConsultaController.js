angular.module("teethUpApp").controller('agendarConsultaController',
    ['$routeParams', '$location', 'ajaxService', 'dataGridService', 'alertService', '$rootScope', '$filter', '$sce',
        function ($routeParams, $location, ajaxService, dataGridService, alertService, $rootScope, $filter, $sce) {

            "use strict";

            var vm = this;
            vm.alerts = [];

            this.initializeController = function () {
                vm.id = "";
                vm.dataConsulta = "";
                vm.hora = "";
                vm.ativo = true;
                vm.clinica = null;
                vm.dentista = null;
                vm.paciente = null;
                vm.messageBox = "";

                vm.getDentistas();
                vm.getPacientes();
                vm.getClinicas();

                dataGridService.initializeTableHeaders();
                dataGridService.addHeader("Codigo", "DataConsulta");
                dataGridService.addHeader("Dia da Semana", "DataConsulta");
                dataGridService.addHeader("Data Consulta", "DataConsulta");
                dataGridService.addHeader("Hora", "Hora")
                dataGridService.addHeader("Clinica", "Clinica");
                dataGridService.addHeader("Dentista", "Dentista");
                dataGridService.addHeader("Paciente", "Paciente");
                dataGridService.addHeader("Ativo", "Ativo");;

                vm.tableHeaders = dataGridService.setTableHeaders();
                vm.defaultSort = dataGridService.setDefaultSort("DataConsulta");
                vm.currentPageNumber = 1;
                vm.sortExpression = "DataConsulta";
                vm.sortDirection = "ASC";
                vm.pageSize = 5;
                this.executeInquiry();

            };

            this.clean = function () {
                vm.id = "";
                vm.dataConsulta = "";
                vm.hora = "";
                vm.clinica = null;
                vm.dentista = null;
                vm.paciente = null;
                vm.ativo = true;
                vm.alerts = [];
                vm.clearValidationErrors();
            };

            this.edit = function (consulta) {
                vm.clean();
                vm.id = consulta.id;
                vm.dataConsulta = consulta.dataConsulta;
                vm.hora = new Date('2018-07-06 ' + consulta.hora);
                vm.ativo = consulta.ativo;
                vm.clinica = consulta.clinica;
                vm.dentista = consulta.dentista;
                vm.paciente = consulta.paciente;
                vm.dataCadastro = consulta.dataCadastro;
            };

            this.save = function () {
                if (vm.validateSave()) {
                    var consulta = new Object();
                    //var hora = Date.parse('2018-08-16 ' + vm.hora);
                    consulta.id = vm.id;
                    if (!$.isNumeric(vm.dataConsulta)) {
                        var d = vm.dataConsulta.split('/');
                        consulta.dataConsulta = d[1] + '/' + d[0] + '/' + d[2];
                        consulta.dataConsulta = Date.parse(consulta.dataConsulta);
                    } else { consulta.dataConsulta = vm.dataConsulta; }
                    consulta.hora = Date.parse(vm.hora);
                    consulta.ativo = vm.ativo;
                    consulta.clinica = vm.clinica;
                    consulta.dentista = vm.dentista;
                    consulta.paciente = vm.paciente;
                    consulta.dataCadastro = vm.dataCadastro;
                    if (consulta.id === "") {
                        ajaxService.ajaxPost(consulta, "rs/consulta", this.saveOnSuccess, this.onError);
                    }
                    else {
                        ajaxService.ajaxPut(consulta, "rs/consulta/" + consulta.id, this.saveOnSuccess, this.onError);
                    }
                }
            };

            this.delete = function (consulta) {
                if (vm.id === "") {
                    alertService.renderErrorMessage("Selecione o registro para excluir.");
                    vm.messageBox = $sce.trustAsHtml(alertService.returnFormattedMessage());
                    vm.alerts = alertService.returnAlerts();
                } else {
                    ajaxService.ajaxDelete(consulta, "rs/consulta/" + vm.id, this.saveOnSuccess, this.onError);
                }
            };

            this.validateSave = function () {
                vm.clearValidationErrors();
                var retorno = true;
                if (vm.dataConsulta === "") {
                    alertService.renderErrorMessage("A data da consulta deve ser informada.");
                    retorno = false;
                } else if (vm.hora === "") {
                    alertService.renderErrorMessage("A hora da consulta deve ser informada.");
                    retorno = false;
                } else if (vm.clinica === null) {
                    alertService.renderErrorMessage("A clinica deve ser informada.");
                    retorno = false;
                } else if (vm.dentista === null) {
                    alertService.renderErrorMessage("O dentista deve ser informado.");
                    retorno = false;
                } else if (vm.paciente === null) {
                    alertService.renderErrorMessage("O paciente deve ser informado.");
                    retorno = false;
                }
                if (retorno) {
                    return true;
                } else {
                    vm.messageBox = $sce.trustAsHtml(alertService.returnFormattedMessage());
                    vm.alerts = alertService.returnAlerts();
                    return false;
                }
            };

            this.saveOnSuccess = function (response) {
                vm.clean();
                vm.executeInquiry();
                vm.clearValidationErrors();
                alertService.renderErrorMessage("Operação realizada com sucesso!");
                vm.messageBox = $sce.trustAsHtml(alertService.returnFormattedMessage());
                vm.alerts = alertService.returnAlerts();
            };

            this.executeInquiry = function () {
                //var inquiry = vm.prepareSearch();
                ajaxService.ajaxGet("rs/consulta", this.getOnSuccess, this.onError);
            };

            this.getOnSuccess = function (response) {
                //vm.consultasData = response.data;
                //var paginated = vm.getPaginatedItems(vm.consultasData, vm.currentPageNumber);
                //vm.consultas = paginated.data;
                //vm.totalRows = paginated.total;
                //vm.totalPages = paginated.total_pages;
                vm.consultas = response.data;
            };

            this.pageChanged = function () {
                var paginated = vm.getPaginatedItems(vm.consultasData, vm.currentPageNumber);
                vm.consultas = paginated.data;
                vm.totalRows = paginated.total;
                vm.totalPages = paginated.total_pages;
            };

            this.getDentistas = function () {
                ajaxService.ajaxGet("rs/dentista", this.getDentistaOnSuccess, this.onError);
            }

            this.getDentistaOnSuccess = function (response) {
                vm.dentistas = response.data;
            };

            this.getPacientes = function () {
                ajaxService.ajaxGet("rs/paciente", this.getPacienteOnSuccess, this.onError);
            }

            this.getPacienteOnSuccess = function (response) {
                vm.pacientes = response.data;
            };

            this.getClinicas = function () {
                ajaxService.ajaxGet("rs/clinica", this.getClinicasOnSuccess, this.onError);
            }

            this.getClinicasOnSuccess = function (response) {
                vm.clinicas = response.data;
            };

            this.onError = function (response) {
                vm.clearValidationErrors();
                //  alertService.renderErrorMessage(response);
                // vm.messageBox = alertService.returnFormattedMessage();
                //vm.alerts = alertService.returnAlerts();
                // alertService.setValidationErrors(vm, response.validationErrors);
                alertService.renderErrorMessage("Erro ao salvar os registros.");
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