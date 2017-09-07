export default class MissionService {
  constructor($http, apiUrls) {
    this.$http = $http
    this.apiUrls = apiUrls
  }

  getAllMissions() {
    return this.$http.get(this.apiUrls.missions)
      .then((resp) => {
        return resp.data
      }, (resp) => {
				let errorStatus = {
					'code': resp.status,
					'text': resp.statusText
				}
				return errorStatus
      })
  }

  getAllNatures() {
    return this.$http.get(this.apiUrls.naturesMissions)
      .then((resp) => {
        return resp.data
      }, (resp) => {
				let errorStatus = {
					'code': resp.status,
					'text': resp.statusText
				}
				return errorStatus
      })
  }

  getAllVilles() {
    return this.$http.get(this.apiUrls.villes)
      .then((resp) => {
        return resp.data
      }, (resp) => {
				let errorStatus = {
					'code': resp.status,
					'text': resp.statusText
				}
				return errorStatus
      })
  }

  getAllTransports() {
    return this.$http.get(this.apiUrls.transports)
      .then((resp) => {
        return resp.data
      }, (resp) => {
				let errorStatus = {
					'code': resp.status,
					'text': resp.statusText
				}
				return errorStatus
      })
  }

  postMission(dateDebut, dateFin, nature, villeDepartId, villeArriveeId, transportId) {
    let mission = {
      "dateDebut": dateDebut,
      "dateFin": dateFin,
      "natureMissionInit": nature,
      "villeDepart": villeDepartId,
      "villeArrivee": villeArriveeId,
      "transport": transportId,
      "prime": 0
    }

    let tabMissions = []

    this.$http.post(this.apiUrls.missions, mission)
      .then((resp) => {alert("La demande de mission a bien été envoyée")},
            (resp) => {alert("Erreur : la demande de mission n'a pas pu être envoyée")}
      )

  }
}
