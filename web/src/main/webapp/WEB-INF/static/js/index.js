var MyApp = angular.module('MyApp', ['ng']);
MyApp.controller('indexCtr', function ($scope, $http, $timeout) {
    $scope.condition = {};
    $scope.try={};
    $scope.init = function () {
        var url = "/list";
        var data = $scope.condition;
        transFn = function (data) {
            return $.param(data);
        };
        postCfg = {
            headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
            transformRequest: transFn
        };
        $http.post(url, data, postCfg).success(function (data) {
            if (data) {
                for(var i=0;i<data.length;i++){

                }
                $scope.list = data;
                $timeout(function () {
                    $('#trys').masonry({
                        itemSelector: '.try-item'
                    });
                }, 3);
            }
        });
    }
    $scope.self=function(){
        $scope.condition.userId=1;
        $scope.init();
    }
    $scope.detail = function(id) {
        var url = "/get?id="+id;
        $http.get(url).success(function (data) {
            if (data) {
                $scope.try = data;
                $("#readModal").click();
            }
        });
    }
    $scope.edit = function(id) {
        var url = "/get?id="+id;
        $http.get(url).success(function (data) {
            if (data) {
                $scope.try = data;
                $("#writeModal").click();
            }
        });
    }
    $scope.add = function() {
        $scope.try = {};
        $("#writeModal").click();
    }
    $scope.save = function() {
        var url = "/save";
        var data = $scope.try;
        transFn = function (data) {
            return $.param(data);
        };
        postCfg = {
            headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
            transformRequest: transFn
        };
        $http.post(url, data, postCfg).success(function (data) {
            if (data) {
                $(".close-writeAnimatedModal").click();
                $scope.init();
            }
        });
    }
});
