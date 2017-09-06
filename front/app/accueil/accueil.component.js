import './accueil.component.css';
import template from './accueil.component.html';

class controller {
    constructor (loginService) {
        this.loginService = loginService
        this.titre = 'Accueil';
    }
}

export let AccueilComponent = {
    template,
    controller,
    bindings: {}
};
