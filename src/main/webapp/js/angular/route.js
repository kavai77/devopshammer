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

        .when('/xml', {
            templateUrl: 'js/angular/template/xml.html',
            controller: 'xmlController'
        })

        .when('/url', {
            templateUrl: 'js/angular/template/url.html',
            controller: 'urlController'
        })

        .when('/base64', {
            templateUrl: 'js/angular/template/base64.html',
            controller: 'base64Controller'
        })

        .otherwise({
            redirectTo: '/'
        })
});