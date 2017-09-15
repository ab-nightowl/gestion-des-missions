import popupTdnSuccess from "../notesDeFrais/modal/tdnSuccess.html"
import popupCtrl from "../notesDeFrais/modal/popup.controller"

export default class headerCtrl {
    constructor(loginService, $location, $http, apiUrls, roles, $uibModal) {
        this.loginService = loginService
        this.$location = $location
        this.roles = roles
        this.publicPath = publicPath
        this.$http = $http
        this.apiUrls = apiUrls
        this.$uibModal = $uibModal

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

    runTdn() {
        this.callTdnExecute()
            .then(respMsg =>
                this.popupSuccess(respMsg)
            )
    }

    callTdnExecute() {
        return this.$http.get(this.apiUrls.runTraitementDeNuit)
            .then(resp => {
                return resp.data
            },
            resp => {
                return resp
            })
    }

    popupSuccess(msg) {
        this.$uibModal.open({
            template: popupTdnSuccess,
            controller: popupCtrl,
            controllerAs: '$ctrl',
            resolve: {
                data: () => msg
            }
        });
    }
}

headerCtrl.$inject = ['loginService', '$location', '$http', 'apiUrls', 'roles', '$uibModal']