export function route ($routeProvider, $locationProvider) {

    $locationProvider.html5Mode(true);

    $routeProvider
    .when('/', {
        template: sessionStorage.getItem('session') ? '<accueil></accueil>' : '<login-component></login-component>'
    })
    .when('/ajout', {
        template: '<gdm-ajout></gdm-ajout>'
    })
    .otherwise({
        redirectTo: '/'
    });

}