export default class modifFraisCtrl {
    constructor(modifFraisService, detailGestionFraisService, $location, moment) {
        this.$location = $location
        this.modifFraisService = modifFraisService
        this.detailGestionFraisService = detailGestionFraisService
        this.moment = moment
        this.idMission = sessionStorage.getItem('idMission')
        this.dbMission = sessionStorage.getItem('dateDebut')
        this.dfMission = sessionStorage.getItem('dateFin')
        this.findNaturesFrais()
        this.detailGestionFraisService.findFraisMission(this.idMission)
            .then(fm => {
                this.fraisMission = fm
            })

        this.fraisCurrent = $location.path().split('/')
        this.idFrais = this.fraisCurrent[2]
        this.modif()

        /** date picker */
        this.dateOptions = {
            dateDisabled: disabled,
            formatYear: 'yy',
            maxDate: new Date(this.dfMission),
            minDate: new Date(this.dbMission),
            startingDay: 1
        };
        // Disable weekend selection
        function disabled(data) {
            let date = data.date,
                mode = data.mode;
            return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
        }
        this.popup2 = {
            opened: false
        };
    }

    redirection() {
        this.modifFraisService.redirection()
    }

    findNaturesFrais() {
        this.modifFraisService.findNaturesFrais()
            .then(naturesFrais => {
                this.natures = naturesFrais
            })
    }

    saveFrais() {
        let dateCreation = this.moment(this.frais.dateCreation).format('YYYY-MM-DD')
        let count = 0
        this.fraisMission.forEach(frais => {
            if (frais.dateCreation == dateCreation && frais.nature.id == this.frais.nature.id) {
                if (frais.id != this.frais.id) {
                    count += 1
                }
            }
        });
        if (count == 0) {
            this.modifFraisService.saveModif(this.frais.id, dateCreation, this.frais.nature, this.frais.montant, this.idMission)
            this.modifFraisService.popupSuccess()
        } else {
            this.modifFraisService.popupFailure()
        }
    }
    clear() {
        this.dt = null;
    };
    open2() {
        this.popup2.opened = true;
    };

    modif() {
        this.modifFraisService.findFrais(this.idFrais)
            .then(frais => {
                this.frais = frais
            })
    }
}