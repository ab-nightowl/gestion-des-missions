export function route($routeProvider, $locationProvider) {

    $locationProvider.html5Mode(true);

    let userIsConnected = !!sessionStorage.getItem('session')

    // Ne pas toucher au code suivant
    $routeProvider
        .when('/', {
            template: userIsConnected ? '<accueil></accueil>' : '<login-component></login-component>'
        })
        .when('/missions/creer', {
            template: '<gdm-creer-mission></gdm-creer-mission>'
        .when('/ajout', {
            template: '<gdm-ajout></gdm-ajout>'
        })
        .when('/gestion', {
            template: '<gdm-gestion-frais></gdm-gestion-frais>'
        })
        .otherwise({
            redirectTo: '/'
        });

    if (userIsConnected) {
        $routeProvider
            .when('/ajout', {
                template: '<gdm-ajout></gdm-ajout>'
            })
            .when('/gestion', {
                template: '<gdm-gestion-frais></gdm-gestion-frais>'
            })
            // Ajouter les autres routes ici
            .when('/missions/creer', {
                template: '<gdm-creer-mission></gdm-creer-mission>'
            })

    }
}
