export default class detailGestionFraisService {
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
}