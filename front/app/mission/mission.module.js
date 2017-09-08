import creerMissionComponent from './creerMission.component'
import listerMissionComponent from './listerMission.component'
import MissionService from './mission.service'

const missionModule = angular.module('missionModule', [])
  .component('gdmCreerMission', creerMissionComponent)
  .component('gdmListerMission', listerMissionComponent)
  .service(MissionService.name, MissionService)

export default missionModule
