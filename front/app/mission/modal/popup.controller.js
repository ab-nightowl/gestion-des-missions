export default class PopupController {
    constructor($uibModalInstance, $location, MissionService) {
        this.$uibModalInstance = $uibModalInstance
        this.$location = $location
        this.MissionService = MissionService
    }

    valider(successOrFailure) {
        this.$uibModalInstance.close();
        if (successOrFailure == 1) {
            this.$location.path('/missions/gestion')
        }
    }

    rester() {
        this.$uibModalInstance.dismiss();
    }
}
