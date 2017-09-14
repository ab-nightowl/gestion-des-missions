export default class detailGestionFraisCtrl {
    constructor(detailGestionFraisService, ajoutFraisService, moment) {
        this.detailGestionFraisService = detailGestionFraisService
        this.moment = moment
        this.idMission = sessionStorage.getItem('idMission')
        this.findDetailsMission()
        this.ajoutFraisService = ajoutFraisService
    }

    findDetailsMission() {
        this.detailGestionFraisService.findMission(this.idMission)
            .then(dm => {
                this.detailMission = dm
            })
            .then(() => {
                this.findFraisMission()
            })
    }

    findFraisMission() {
        this.detailGestionFraisService.findFraisMission(this.idMission)
            .then(fm => {
                this.fraisMission = fm
            })
            .then(() => {
                this.calculDeductionPrime()
            })
    }

    suppr(idFrais, dateCreationFrais, natureLibelle, montantFrais) {
        this.detailGestionFraisService.popupFraisSuppr(idFrais, dateCreationFrais, natureLibelle, montantFrais)
    }

    valider() {
        if (this.sommeFrais >= (this.plafond * this.nbJour[0])) {
            if (this.detailMission.natureMissionInit.depassementFrais) {
                this.detailGestionFraisService.validerNoteDeFrais(this.detailMission)
                this.detailGestionFraisService.popupSuccess()
            } else {
                this.detailGestionFraisService.popupFailure()
            }
        } else {
            this.detailGestionFraisService.validerNoteDeFrais(this.detailMission)
            this.detailGestionFraisService.popupSuccess()
        }
    }

    calculDeductionPrime() {
        this.sommeFrais = 0
        this.fraisMission.forEach(element => {
            this.sommeFrais += element.montant
        });

        this.plafond = this.detailMission.natureMissionInit.plafondFrais

        let debutMission = this.moment(this.detailMission.dateDebut)
        let finMission = this.moment(this.detailMission.dateFin)
        let nbJourMission = debutMission.to(finMission, true)
        this.nbJour = nbJourMission.split(' ')

        this.deductionPrime = this.sommeFrais - (this.plafond * this.nbJour[0])
    }
}