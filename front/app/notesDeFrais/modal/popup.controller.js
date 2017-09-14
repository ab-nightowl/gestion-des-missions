export default class PopupController {
    constructor($uibModalInstance, $location, data, NatureMissionService, $rootScope, $window) {
        this.$uibModalInstance = $uibModalInstance
        this.$location = $location
        this.data = data
        this.NatureMissionService = NatureMissionService
        this.$rootScope =$rootScope
        this.$window = $window
    }

    valider(successOrFailure, redirectPath = '/detail') {
        this.$uibModalInstance.close();
        if (successOrFailure == 1) {
            this.$location.path(redirectPath)
        }
    }

    annuler() {
        this.$uibModalInstance.dismiss();
    }

    suppr(entityType, entityId) {
        if(entityType === "nature") {
            this.NatureMissionService.supprimerNature(entityId)
        }
        this.$uibModalInstance.close();
        this.$window.location.reload();
    }
}
