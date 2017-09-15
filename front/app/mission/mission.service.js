import popupSuccess from "./modal/creerMissionSuccess.html"
import popupFailure from "./modal/creerMissionFailure.html"
import popupSuppressionSuccess from "./modal/supprimerMissionSuccess.html"

import popupCtrl from "./modal/popup.controller"

export default class MissionService {
  constructor($http, apiUrls, $uibModal, $window) {
    this.$http = $http
    this.apiUrls = apiUrls
    this.$uibModal = $uibModal
    this.$window = $window
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
    return this.$http.get(`${this.apiUrls.naturesMissions}/lister`)
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
      // Display error somehow
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

  deleteMission(id) {
    this.$http.delete(this.apiUrls.missions+'/'+id)
    this.$window.location.reload()
  }

  popupSuccess() {
    this.$uibModal.open({
      template: popupSuccess,
      controller: popupCtrl,
      controllerAs: '$ctrl'
    });
  }
  popupFailure() {
    this.$uibModal.open({
      template: popupFailure,
      controller: popupCtrl,
      controllerAs: '$ctrl'
    });
  }

  popupSuppressionSuccess(id, dateDebut, dateFin, nature, villeDepart, villeArrivee, transport, statut) {
    this.$uibModal.open({
        template: popupSuppressionSuccess,
        controller: popupCtrl,
        controllerAs: '$ctrl',
        resolve: {
            "id": () => id,
            "dateDebut": () => dateDebut,
            "dateFin": () => dateFin,
            "nature": () => nature,
            "villeDepart": () => villeDepart,
            "villeArrivee": () => villeArrivee,
            "transport": () => transport,
            "statut": () => statut
        }
    })
  }

  findMissionsByUtilisateur(utilisateurMatricule) {
    return this.$http.get(this.apiUrls.missions+'/'+utilisateurMatricule)
      .then(resp => {return resp.data},
            resp => {return "Erreur : la requête pour trouver les missions de l'utilisateur courant a échouée"})
      .then(missions => {
        return this.$http.get(this.apiUrls.absences+'/'+utilisateurMatricule)
        .then(resp => {
          this.addAbsencesToMissions(resp.data, missions)
          this.addActions(missions)
          return missions
          }, resp => {return "Erreur : la requête pour trouver les absences de l'utilisateur courant a échouée"}
        )
      })
  }

  addAbsencesToMissions(absences, missions) {
    absences.forEach(abs => {
      missions.push({
        "dateDebut": abs.dateDebut,
        "dateFin": abs.dateFin,
        "natureMissionInit": {
          "libelle": abs.type
        },
        "statut": abs.statut
      })
    })
  }

  // Permet de définir quelles actions sont possibles lors de la visualisation des missions
  // Rajoute un tableau d'actions à la mission
  addActions(missions) {
      missions.forEach(m => {
          m.actions = []

          if(m.natureMissionInit.libelle != "MISSION" && (m.statut == "INITIALE" || m.statut == "REJETEE")) {
              m.actions.push("modification")
          }

          if(m.natureMissionInit.libelle != "MISSION") {
              let now = Date.now()
              let dateDebut = new Date(m.dateDebut);
              if(dateDebut >= now) {
                  m.actions.push("suppression")
              }
          }

          if(m.natureMissionInit.libelle == "MISSION") {
              m.actions.push("visualisation")
          }
      })
  }
}
