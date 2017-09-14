import popupFraisSuppr from "../modal/fraisSuppr.html"
import popupCtrl2 from "../modal/popup.controller.2"
export default class detailGestionFraisService {
    constructor(apiUrls, $http, $uibModal, $window, $location) {
        this.apiUrls = apiUrls
        this.$http = $http
        this.$uibModal = $uibModal
        this.$window = $window
        this.$location = $location
    }

    findMission(idMission) {
        return this.$http.get(this.apiUrls.missions + "/Detail/" + idMission)
            .then(response => {
                return response.data;
            }, response => { })
    }

    findFraisMission(idMission) {
        return this.$http.get(this.apiUrls.frais + "ParM/" + idMission)
            .then(response => {
                return response.data;
            }, response => { })
    }


    popupFraisSuppr(idFrais, dateCreationFrais, natureLibelle, montantFrais) {
        this.$uibModal.open({
            template: popupFraisSuppr,
            controller: popupCtrl2,
            controllerAs: '$ctrl',
            resolve: {
                idFrais: () => idFrais,
                dateCreationFrais: () => dateCreationFrais,
                natureLibelle: () => natureLibelle,
                montantFrais: () => montantFrais
            }
        });
    }

    suppr(idFrais) {
        this.$http.delete(this.apiUrls.frais + "/" + idFrais)
        this.$window.location.reload();
    }

    validerNoteDeFrais(mission){
        this.$http.put(this.apiUrls.missions, mission)
        this.$location.path('/note-de-frais/gestion')
        this.$window.location.reload();
    }
}