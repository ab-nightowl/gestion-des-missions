export function route($routeProvider, $locationProvider, roles) {

    $locationProvider.html5Mode(true);

    $routeProvider
        .when('/', {
            template: '<accueil></accueil>',
            data: {
                role: [roles.allAuthentificated]
            }
        })
        .when('/ajout', {
            template: '<h1>Nouveau Frais</h1><gdm-ajout></gdm-ajout>',
            data: {
                role: [roles.allAuthentificated]
            }
        })
        .when('/modification/:msg', {
            template: "<h1>Modification d'un Frais</h1><gdm-modif></gdm-modif>",
            data: {
                role: [roles.allAuthentificated]
            }
        })
        .when('/note-de-frais/gestion', {
            template: '<gdm-gestion-frais></gdm-gestion-frais>',
            data: {
                role: [roles.allAuthentificated]
            }
        })
        .when('/detail', {
            template: '<gdm-detail-gestion-frais></gdm-detail-gestion-frais>',
            data: {
                role: [roles.allAuthentificated]
            }
        })
        .when('/missions/creer', {
            template: '<gdm-creer-mission></gdm-creer-mission>',
            data: {
                role: [roles.allAuthentificated]
            }
        })
        .when('/missions/gestion', {
            template: '<gdm-lister-mission></gdm-lister-mission>',
            data: {
                role: [roles.allAuthentificated]
            }
        })
        .when('/prime', {
            template: '<prime-component></prime-component>',
            data: {
                role: [roles.allAuthentificated]
            }
        })
        .when('/missions/natures', {
            template: '<gdm-lister-nature-mission></gdm-lister-nature-mission>',
            data: {
                role: [roles.admin]
            }
        })
        .when('/missions/natures/creer', {
            template: '<gdm-creer-nature-mission></gdm-creer-nature-mission>',
            data: {
                role: [roles.admin]
            }
        })
        .when('/connexion', {
            template: '<login-component></login-component>',
            data: {
                role: [roles.allNotAuthentificated]
            }
        })
        .otherwise({
            redirectTo: '/'
        })
}
