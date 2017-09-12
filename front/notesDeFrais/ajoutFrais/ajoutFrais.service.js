import popupSuccess from "../modal/ajoutsuccess.html"
import popupFailure from "../modal/ajoutfailure.html"
import popupCtrl from "../modal/popup.controller"

export default class ajoutFraisService {
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

    saveNew(date, nature, montant, idMission) {
        this.frais = {
            "dateCreation": date,
            "montant": montant
        }

        this.$http.post(this.apiUrls.naturesFrais + "/" + idMission + "/" + nature, this.frais)
    }

    popupSuccess(){
        this.$uibModal.open({
            template: popupSuccess,
            controller: popupCtrl,
            controllerAs: '$ctrl'
        });
    }
    popupFailure(){
        this.$uibModal.open({
            template: popupFailure,
            controller: popupCtrl,
            controllerAs: '$ctrl'
        });
    }

}