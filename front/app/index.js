import angular from 'angular';
import RouteModule from 'angular-route';
import 'bootstrap/dist/css/bootstrap.css';
import uibootstrap from 'angular-ui-bootstrap';
import { route } from './app.route';
import { AccueilComponent } from './accueil/accueil.component';

//Modules
import notesDeFrais from '../notesDeFrais/notesDeFrais.module.js'
// constant
import apiUrls from './apiUrls.service.js'



angular.module('app', [RouteModule, uibootstrap, notesDeFrais.name])
    .value('apiUrl', API_URL)
    .component('accueil', AccueilComponent)
    .constant('apiUrls', apiUrls)
    .config(route);
