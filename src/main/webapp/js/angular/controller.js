devOpsHammer.controller('homeController', ['$scope', '$http', function ($scope, $http) {
}]);

devOpsHammer.controller('jsonController', ['$scope', '$http', function ($scope, $http) {

    $scope.firstAction = true;
    $scope.message = "";
    $scope.jsonInput = "";
    $scope.jsonValid = "false";
    $scope.editorOptions = {
        lineNumbers: true,
        mode: 'scheme'
    };

    $scope.hasNoContent = function () {
        return $scope.firstAction === true;
    };

    $scope.hasValidContent = function () {
        return $scope.firstAction === false && $scope.jsonValid === 'true';
    };

    $scope.hasInvalidContent = function () {
        return $scope.firstAction === false && $scope.jsonValid !== 'true';
    };

    $scope.jsonInputChange = function () {
        $scope.firstAction = false;

        // Simple POST request example (passing data) :
        $http.post('/json/pretty', $scope.jsonInput).
            success(function (data, status, headers, config) {
                // this callback will be called asynchronously
                // when the response is available
                $scope.jsonValid = data.valid;
                $scope.message = "- " + atob(data.message);
                if (data.valid === "true") {
                    $scope.jsonInput = atob(data.json);
                }
            }).
            error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error while calling validation service");
            });
    };
}]);

devOpsHammer.controller('xmlController', ['$scope', '$http', function ($scope, $http) {

    $scope.firstAction = true;
    $scope.message = "";
    $scope.xmlInput = "";
    $scope.xmlValid = "false";
    $scope.editorOptions = {
        lineNumbers: true,
        mode: 'scheme'
    };

    $scope.hasNoContent = function () {
        return $scope.firstAction === true;
    };

    $scope.hasValidContent = function () {
        return $scope.firstAction === false && $scope.xmlValid === 'true';
    };

    $scope.hasInvalidContent = function () {
        return $scope.firstAction === false && $scope.xmlValid !== 'true';
    };

    $scope.xmlInputChange = function () {
        $scope.firstAction = false;

        // Simple POST request example (passing data) :
        $http.post('/xml/pretty', $scope.xmlInput).
            success(function (data, status, headers, config) {
                // this callback will be called asynchronously
                // when the response is available
                $scope.xmlValid = data.valid;
                $scope.message = "- " + atob(data.message);
                if (data.valid === "true") {
                    $scope.xmlInput = atob(data.xml);
                }
            }).
            error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error while calling validation service");
            });
    };

}]);
devOpsHammer.controller('urlController', ['$scope', '$http', function ($scope, $http) {

    $scope.urlInput = "";
    $scope.firstAction = true;
    $scope.urlCoded = "na";
    $scope.editorOptions = {
        lineNumbers: true,
        mode: 'scheme'
    };

    $scope.urlInputEncode = function () {
        $scope.firstAction = false;

        // Simple POST request example (passing data) :
        $http.post('/url/encode', $scope.urlInput).
            success(function (data, status, headers, config) {
                // this callback will be called asynchronously
                // when the response is available
                $scope.urlCoded = "encoded";
                $scope.urlInput = data;
            }).
            error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error while calling validation service");
            });
    };

    $scope.urlInputDecode = function () {
        $scope.firstAction = false;

        // Simple POST request example (passing data) :
        $http.post('/url/decode', $scope.urlInput).
            success(function (data, status, headers, config) {
                // this callback will be called asynchronously
                // when the response is available
                $scope.urlCoded = "decoded";
                $scope.urlInput = data;
            }).
            error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error while calling validation service");
            });
    };
}]);

devOpsHammer.controller('base64Controller', ['$scope', '$http', function ($scope, $http) {

    $scope.base64Input = "";
    $scope.firstAction = true;
    $scope.base64Coded = "na";
    $scope.editorOptions = {
        lineNumbers: true,
        mode: 'scheme'
    };

    $scope.base64InputEncode = function () {
        $scope.firstAction = false;

        // Simple POST request example (passing data) :
        $http.post('/base64/encode', $scope.base64Input).
            success(function (data, status, headers, config) {
                // this callback will be called asynchronously
                // when the response is available
                $scope.base64Coded = "encoded";
                $scope.base64Input = data;
            }).
            error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error while calling validation service");
            });
    };

    $scope.base64InputDecode = function () {
        $scope.firstAction = false;

        // Simple POST request example (passing data) :
        $http.post('/base64/decode', $scope.base64Input).
            success(function (data, status, headers, config) {
                // this callback will be called asynchronously
                // when the response is available
                $scope.base64Coded = "decoded";
                $scope.base64Input = data;
            }).
            error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("error while calling validation service");
            });
    };
}]);

devOpsHammer.controller("sequenceController", ["$scope", "sequenceDiagram", "$sce", function($scope, sequence, $sce){
    $scope.svg = $sce.trustAsHtml(sequence.render("A->B: Does something"));
}]);

