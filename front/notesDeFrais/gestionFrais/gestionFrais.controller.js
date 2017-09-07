export default class gestionFraisCtrl {
    constructor(gestionFraisService) {
        this.gestionFraisService = gestionFraisService
        this.sumFraisMission()
        this.findMission()
        this.mission = []
        this.dateNow = new Date();
        this.order = "dateDebut";
        this.triInverse = false;
    }

    findMission() {
        console.log(sessionStorage.getItem("userMatricule"))
        this.gestionFraisService.findMission(sessionStorage.getItem("userMatricule"))
            .then(mission => {
                mission.forEach(m => {
                    this.sumfrais.forEach(sf => {
                        if (sf.missionId === m.id) {
                            m.frais = sf.sumMontant
                        }
                        else {
                            if (m.frais == null || m.frais == 0) {
                                m.frais = 0
                            }
                        }
                    }, this);

                    this.mission.push(m)
                }, this);
            })
    }

    sumFraisMission() {
        this.gestionFraisService.sumFraisMission()
            .then(frais => {
                this.sumfrais = frais
            })
    }

    updateOrderEtTri(order) {
        this.order = order;
        this.triInverse = !this.triInverse;
    }

}