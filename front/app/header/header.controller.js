export default class headerCtrl{
    constructor(loginService, $location){
        this.loginService = loginService
        this.$location = $location
        this.publicPath = publicPath

        this.user = this.loginService.getConnectedUserInfo()
    }
    
    isUserConnected(){
        return this.loginService.isConnected()
    }
}

headerCtrl.$inject = ['loginService', '$location']