export default class ajoutFraisCtrl {
    constructor(ajoutFraisService, detailGestionFraisService) {
        this.ajoutFraisService = ajoutFraisService
        this.detailGestionFraisService = detailGestionFraisService
        this.idMission = sessionStorage.getItem('idMission')
        this.dbMission = sessionStorage.getItem('dateDebut')
        this.dfMission = sessionStorage.getItem('dateFin')
        this.findNaturesFrais()
        this.detailGestionFraisService.findFraisMission(this.idMission)
            .then(fm => {
                this.fraisMission = fm
            })

        this.today = new Date();
        this.inlineOptions = {
            customClass: getDayClass,
            minDate: new Date(),
            showWeeks: true
        };

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

        this.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        this.format = this.formats[0];
        this.altInputFormats = ['M!/d!/yyyy'];

        this.popup2 = {
            opened: false
        };

        let tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        let afterTomorrow = new Date();
        afterTomorrow.setDate(tomorrow.getDate() + 1);
        this.events = [
            {
                date: tomorrow,
                status: 'full'
            },
            {
                date: afterTomorrow,
                status: 'partially'
            }
        ];

        function getDayClass(data) {
            let date = data.date,
                mode = data.mode;
            if (mode === 'day') {
                let dayToCheck = new Date(date).setHours(0, 0, 0, 0);

                for (let i = 0; i < this.events.length; i++) {
                    let currentDay = new Date(this.events[i].date).setHours(0, 0, 0, 0);

                    if (dayToCheck === currentDay) {
                        return this.events[i].status;
                    }
                }
            }

            return '';
        }
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

    info() {
        let year = this.date.getFullYear()
        let month = this.formatdate(this.date.getMonth() + 1)
        let day = this.formatdate(this.date.getDate())

        this.date2 = year + "-" + month + "-" + day
    }
    formatdate(number){
        if (number < 10){
            return "0"+number;
        }
        else{
            return number;
        }
    }

    saveFrais() {
        this.info()
        let count = 0
        this.fraisMission.forEach(frais => {
            if (frais.dateCreation == this.date2 && frais.nature.id == this.nature) {
                count += 1
            }
        });
        if (count == 0) {
            this.ajoutFraisService.saveNew(this.date2, this.nature, this.montant, this.idMission)
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