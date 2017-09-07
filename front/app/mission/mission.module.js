import creerMissionComponent from './creerMission.component'
import MissionService from './mission.service'

const missionModule = angular.module('missionModule', [])
  .component('gdmCreerMission', creerMissionComponent)
  .service(MissionService.name, MissionService)

export default missionModule
