import popupSuccess from "../modal/ajoutsuccess.1.html"
import popupFailure from "../modal/ajoutfailure.html"
import popupCtrl from "../modal/popup.controller"

export default class modifFraisService {
    constructor(apiUrls, $http, $location, $uibModal) {
        this.apiUrls = apiUrls
        this.$http = $http
        this.$uibModal = $uibModal
        this.$location = $location
    }

    redirection() {
        this.$location.path('/detail')
    }

    findNaturesFrais() {
        return this.$http.get(this.apiUrls.naturesFrais)
            .then(response => {
                return response.data;
            }, response => { })
    }

    findFrais(idFrais) {
        return this.$http.get(this.apiUrls.frais+"ParI/"+idFrais)
            .then(response => {
                return response.data;
            }, response => { })
            .then(frais => {
                frais.dateCreation = new Date(frais.dateCreation)
                return frais;
            })
    }

    saveModif(id, date, nature, montant, idMission) {
        this.frais = {
            "id": id,
            "dateCreation": date,
            "nature": nature,
            "montant": montant
        }

        this.$http.put(this.apiUrls.frais + "/" + idMission, this.frais)
    }

    popupSuccess(){
        this.$uibModal.open({
            template: popupSuccess,
            controller: popupCtrl,
            controllerAs: '$ctrl',
            resolve: {
              data: () => "-"
            }
        });
    }
    popupFailure(){
        this.$uibModal.open({
            template: popupFailure,
            controller: popupCtrl,
            controllerAs: '$ctrl',
            resolve: {
              data: () => "-"
            }
        });
    }

}