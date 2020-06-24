// 首页控制器
app.controller('indexController',function ($scope,loginService) {
    $scope.showname = function () {
        loginService.getLoginUserName().success(
            function(response) {
                console.log(response);
                $scope.loginName = response.loginName;
            }
        );
    }
});