export default class ajoutFraisCtrl {
    constructor(ajoutFraisService) {
        this.ajoutFraisService = ajoutFraisService
        this.findNaturesFrais()
        this.var = "blabla coucou"

        this.today();
        this.inlineOptions = {
            customClass: getDayClass,
            minDate: new Date(),
            showWeeks: true
        };

        this.dateOptions = {
            dateDisabled: disabled,
            formatYear: 'yy',
            maxDate: new Date(2020, 5, 22),
            minDate: new Date(),
            startingDay: 1
        };
        // Disable weekend selection
        function disabled(data) {
            var date = data.date,
                mode = data.mode;
            return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
        }
        this.toggleMin();
        this.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        this.format = this.formats[0];
        this.altInputFormats = ['M!/d!/yyyy'];

        this.popup2 = {
            opened: false
        };

        var tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        var afterTomorrow = new Date();
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
            var date = data.date,
                mode = data.mode;
            if (mode === 'day') {
                var dayToCheck = new Date(date).setHours(0, 0, 0, 0);

                for (var i = 0; i < this.events.length; i++) {
                    var currentDay = new Date(this.events[i].date).setHours(0, 0, 0, 0);

                    if (dayToCheck === currentDay) {
                        return this.events[i].status;
                    }
                }
            }

            return '';
        }
    }

    findNaturesFrais() {
        this.ajoutFraisService.findNaturesFrais()
            .then(naturesFrais => {
                this.natures = naturesFrais
            })
    }

    saveFrais() {
        
        console.log(this.date)
        console.log(this.nature)
        console.log(this.montant)
        this.ajoutFraisService.saveNew(this.date, this.nature, this.montant)
    }

    today() {
        this.dt = new Date();
    };

    clear() {
        this.dt = null;
    };

    toggleMin() {
        this.inlineOptions.minDate = this.inlineOptions.minDate ? null : new Date();
        this.dateOptions.minDate = this.inlineOptions.minDate;
    };
    open2() {
        this.popup2.opened = true;
    };

    setDate(year, month, day) {
        this.dt = new Date(year, month, day);
    };

}