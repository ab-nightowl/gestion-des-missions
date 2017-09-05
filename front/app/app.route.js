export function route ($routeProvider, $locationProvider) {

    $locationProvider.html5Mode(true);

    $routeProvider
    .when('/', {
        template: '<accueil></accueil>'
    })
    .when('/missions/creer', {
        template: '<gdm-creer-mission></gdm-creer-mission>'
    })
    .otherwise({
        redirectTo: '/'
    });

}
