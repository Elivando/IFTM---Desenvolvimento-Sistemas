//alert("data grid service")
console.log("data grid service");

angular.module('teethUpApp').service('dataGridService', ['$http', 'blockUI', function ($http, blockUI) {

    var dataGrid = new Object();

    dataGrid.tableHeaders = [];
    dataGrid.sortExpression = "";
    dataGrid.sortDirection = "";
    dataGrid.sort = "";

    this.initializeTableHeaders = function () {
        dataGrid = new Object();
        dataGrid.tableHeaders = [];
    };

    this.addHeader = function (label, expression) {
        var tableHeader = new Object();
        tableHeader.label = label;
        tableHeader.sortExpression = expression;
        dataGrid.tableHeaders.push(tableHeader);
    };

    this.setTableHeaders = function () {
        return dataGrid.tableHeaders;
    }

    this.changeSorting = function (columnSelected, currentSort, tableHeaders) {

        dataGrid = new Object();

        dataGrid.sortExpression = "";

        var sort = currentSort;
        if (sort.column == columnSelected) {
            sort.descending = !sort.descending;
        } else {
            sort.column = columnSelected;
            sort.descending = false;
        }

        for (var i = 0; i < tableHeaders.length; i++) {
            if (sort.column == tableHeaders[i].label) {
                dataGrid.sortExpression = tableHeaders[i].sortExpression;
                break;
            }
        }

        if (sort.descending == true)
            dataGrid.sortDirection = "DESC";
        else
            dataGrid.sortDirection = "ASC";

        dataGrid.sort = sort;

    }

    this.getSort = function (columnSelected, currentSort, tableHeaders) {
        return dataGrid.sort;
    };

    this.getSortDirection = function () {
        return dataGrid.sortDirection;
    };

    this.getSortExpression = function () {
        return dataGrid.sortExpression;
    };

    this.setDefaultSort = function (defaultSort) {
        var sort = {
            column: defaultSort,
            descending: false
        }
        return sort;
    };

    this.setSortIndicator = function (column, defaultSort) {
        return column == defaultSort.column && 'sort-' + defaultSort.descending;
    };
    
    function capitalize(string) {
        return string.charAt(0).toLowerCase() + string.slice(1);
    };

    /*
    this.exportExcel = function (data, route, nameList, tableHeaders, errorFunction) {
        var baseSiteUrlPath = $("base").first().attr("href");
        blockUI.start("Aguarde...");
        route = baseSiteUrlPath + route;

        $http.post(route, data).success(function (response, status, headers, config) {
            blockUI.stop();
            getExportOnSuccess(response, nameList, tableHeaders);
        }).error(function (response) {
            blockUI.stop();
            if (response.isAuthenicated == false && $location.$$path != "/Accounts/Login") { window.location = baseSiteUrlPath + "/Accounts/Login"; }
            errorFunction(response);
        });
    };

    function getExportOnSuccess(response, nameList, tableHeaders) {
        var mystyle = {
            headers: true,
            column: { style: { Font: { Bold: "1" } } }
        };
        var today = new Date().toJSON().slice(0, 10);
        var objs = response[nameList];
        var columsGrid = '';

        for (var i = 0; i < tableHeaders.length; i++) {
            if (tableHeaders[i].sortExpression != '') {
                if (columsGrid == '') {
                    columsGrid = columsGrid + capitalize(tableHeaders[i].sortExpression) + '[' + tableHeaders[i].label.toUpperCase() + ']';
                } else {
                    columsGrid = columsGrid + ',' + capitalize(tableHeaders[i].sortExpression) + '[' + tableHeaders[i].label.toUpperCase() + ']';
                }
            }
        }

        alasql.promise('SELECT ' + columsGrid + ' INTO XLSXML("Report_' + nameList + '_' + today + '.xls",?) FROM ?', [mystyle, objs])
                .then(function (data) {
                    console.log('Data saved');
                }).catch(function (err) {
                    console.log('Error:', err);
                });
    };
    */

}]);
