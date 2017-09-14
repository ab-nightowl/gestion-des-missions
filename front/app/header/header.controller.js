export default class headerCtrl {
    constructor(loginService, $location, roles) {
        this.loginService = loginService
        this.$location = $location
        this.roles = roles
        this.publicPath = publicPath

        this.user = ""
        this.userRole = ""
        this.updateConnectedUserInfo()
    }

    updateConnectedUserInfo() {
        this.user = this.loginService.getConnectedUserInfo()
        if (this.user.email) {
            this.loginService.retrieveUserRole(this.user.email).then(role =>
                this.userRole = role
            )
        }
    }

    isUserConnected() {
        return this.loginService.isConnected()
    }

    isCurrentPath(path) {
        return this.$location.path() === path
    }
}

headerCtrl.$inject = ['loginService', '$location', 'roles']