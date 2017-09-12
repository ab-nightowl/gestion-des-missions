export default class detailGestionFraisCtrl {
    constructor(detailGestionFraisService) {
        this.detailGestionFraisService = detailGestionFraisService
        this.idMission = sessionStorage.getItem('idMission')
        this.findDetailsMission()
        this.findFraisMission()

    }


    findDetailsMission(){
        this.detailGestionFraisService.findMission(this.idMission)
            .then(dm => {
                this.detailMission = dm
            })
    }

    findFraisMission(){
        this.detailGestionFraisService.findFraisMission(this.idMission)
            .then(fm => {
                this.fraisMission = fm
            })
    }

    suppr(idFrais, dateCreationFrais, natureLibelle, montantFrais){
        this.detailGestionFraisService.popupFraisSuppr(idFrais, dateCreationFrais, natureLibelle, montantFrais)
    }


}