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
        minDate: this.dateDebut? this.dateDebut : new Date(),
        startingDay: 1
    };

    // Disable weekend selection
    function disabled(data) {
        let date = data.date,
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
      .then(tabMissions => {return this.missionsUtilisateur = tabMissions})
  }

  findAllMissions() {
    this.MissionService.getAllMissions()
      .then((tabMissions) => {
        this.missions = tabMissions
      })
  }

  findAllNatures() {
    this.MissionService.getAllNatures()
      .then((tabNatures) => {
        this.natures = tabNatures
      })
  }

  findAllVilles() {
    this.MissionService.getAllVilles()
      .then((tabVilles) => {
        this.villes = tabVilles
      })
  }

  findAllTransports() {
    this.MissionService.getAllTransports()
      .then((tabTransports) => {
        this.transports = tabTransports
      })
  }

  findNature(id) {
    return this.natures.find(nature => nature.id == id)
  }

  creerMission() {
    this.MissionService.postMission(this.dateDebut, this.dateFin, this.nature, this.villeDepart, this.villeArrivee, this.transport, this.statut, this.utilisateurMatricule)
      .then(success => {this.MissionService.popupSuccess()},
            error => {this.MissionService.popupFailure()})
  }

  changeDateDebut() {
    this.callTransportIsAvionCondition()
    if (!this.dateDebut) {
      this.dateOptions2.minDate = new Date()
    } else {
      this.dateOptions2.minDate = this.dateDebut
    }

    this.estimerPrime()
  }

  changeDateFin() {
    this.estimerPrime()
  }

  changeTransport() {
    this.callTransportIsAvionCondition()
    this.estimerPrime()
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

          this.dateDebut = null
          this.dateFin = null

          this.todayPlus7Days = todayPlus7Days.format('D/MM/YYYY')
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

  estimerPrime() {
    if (this.nature && this.dateDebut && this.dateFin) {
      // Récupération de l'objet nature correspondant au libellé choisi par l'utilisateur
      let nature = this.findNature(this.nature.id)
      // Calcul du nb jours mission
      let dateFin = this.moment(this.dateFin)
      let dateDebut = this.moment(this.dateDebut)
      let nbJoursMissions = dateFin.diff(dateDebut, 'days')+1
      // Calcul déduction des frais (estimation) = plafond note de frais * nb jours missions
      let deduction = nature.plafondFrais * nbJoursMissions
      // Calcul estimation prime = nbJoursMission * tjm * tauxPrime(%) - deduction
      this.estimationPrime = nbJoursMissions * nature.tjm * nature.tauxPrime - deduction
    }
  }

}
