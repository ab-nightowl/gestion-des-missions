export default class MissionController {
  constructor(MissionService, moment) {
    this.MissionService = MissionService
    this.moment = moment
    this.order = "dateDebut";
    this.triInverse = false;

    // Date Options for date de début
    this.dateOptions = {
        dateDisabled: disabled,
        formatYear: 'yy',
        maxDate: new Date(2020, 5, 22),
        minDate: new Date(),
        startingDay: 1
    };
    // Date Options for date de fin
    this.dateOptions2 = {
        dateDisabled: disabled,
        formatYear: 'yy',
        maxDate: new Date(2020, 5, 22),
        minDate: this.dateDebut,
        startingDay: 1
    };

    // Disable weekend selection
    function disabled(data) {
        var date = data.date,
            mode = data.mode;
        return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
    }

    this.formats = ['dd/MM/yyyy', 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    this.format = this.formats[0];
    this.altInputFormats = ['M!/d!/yyyy'];

    this.popup1 = {
        opened: false
    };
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

  $onInit() {
    this.statut = "DEMANDE_INITIALE"
    this.utilisateurMatricule = sessionStorage.getItem('userMatricule')
    this.findMissionsUtilisateur()
    this.findAllMissions()
    this.findAllNatures()
    this.findAllVilles()
    this.findAllTransports()
  }


  findMissionsUtilisateur() {
    this.MissionService.findMissionsByUtilisateur(this.utilisateurMatricule)
      .then(tabMissions => {return this.missionsUtilisateur = tabMissions},
            info => alert(info))
  }

  findAllMissions() {
    this.MissionService.getAllMissions()
      .then((tabMissions) => {
        this.missions = tabMissions
      }, (errorStatus) => {
				alert(`Status: $(errorStatus.code) - $(errorStatus.text)`)
			})
  }

  findAllNatures() {
    this.MissionService.getAllNatures()
      .then((tabNatures) => {
        this.natures = tabNatures
      }, (errorStatus) => {
				alert(`Status: $(errorStatus.code) - $(errorStatus.text)`)
			})
  }

  findAllVilles() {
    this.MissionService.getAllVilles()
      .then((tabVilles) => {
        this.villes = tabVilles
      }, (errorStatus) => {
				alert(`Status: $(errorStatus.code) - $(errorStatus.text)`)
			})
  }

  findAllTransports() {
    this.MissionService.getAllTransports()
      .then((tabTransports) => {
        this.transports = tabTransports
      }, (errorStatus) => {
				alert(`Status: $(errorStatus.code) - $(errorStatus.text)`)
			})
  }

  creerMission() {
    this.MissionService.postMission(this.dateDebut, this.dateFin, this.nature, this.villeDepart, this.villeArrivee, this.transport, this.statut, this.utilisateurMatricule)
      .then(success => {this.MissionService.popupSuccess()},
            error => {this.MissionService.popupFailure()})
  }

  changeDateDebutOptions() {
    this.callTransportIsAvionCondition()
  }

  callTransportIsAvionCondition() {
    let today = this.moment().subtract(1, 'days').endOf('day')
    let todayPlus7Days = this.moment().startOf('day').add(7, 'days')

    if (this.dateDebut) {
      if (this.transport) {

        this.transportIsAvionCondition = (this.transport.libelle == "Avion")
        this.dateCondition = this.moment(this.dateDebut).isBetween(today, todayPlus7Days)

        if (this.transportIsAvionCondition && this.dateCondition) {
          this.dateOptions.minDate = todayPlus7Days.toDate()
          this.dateOptions2.minDate = this.dateOptions.minDate

          this.dateDebut = this.moment(this.dateDebut).add(7, 'days').toDate()
          this.dateFin = null
        } else if (this.transportIsAvionCondition) {
          this.dateOptions.minDate = todayPlus7Days.toDate()
          this.dateOptions2.minDate = this.dateOptions.minDate
        } else {
          this.dateOptions.minDate = this.moment().toDate()
          this.dateOptions2.minDate = this.dateOptions.minDate
        }
      }

    }
  }

  changeDateFinOptions() {
    this.callTransportIsAvionCondition()
    this.dateOptions2.minDate = this.dateDebut
  }

  updateOrderEtTri(order) {
      this.order = order;
      this.triInverse = !this.triInverse;
  }

  open1() {
      this.popup1.opened = true;
  };
  open2() {
      this.popup2.opened = true;
  };

}
