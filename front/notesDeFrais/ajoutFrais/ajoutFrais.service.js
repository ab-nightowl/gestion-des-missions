export default class ajoutFraisService {
    constructor(apiUrls, $http) {
        this.apiUrls = apiUrls
        this.$http = $http
    }

    findNaturesFrais() {
        return this.$http.get(this.apiUrls.naturesFrais)
            .then(response => {
                return response.data;
            }, response => { })
    }

    saveNew(date, nature, montant) {
        this.frais = {
            "dateCreation": date,
            "natureId": nature,
            "montant": montant
        }


        this.$http.post(this.apiUrls.naturesFrais, this.frais).then(function (data) {
            alert('Cest bon !');
        });

    }

}