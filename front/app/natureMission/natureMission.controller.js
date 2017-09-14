export default class NatureMissionController {
    constructor($location, NatureMissionService, $scope, $rootScope) {
        this.$location = $location
        this.NatureMissionService = NatureMissionService

        this.$scope = $scope
        this.$rootScope = $rootScope
        this.defaultNature = {
            libelle: "",
            facture: "",
            versementPrime: "",
            tjm: "",
            plafondFrais: "",
            depassementFrais: "",
            tauxPrime: ""
        }

        this.updateListNatures()
    }

    updateListNatures() {
        this.NatureMissionService.getAllNatures().then(natures => this.natures = natures.filter(nature => nature.actif === true))
    }

    creerNature(nature) {
        this.NatureMissionService.creerNature(nature).then(respMsg => {
            if (respMsg === "success") {
                this.NatureMissionService.popupCreationNatureSuccess()
                this.resetForm()
            } else {
                this.NatureMissionService.popupCreationNatureFailure(respMsg)
            }
        })
    }

    confirmSuppressionNature(nature) {
        this.NatureMissionService.popupSuppressionNature(nature)
        // this.NatureMissionService.supprimerNature(nature).then(resp => this.updateListNatures())
    }

    resetForm() {
        this.$scope.formCreerNatureMission.$setPristine()
        this.nature = angular.copy(this.defaultNature);
    }
}
