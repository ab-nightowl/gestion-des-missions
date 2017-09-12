export default class detailGestionFraisCtrl {
    constructor(detailGestionFraisService, $routeParams) {
        this.detailGestionFraisService = detailGestionFraisService
        this.idMission = $routeParams.msg
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


}