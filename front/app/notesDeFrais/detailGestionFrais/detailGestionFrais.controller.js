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
        let debutMission = new Date(this.detailMission.dateDebut)
        let finMission = new Date(this.detailMission.dateFin)
        this.nbJourMission = this.dateDiff(debutMission, finMission)
        this.deductionPrime = this.sommeFrais - (this.plafond * this.nbJourMission.day)
    }

    dateDiff(date1, date2) {
        var diff = {}                           // Initialisation du retour
        var tmp = date2 - date1;

        tmp = Math.floor(tmp / 1000);             // Nombre de secondes entre les 2 dates
        diff.sec = tmp % 60;                    // Extraction du nombre de secondes

        tmp = Math.floor((tmp - diff.sec) / 60);    // Nombre de minutes (partie entière)
        diff.min = tmp % 60;                    // Extraction du nombre de minutes

        tmp = Math.floor((tmp - diff.min) / 60);    // Nombre d'heures (entières)
        diff.hour = tmp % 24;                   // Extraction du nombre d'heures

        tmp = Math.floor((tmp - diff.hour) / 24);   // Nombre de jours restants
        diff.day = tmp;

        return diff;
    }
}