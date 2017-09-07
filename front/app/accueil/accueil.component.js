import './accueil.component.css';
import template from './accueil.component.html';

class controller {
    constructor (loginService) {
        this.loginService = loginService
        this.titre = "Bienvenue sur l'application GDM - Gestion Des Missions";
    }
}

export let AccueilComponent = {
    template,
    controller,
    bindings: {}
};
