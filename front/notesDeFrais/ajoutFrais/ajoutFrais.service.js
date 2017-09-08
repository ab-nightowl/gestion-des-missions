export default class ajoutFraisService {
    constructor(apiUrls, $http, $location) {
        this.apiUrls = apiUrls
        this.$http = $http
        this.$location = $location
    }

    redirection(idMission) {
        this.$location.path('/detail/' + idMission)
    }

    findNaturesFrais() {
        return this.$http.get(this.apiUrls.naturesFrais)
            .then(response => {
                return response.data;
            }, response => { })
    }

    saveNew(date, nature, montant, idMission) {
        this.frais = {
            "dateCreation": date,
            "montant": montant
        }

        this.$http.post(this.apiUrls.naturesFrais + "/" + idMission + "/" + nature, this.frais).then(function (data) {
            alert("Le frais à été enregistré !");
        });
        this.redirection(idMission)
    }

}