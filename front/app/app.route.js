export function route ($routeProvider, $locationProvider) {

    $locationProvider.html5Mode(true);

    $routeProvider
    .when('/', {
        template: '<accueil></accueil>'
    })
    .when('/ajout', {
        template: '<gdm-ajout></gdm-ajout>'
    })
    .otherwise({
        redirectTo: '/'
    });

}
