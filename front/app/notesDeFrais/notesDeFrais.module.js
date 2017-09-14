// Components
import ajoutFraisComponent from './ajoutFrais/ajoutFrais.component'
import gestionFraisComponent from './gestionFrais/gestionFrais.component'
import detailGestionFraisComponent from './detailGestionFrais/detailGestionFrais.component'
import modificationFraisComponent from './modificationFrais/modifFrais.component'
import ajoutFraisService from './ajoutFrais/ajoutFrais.service'
import gestionFraisService from './gestionFrais/gestionFrais.service'
import detailGestionFraisService from './detailGestionFrais/detailGestionFrais.service'
import modificationFraisService from './modificationFrais/modifFrais.service'

const noteDeFraisModule = angular.module('noteDeFraisModule', [])
.component('gdmAjout', ajoutFraisComponent)
.component('gdmGestionFrais', gestionFraisComponent)
.component('gdmDetailGestionFrais', detailGestionFraisComponent)
.component('gdmModif', modificationFraisComponent)
.service(ajoutFraisService.name, ajoutFraisService)
.service(gestionFraisService.name, gestionFraisService)
.service(detailGestionFraisService.name, detailGestionFraisService)
.service(modificationFraisService.name, modificationFraisService)


export default noteDeFraisModule