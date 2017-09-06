// Components
import ajoutFraisComponent from './ajoutFrais/ajoutFrais.component'
import ajoutFraisService from './ajoutFrais/ajoutFrais.service'

const noteDeFraisModule = angular.module('noteDeFraisModule', [])
.component('gdmAjout', ajoutFraisComponent)
.service(ajoutFraisService.name, ajoutFraisService)


export default noteDeFraisModule