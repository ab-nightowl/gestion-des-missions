export default class detailGestionFraisService {
    constructor(apiUrls, $http) {
        this.apiUrls = apiUrls
        this.$http = $http
    }

    findMission(idMission) {
        console.log(this.apiUrls.missions+"/Detail/"+idMission)
        return this.$http.get(this.apiUrls.missions+"/Detail/"+idMission)
            .then(response => {
                return response.data;
            }, response => { })
    }

    findFraisMission(idMission) {
        console.log(this.apiUrls.frais+"/"+idMission)
        return this.$http.get(this.apiUrls.frais+"/"+idMission)
            .then(response => {
                console.log(response.data)
                return response.data;
            }, response => { })
    }
}