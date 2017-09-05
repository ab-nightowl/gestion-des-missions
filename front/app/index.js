import angular from 'angular';
import RouteModule from 'angular-route';
import missionModule from './mission/mission.module';


import 'bootstrap/dist/css/bootstrap.css';
import { route } from './app.route';
import { AccueilComponent } from './accueil/accueil.component';

angular.module('app', [RouteModule, missionModule.name])
.value( 'apiUrl', API_URL)
.component('accueil', AccueilComponent)
.config(route);
