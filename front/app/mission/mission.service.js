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

  putMission(dateDebut, dateFin, natureId, villeDepartId, villeArriveeId, transportId) {
    let mission = {
      "dateDebut": dateDebut,
      "dateFin": dateFin,
      "naturesMissionInitId": natureId,
      "naturesMissionId": null,
      "villeDepartId": villeDepartId,
      "villeArriveeId": villeArriveeId,
      "transportId": transportId,
      "prime": 0,
      "utilisateurId": 1,
      "statut": "Initial",
      "frais": []
    }

    let tabMissions = []

    return this.$http.post(this.apiUrls.missions, mission)
      .then((resp) => {return "La demande de mission a bien été envoyée"},
            (resp) => {return "Erreur : la demande de mission n'a pas pu être envoyée"}
      )

  }
}
