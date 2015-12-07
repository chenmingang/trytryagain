var MyApp = angular.module('MyApp', ['ng']);
MyApp.controller('indexCtr', function ($scope, $http, $timeout) {
    $scope.condition = {};
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
                $scope.list = data;
                $timeout(function () {
                    $('#trys').masonry({
                        itemSelector: '.try-item'
                    });
                }, 3);
            }
        });
    }
    $scope.detail = function(id) {
        var url = "/get?id="+id;
        $http.get(url).success(function (data) {
            if (data) {
                $scope.try = data;
            }
        });
    }
});
$("#trys").on('click','h4',function(){
    //var tryitemdiv = $($(this).parent()).parent();
    //var trysdiv = $(tryitemdiv).parent();
    //var width = trysdiv[0].clientWidth;
    //tryitemdiv.animate({width:width+'px'},"slow");
    ////tryitemdiv.removeClass('try-item');
    //$('#trys').masonry({
    //    itemSelector: '.try-item'
    //});
});