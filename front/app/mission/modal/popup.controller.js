export default class PopupController {
    constructor($uibModalInstance, $location, MissionService, id, dateDebut, dateFin, nature, villeDepart, villeArrivee, transport, statut) {
        this.$uibModalInstance = $uibModalInstance
        this.$location = $location
        this.MissionService = MissionService
        this.id = id
        this.dateDebut = dateDebut
        this.dateFin = dateFin
        this.nature = nature
        this.villeDepart = villeDepart
        this.villeArrivee = villeArrivee
        this.transport = transport
        this.statut = statut
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

    supprimer() {
        this.$uibModalInstance.close();
        this.MissionService.deleteMission(this.id)
    }
}
