import ngRoute from 'angular-route'

import loginService from './login.service'
import loginComponent from './login.component'

const loginModule = angular
    .module('loginModule', [ngRoute])
    .component('loginComponent', loginComponent)
    .service('loginService', ['$http', '$location', '$window', 'apiUrls', 'sha1', loginService])

export default loginModule