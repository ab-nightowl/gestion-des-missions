import angular from 'angular';
import sha1 from 'sha1';
import RouteModule from 'angular-route';

import 'bootstrap/dist/css/bootstrap.css';
import './assets/css/styles.css'
import uibootstrap from 'angular-ui-bootstrap';

import moment from 'moment'

import { route } from './app.route';
import { AccueilComponent } from './accueil/accueil.component';
import HeaderComponent from './header/header.component';

// Modules
import notesDeFrais from '../notesDeFrais/notesDeFrais.module.js'
import login from './login/login.module'
import missionModule from './mission/mission.module';

// Constant
import apiUrls from './apiUrls.service'

import pdfmake from 'pdfmake/build/pdfmake.js'
import pdfFonts from "pdfmake/build/vfs_fonts";
pdfMake.vfs = pdfFonts.pdfMake.vfs;

angular.module('app', [RouteModule, uibootstrap, notesDeFrais.name, login.name,
    missionModule.name])
    .value('pdfMake', pdfmake)
    .value('apiUrl', API_URL)
    .value('publicPath', publicPath)
    .value('sha1', sha1)
    .constant('apiUrls', apiUrls)
    .constant('moment', moment)
    .component('accueil', AccueilComponent)
    .component('header', HeaderComponent)
    .config(route)
