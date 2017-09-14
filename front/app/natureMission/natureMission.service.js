import popupCreationNatureSuccess from "../notesDeFrais/modal/ajoutNatureSuccess.html"
import popupCreationNatureFailure from "../notesDeFrais/modal/ajoutNatureFailure.html"
import popupSuppressionNature from "../notesDeFrais/modal/natureMissionSuppr.html"
import popupCtrl from "../notesDeFrais/modal/popup.controller"

export default class NatureMissionService {
  constructor($http, $uibModal, apiUrls) {
    this.$http = $http
    this.$uibModal = $uibModal
    this.apiUrls = apiUrls
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

  creerNature(nature) {
    return this.$http.post(`${this.apiUrls.naturesMissions}/creer`, nature)
      .then(
      (resp) => {
        return resp.data.msg
      })
  }

  supprimerNature(natureId) {
    return this.$http.post(`${this.apiUrls.naturesMissions}/supprimer/${natureId}`)
      .then(
      (resp) => {
        return resp.data.msg
      })
  }

  popupCreationNatureSuccess() {
    this.$uibModal.open({
      template: popupCreationNatureSuccess,
      controller: popupCtrl,
      controllerAs: '$ctrl',
      resolve: {
        data: () => "-"
      }
    });
  }
  popupCreationNatureFailure(errorMsg) {
    this.$uibModal.open({
      template: popupCreationNatureFailure,
      controller: popupCtrl,
      controllerAs: '$ctrl',
      resolve: {
        data: () => {
          return { errorMsg: errorMsg }
        }
      }
    });
  }

  popupSuppressionNature(nature) {
    this.$uibModal.open({
      template: popupSuppressionNature,
      controller: popupCtrl,
      controllerAs: '$ctrl',
      resolve: {
        data: () => {
          return { nature: nature }
        }
      }
    });
  }
}
