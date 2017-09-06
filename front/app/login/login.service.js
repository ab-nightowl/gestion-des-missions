export default class loginService {
	constructor($http, $location, $window, apiUrls, sha1) {
		this.$http = $http
		this.$location = $location
		this.$window = $window
		this.apiUrls = apiUrls
		this.sha1 = sha1
	}

	isConnected() {
		if (sessionStorage.getItem('session')) {
			return true
		} else {
			return false
		}
	}

	checkUser(email, password) {
		let chryptedPassword = password ? this.sha1(password) : ""

		return this.$http.get(this.apiUrls.utilisateurs)
			.then(users => {
				return users.data
			})
			.then(users => {
				return users.find(
					user => user.email === email && user.password === chryptedPassword
				)
			})
	}

	connect(email, password) {
		sessionStorage.setItem('session', true)
		sessionStorage.setItem('userEmail', email)
		sessionStorage.setItem('userPassword', password)
		this.$location.path('/')
		this.$window.location.reload();
	}

	disconnect() {
		sessionStorage.removeItem('session')
		sessionStorage.removeItem('userEmail')
		sessionStorage.removeItem('userPassword')
		sessionStorage.clear()
		this.$location.path('/')
		this.$window.location.reload();
	}
}

loginService.$inject = ['$http', '$location', '$window', 'apiUrls', 'sha1'];