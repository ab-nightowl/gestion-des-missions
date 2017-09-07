export default class gestionFraisService {
    constructor(apiUrls, $http) {
        this.apiUrls = apiUrls
        this.$http = $http
    }

    findMission() {
        return this.$http.get(this.apiUrls.mission)
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