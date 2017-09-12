export function route($routeProvider, $locationProvider, roles) {

    $locationProvider.html5Mode(true);

    $routeProvider
        .when('/', {
            template: '<accueil></accueil>',
            data: {
               role: [ roles.allAuthentificated ]
            }
        })
        .when('/ajout/:msg', {
            template: '<gdm-ajout></gdm-ajout>',
            data: {
               role: [ roles.allAuthentificated ]
            }
        })
        .when('/note-de-frais/gestion', {
            template: '<gdm-gestion-frais></gdm-gestion-frais>',
            data: {
               role: [ roles.allAuthentificated ]
            }
        })
        .when('/detail/:msg', {
            template: '<gdm-detail-gestion-frais></gdm-detail-gestion-frais>',
            data: {
               role: [ roles.allAuthentificated ]
            }
        })
        .when('/missions/creer', {
            template: '<gdm-creer-mission></gdm-creer-mission>',
            data: {
               role: [ roles.allAuthentificated ]
            }
        })
        .when('/missions/gestion', {
            template: '<gdm-lister-mission></gdm-lister-mission>',
            data: {
               role: [ roles.allAuthentificated ]
            }
        })
        .when('/prime', {
            template: '<prime-component></prime-component>',
            data: {
                role: [ roles.allAuthentificated ]
            }
        })
        .when('/connexion', {
            template: '<login-component></login-component>',
            data: {
               role: [ roles.all ]
            }
        })
        .otherwise({
            redirectTo: '/'
        })
}
