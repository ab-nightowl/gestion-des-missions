import listerNatureMissionComponent from './listerNatureMission.component'
import creerNatureMissionComponent from './creerNatureMission.component'
import NatureMissionService from './natureMission.service'

const natureMissionModule = angular.module('natureMissionModule', [])
  .component('gdmListerNatureMission', listerNatureMissionComponent)
  .component('gdmCreerNatureMission', creerNatureMissionComponent)
  .service(NatureMissionService.name, NatureMissionService)

export default natureMissionModule
