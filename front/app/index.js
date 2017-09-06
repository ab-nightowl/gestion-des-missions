import angular from 'angular';
import sha1 from 'sha1';
import RouteModule from 'angular-route';
import 'bootstrap/dist/css/bootstrap.css';
import '../public/assets/css/styles.css'
import uibootstrap from 'angular-ui-bootstrap';
import { route } from './app.route';
import { AccueilComponent } from './accueil/accueil.component';

// Modules
import notesDeFrais from '../notesDeFrais/notesDeFrais.module.js'
import login from './login/login.module'

// Constant
import apiUrls from './apiUrls.service'

angular.module('app', [RouteModule, uibootstrap, notesDeFrais.name, login.name])
    .value('apiUrl', API_URL)
    .value('sha1', sha1)
    .constant('apiUrls', apiUrls)
    .component('accueil', AccueilComponent)
    .config(route)