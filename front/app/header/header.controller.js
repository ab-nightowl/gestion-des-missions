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

    isCurrentPath(path) {
        return this.$location.path() === path
    }
}

headerCtrl.$inject = ['loginService', '$location']