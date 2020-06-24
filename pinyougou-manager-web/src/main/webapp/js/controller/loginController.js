app.controller("loginController",function($scope,$http,$controller,loginService){
    //获取用户名
    $scope.getUserName=function(){
        loginService.getLoginUserName().success(function(response){
            $scope.userloginname=response;
        });
    }
});
