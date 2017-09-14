export default class detailGestionFraisCtrl {
    constructor(detailGestionFraisService, ajoutFraisService) {
        this.detailGestionFraisService = detailGestionFraisService
        this.idMission = sessionStorage.getItem('idMission')
        this.findDetailsMission()
        this.findFraisMission()
        this.ajoutFraisService = ajoutFraisService
    }


    findDetailsMission() {
        this.detailGestionFraisService.findMission(this.idMission)
            .then(dm => {
                this.detailMission = dm
            })
    }

    findFraisMission() {
        this.detailGestionFraisService.findFraisMission(this.idMission)
            .then(fm => {
                this.fraisMission = fm
            })
    }

    suppr(idFrais, dateCreationFrais, natureLibelle, montantFrais) {
        this.detailGestionFraisService.popupFraisSuppr(idFrais, dateCreationFrais, natureLibelle, montantFrais)
    }

    valider(){
        this.detailGestionFraisService.validerNoteDeFrais(this.detailMission)
    }


}