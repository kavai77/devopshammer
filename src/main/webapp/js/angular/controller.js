devOpsHammer.controller('homeController', ['$scope', '$http', function ($scope, $http) {
}]);

devOpsHammer.controller('jsonController', ['$scope', '$http', function ($scope, $http) {

    $scope.firstAction = true;
    $scope.message = "";
    $scope.jsonInput = "";
    $scope.jsonValid = "false";

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
        $http.post('http://localhost:8080/json/pretty', $scope.jsonInput).
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