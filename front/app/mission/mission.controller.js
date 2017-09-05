export default class MissionController {
  constructor(MissionService) {
    this.MissionService = MissionService
  }

  $onInit() {
    this.MissionService.getAllMissions()
      .then((tabMissions) => {
        this.missions = tabMissions
      }, (errorStatus) => {
				alert(`Status: $(errorStatus.code) - $(errorStatus.text)`)
			})

    this.MissionService.getAllNatures()
      .then((tabNatures) => {
        this.natures = tabNatures
      }, (errorStatus) => {
				alert(`Status: $(errorStatus.code) - $(errorStatus.text)`)
			})

    this.MissionService.getAllVilles()
      .then((tabVilles) => {
        this.villes = tabVilles
      }, (errorStatus) => {
				alert(`Status: $(errorStatus.code) - $(errorStatus.text)`)
			})

    this.MissionService.getAllTransports()
      .then((tabTransports) => {
        this.transports = tabTransports
      }, (errorStatus) => {
				alert(`Status: $(errorStatus.code) - $(errorStatus.text)`)
			})


  }

  creerMission() {
    this.MissionService.putMission(this.dateDebut, this.dateFin, this.nature.id, this.villeDepart.id, this.villeArrivee.id, this.transport.id)
      .then((info) => {alert(info)},
            (info) => {alert(info)}
      )
  }
}
