export default class gestionFraisCtrl {
    constructor(gestionFraisService) {
        this.gestionFraisService = gestionFraisService
        this.sumFraisMission()
        this.findMission()
        this.mission = []
    }

    findMission() {
        this.gestionFraisService.findMission()
            .then(mission => {
                mission.forEach(m => {
                    this.sumfrais.forEach(sf => {
                        if (sf.missionId === m.id) {
                            m.frais = sf.sumMontant
                            this.mission.push(m)
                        }
                    }, this);
                }, this);
            })
    }

    sumFraisMission() {
        this.gestionFraisService.sumFraisMission()
            .then(frais => {
                this.sumfrais = frais
            })
    }

}