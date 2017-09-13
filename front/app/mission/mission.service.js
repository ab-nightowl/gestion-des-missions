import popupSuccess from "./modal/creerMissionSuccess.html"
import popupFailure from "./modal/creerMissionFailure.html"
import popupCtrl from "./modal/popup.controller"

export default class MissionService {
  constructor($http, apiUrls, $uibModal) {
    this.$http = $http
    this.apiUrls = apiUrls
    this.$uibModal = $uibModal
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

  postMission(dateDebut, dateFin, nature, villeDepart, villeArrivee, transport, statut, utilisateurMatricule) {
    if (!dateDebut || !dateFin || !nature || !villeDepart || !villeArrivee || !transport || !statut || !utilisateurMatricule) {
      alert("Vous devez remplir tous les champs")
    } else {

      let mission = {
        "dateDebut": dateDebut,
        "dateFin": dateFin,
        "natureMissionInit": nature,
        "villeDepart": villeDepart,
        "villeArrivee": villeArrivee,
        "transport": transport,
        "prime": null,
        "statut": statut,
        "utilisateurMatricule": utilisateurMatricule
      }

      let tabMissions = []

      return this.$http.post(this.apiUrls.missions, mission)
    }

  }

  popupSuccess(){
    this.$uibModal.open({
      template: popupSuccess,
      controller: popupCtrl,
      controllerAs: '$ctrl'
    });
  }
  popupFailure(){
    this.$uibModal.open({
      template: popupFailure,
      controller: popupCtrl,
      controllerAs: '$ctrl'
    });
  }

  findMissionsByUtilisateur(utilisateurMatricule) {
    return this.$http.get(this.apiUrls.missions+'/'+utilisateurMatricule)
      .then(resp => {return resp.data},
            resp => {return "Erreur : la requête pour trouver les missions de l'utilisateur courant a échouée"})
  }

}
