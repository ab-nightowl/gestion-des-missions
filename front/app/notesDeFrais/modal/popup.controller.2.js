export default class PopupController {
    constructor($uibModalInstance, $location, idFrais, dateCreationFrais, natureLibelle, montantFrais, detailGestionFraisService) {
        this.$uibModalInstance = $uibModalInstance
        this.$location = $location
        this.idFrais = idFrais
        this.dateCreationFrais = dateCreationFrais
        this.natureLibelle = natureLibelle
        this.montantFrais = montantFrais
        this.detailGestionFraisService = detailGestionFraisService
    }

    annuler() {
        this.$uibModalInstance.dismiss();
    }

    suppr() {
        this.$uibModalInstance.close();
        this.detailGestionFraisService.suppr(this.idFrais)
    }
}