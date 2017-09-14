import angular from 'angular';
import sha1 from 'sha1';
import chartjs from "chart.js"
import RouteModule from 'angular-route';

import 'bootstrap/dist/css/bootstrap.css';
import './assets/css/styles.css'
import uibootstrap from 'angular-ui-bootstrap';
import { route } from './app.route';
import { AccueilComponent } from './accueil/accueil.component';
import HeaderComponent from './header/header.component';
import PrimeComponent from './prime/prime.component';

// Modules
import notesDeFrais from './notesDeFrais/notesDeFrais.module.js'
import login from './login/login.module'
import missionModule from './mission/mission.module';
import natureMissionModule from './natureMission/natureMission.module';
import primeModule from './prime/prime.module';

// Constant
import apiUrls from './apiUrls.service'

import pdfmake from 'pdfmake/build/pdfmake.js'
import pdfFonts from 'pdfmake/build/vfs_fonts';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

angular.module('app', [RouteModule, uibootstrap, notesDeFrais.name, login.name,
    missionModule.name, natureMissionModule.name, primeModule.name])
    .value('pdfMake', pdfmake)
    .value('apiUrl', API_URL)
    .value('publicPath', publicPath)
    .value('sha1', sha1)
    .value('chartjs', chartjs)
    .constant('apiUrls', apiUrls)
    .component('accueil', AccueilComponent)
    .component('header', HeaderComponent)
    .config(route)
    .constant('roles', {
        all: "*",
        allAuthentificated: "auth",
        admin: "ROLE_ADMINISTRATEUR",
        manager: "ROLE_MANAGER",
        employe: "ROLE_EMPLOYE"
    })
    .run(['$rootScope', '$location', 'loginService', 'roles', function ($rootScope, $location, loginService, roles) {
        $rootScope.$on('$routeChangeStart', function (event, next, current) {
            if (!next.data || next.data.role[0] === roles.all) {
                // All good to go
            } else if (loginService.isConnected()) {
                if (next.data.role[0] !== roles.allAuthentificated) {
                    let userRole = loginService.getUserRole()
                    if (!userRole) {
                        loginService.retrieveUserRole(loginService.getUserEmail()).then(role => {
                            if (next.data.role.indexOf(role) === -1) {
                                $location.path('/')
                            }
                        })
                    } else {
                        if (next.data.role.indexOf(userRole) === -1) {
                            $location.path('/')
                        }
                    }
                }
            } else {
                $location.path('/connexion')
            }
        });
    }])