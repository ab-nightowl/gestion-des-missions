// Components
import ajoutFraisComponent from './ajoutFrais/ajoutFrais.component'
import gestionFraisComponent from './gestionFrais/gestionFrais.component'
import ajoutFraisService from './ajoutFrais/ajoutFrais.service'
import gestionFraisService from './gestionFrais/gestionFrais.service'

const noteDeFraisModule = angular.module('noteDeFraisModule', [])
.component('gdmAjout', ajoutFraisComponent)
.component('gdmGestionFrais', gestionFraisComponent)
.service(ajoutFraisService.name, ajoutFraisService)
.service(gestionFraisService.name, gestionFraisService)


export default noteDeFraisModule