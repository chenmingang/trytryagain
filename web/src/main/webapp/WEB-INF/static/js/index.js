var MyApp = angular.module('MyApp', ['ng']);
MyApp.controller('indexCtr', function ($scope,$http,$timeout) {
    $scope.init = function () {
        var url = "/list";
        var data = {};
        transFn = function (data) {
            return $.param(data);
        };
        postCfg = {
            headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
            transformRequest: transFn
        };
        $http.post(url, data, postCfg).success(function (data) {
            if (data) {
                $scope.list = data;
                $timeout(function() {
                    $('#trys').masonry({
                        itemSelector: '.try-item'
                    });
                }, 3);

            }
        });
    }
});