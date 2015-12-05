$('.trys').masonry({
    itemSelector: '.try-item',
    columnWidth: 280,
    visibleStyle: { opacity: 1, transform: 'scale(1)' },
    isAnimated: true,
    animationOptions: {
        duration: 550,
        easing: 'linear',
        queue: false
    }

});
function indexCtr($scope, $http) {
    $scope.init = function () {
        var url = "/list";
        var data={};
        transFn = function (data) {
            return $.param(data);
        };
        postCfg = {
            headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
            transformRequest: transFn
        };
        $http.post(url, data, postCfg).success(function(data){
            if(data){
                $scope.list=data;
            }
        });
    }
}