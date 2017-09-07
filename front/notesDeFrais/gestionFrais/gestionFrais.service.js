export default class gestionFraisService {
    constructor(apiUrls, $http) {
        this.apiUrls = apiUrls
        this.$http = $http
    }

    findMission(userMatricule) {
        console.log(this.apiUrls.missions+"/"+userMatricule)
        return this.$http.get(this.apiUrls.missions+"/"+userMatricule)
            .then(response => {
                return response.data;
            }, response => { })
    }

    sumFraisMission() {
        return this.$http.get(this.apiUrls.frais)
            .then(response => {
                return response.data
            }, response => { })
    }

}