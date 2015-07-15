// module
var devOpsHammer = angular.module('devOpsHammer', ['ngRoute', 'ui.codemirror', 'angularFileUpload']);
devOpsHammer.run(['$http', function ($http) {
    $http.get('/warmup');
}]);
