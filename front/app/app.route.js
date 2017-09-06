export function route($routeProvider, $locationProvider) {

    $locationProvider.html5Mode(true);

    if (sessionStorage.getItem('session')) {
        $routeProvider
            .when('/ajout', {
                template: '<gdm-ajout></gdm-ajout>'
            })
            // Ajouter les autres routes ici
    }

    // Ne pas toucher au code suivant
    $routeProvider
        .when('/', {
            template: sessionStorage.getItem('session') ? '<accueil></accueil>' : '<login-component></login-component>'
        })
        .otherwise({
            redirectTo: '/'
        });
}