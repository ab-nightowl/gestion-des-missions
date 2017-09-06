export default class loginCtrl {
    constructor(loginService, $location, $window) {
        this.loginService = loginService
        this.$location = $location
        this.$window = $window
    }

    connect(email, password) {
        this.$location.hash("")
        this.loginService.checkUser(email, password)
            .then(userFound => {
                if (userFound) {
                    this.loginService.connect(userFound.email, userFound.password)
                    this.$location.hash('')
                } else {
                    this.$location.path('/')
                    this.$location.hash('error')
                }
            })
    }
}

loginCtrl.$inject = ['loginService', '$location', '$window'];