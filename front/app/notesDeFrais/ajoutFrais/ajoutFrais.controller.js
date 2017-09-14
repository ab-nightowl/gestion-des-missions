export default class ajoutFraisCtrl {
    constructor(ajoutFraisService, detailGestionFraisService, $location, moment) {
        this.$location = $location
        this.ajoutFraisService = ajoutFraisService
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
        this.ajoutFraisService.redirection()
    }

    findNaturesFrais() {
        this.ajoutFraisService.findNaturesFrais()
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
            this.ajoutFraisService.saveNew(dateCreation, this.frais.nature, this.frais.montant, this.idMission)
            this.ajoutFraisService.popupSuccess()
        } else {
            this.ajoutFraisService.popupFailure()
        }
    }

    today() {
        this.dt = new Date();
    };
    clear() {
        this.dt = null;
    };
    open2() {
        this.popup2.opened = true;
    };

    setDate(year, month, day) {
        this.dt = new Date(year, month, day);
    };
}