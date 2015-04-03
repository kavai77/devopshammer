//routes
devOpsHammer.config(function ($routeProvider) {
    $routeProvider

        .when('/', {
            templateUrl: 'js/angular/template/home.html',
            controller: 'homeController'
        })

        .when('/json', {
            templateUrl: 'js/angular/template/json.html',
            controller: 'jsonController'
        })
});