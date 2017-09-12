export default class PopupController {
    constructor($uibModalInstance, $location) {
        this.$uibModalInstance = $uibModalInstance
        this.$location = $location
    }

    valider(successOrFailure) {
        this.$uibModalInstance.close();
        if (successOrFailure == 1) {
            this.$location.path('/detail')
        }
    }

    annuler() {
        this.$uibModalInstance.dismiss();
    }
}