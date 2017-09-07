export default class detailGestionFraisService {
    constructor(apiUrls, $http) {
        this.apiUrls = apiUrls
        this.$http = $http
    }

    findMission(idMission) {
        return this.$http.get(this.apiUrls.missions+"/Detail/"+idMission)
            .then(response => {
                return response.data;
            }, response => { })
    }

    findFraisMission(idMission) {
        return this.$http.get(this.apiUrls.frais+"/"+idMission)
            .then(response => {
                return response.data;
            }, response => { })
    }
}