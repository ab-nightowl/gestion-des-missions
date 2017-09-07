import angular from 'angular';
import RouteModule from 'angular-route';
import missionModule from './mission/mission.module';

import apiUrls from './apiUrls.service';

import 'bootstrap/dist/css/bootstrap.css';
import uibootstrap from 'angular-ui-bootstrap';
import { route } from './app.route';
import { AccueilComponent } from './accueil/accueil.component';

angular.module('app', [RouteModule, uibootstrap, missionModule.name])
.value( 'apiUrl', API_URL)
.constant('apiUrls', apiUrls)
.component('accueil', AccueilComponent)
.config(route);
