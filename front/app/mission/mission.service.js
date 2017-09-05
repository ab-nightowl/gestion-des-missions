export default class MissionService {
  constructor($http) {
    this.$http = $http
  }

  getAllMissions() {
    return this.$http.get('http://localhost:3000/missions')
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
    return this.$http.get('http://localhost:3000/naturesMission')
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
    return this.$http.get('http://localhost:3000/villes')
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
    return this.$http.get('http://localhost:3000/transports')
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

    return this.$http.post('http://localhost:3000/missions/', mission)
      .then((resp) => {return "La demande de mission a bien été envoyée"},
            (resp) => {return "Erreur : la demande de mission n'a pas pu être envoyée"}
      )

  }
}
