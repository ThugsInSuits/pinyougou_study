app.service("loginService",function($http){

    //查询用户登录名字信息
    this.getLoginUserName=function(){
        return $http.get("../login/name.shtml");
    }
});
