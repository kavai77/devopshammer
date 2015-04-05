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