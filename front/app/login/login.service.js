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

		return this.$http.get(`${this.apiUrls.utilisateurs}/lister`)
			.then(users => {
				return users.data
			},
			error => {
				console.log("loginService: checkUser(): error:", error);
			})
			.then(users => {
				return users.find(
					user => user.email === email && user.password === chryptedPassword
				)
			})
	}

	getConnectedUserInfo() {
		return {
			"email": this.getUserEmail(),
			"nom": this.getUserNom(),
			"prenom": this.getUserPrenom(),
			"role": this.getUserRole()
		}
	}

	getUserEmail() {
		return sessionStorage.getItem('userEmail')
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
		},
			error => {
				console.log("loginService: setUserRole() error:", error);
			})
	}

	connect(user) {
		sessionStorage.setItem('session', true)
		sessionStorage.setItem('userEmail', user.email)
		sessionStorage.setItem('userNom', user.nom)
		sessionStorage.setItem('userPrenom', user.prenom)
		sessionStorage.setItem('userMatricule', user.matricule)
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