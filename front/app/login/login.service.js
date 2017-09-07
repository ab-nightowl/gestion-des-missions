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

	getConnectedUserInfo() {
		return {
			"nom": this.getUserNom(),
			"prenom": this.getUserPrenom(),
			"role": this.getUserRole()
		}
	}

	getUserRole() {
		return sessionStorage.getItem('userRole')
	}

	getUserNom() {
		return sessionStorage.getItem('userNom')
	}

	getUserPrenom() {
		return sessionStorage.getItem('userPrenom')
	}

	setUserRole(email) {
		return this.$http({
			url: `${this.apiUrls.utilisateurs}/role`,
			method: "GET",
			params: {
				userEmail: email
			}
		}).then(resp => {
			sessionStorage.setItem('userRole', resp.data.role)
		})
	}

	connect(user) {
		sessionStorage.setItem('session', true)
		sessionStorage.setItem('userEmail', user.email)
		sessionStorage.setItem('userNom', user.nom)
		sessionStorage.setItem('userPrenom', user.prenom)
		this.setUserRole(user.email)
		this.$location.path('/')
		this.$window.location.reload();
	}

	disconnect() {
		sessionStorage.clear()
		this.$location.path('/')
		this.$window.location.reload();
	}
}

loginService.$inject = ['$http', '$location', '$window', 'apiUrls', 'sha1'];