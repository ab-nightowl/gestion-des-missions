export default class PopupController {
    constructor($uibModalInstance, $location) {
        this.$uibModalInstance = $uibModalInstance
        this.$location = $location
    }

    annuler() {
        this.$uibModalInstance.dismiss();
    }

    valider(successOrFailure) {
        this.$uibModalInstance.close();
        if (successOrFailure == 1) {
            this.$location.path('/note-de-frais/gestion')
        }
    }
}