devOpsHammer.controller('jsonController', ['$scope', '$http', function ($scope, $http) {

    $scope.firstAction = true;
    $scope.jsonInput = "";
    $scope.jsonValid = "false";

    $scope.jsonInputChange = function () {
        $scope.firstAction = false;

        // Simple POST request example (passing data) :
        $http.post('http://localhost:8080/json/pretty', $scope.jsonInput).
            success(function (data, status, headers, config) {
                // this callback will be called asynchronously
                // when the response is available
                $scope.jsonValid = data.valid;
                if (data.valid === "true") {
                    $scope.jsonInput = JSON.stringify(data.json, null, 4);
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
        $http.post('http://localhost:8080/xml/pretty', $scope.xmlInput).
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
    $scope.urlInput = "";
    $scope.urlCoded = "na";

    $scope.urlInputEncode = function () {
            $scope.firstAction = false;

            // Simple POST request example (passing data) :
            $http.post('http://localhost:8080/url/encode', $scope.urlInput).
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
                $http.post('http://localhost:8080/url/decode', $scope.urlInput).
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

