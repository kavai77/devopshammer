devOpsHammer.controller('homeController', ['$scope', '$http', function ($scope, $http) {
}]);

devOpsHammer.controller('jsonController', ['$scope', '$http', function ($scope, $http) {
    $scope.message = "";
    $scope.errorMessage = "";
    $scope.jsonInput = "";
    $scope.editorOptions = {
        lineNumbers: true,
        mode: 'scheme'
    };

    $scope.jsonInputChange = function () {
        if (!$scope.jsonInput.isEmpty()) {
            $scope.message = "Uploading...";
            $scope.errorMessage = "";
            $http.post('/json/pretty', $scope.jsonInput).
                success(function (data, status, headers, config) {
                    $scope.message = "FORMATTED";
                    $scope.jsonInput = atob(data);
                }).
                error(function (data, status, headers, config) {
                    $scope.message = "";
                    $scope.errorMessage = "INVALID - " + data;
                    console.log("error while calling validation service");
                });
        }
    };
}]);

devOpsHammer.controller('xmlController', ['$scope', '$http', function ($scope, $http) {
    $scope.message = "";
    $scope.errorMessage = "";
    $scope.xmlInput = "";
    $scope.editorOptions = {
        lineNumbers: true,
        mode: 'scheme'
    };

    $scope.xmlInputChange = function () {
        if (!$scope.xmlInput.isEmpty()) {
            $scope.message = "Uploading...";
            $scope.errorMessage = "";
            $http.post('/xml/pretty', $scope.xmlInput).
                success(function (data, status, headers, config) {
                    $scope.message = "FORMATTED";
                    $scope.xmlInput = data;
                }).
                error(function (data, status, headers, config) {
                    $scope.message = "";
                    $scope.errorMessage = "INVALID - " + data;
                    console.log("error while calling validation service");
                });
        }
    };

}]);
devOpsHammer.controller('urlController', ['$scope', '$http', function ($scope, $http) {

    $scope.urlInput = "";
    $scope.message = "";
    $scope.errorMessage = "";
    $scope.editorOptions = {
        lineNumbers: true,
        mode: 'scheme'
    };

    $scope.urlInputEncode = function () {
        if (!$scope.urlInput.isEmpty()) {
            $scope.message = "Uploading...";
            $scope.errorMessage = "";
            $http.post('/url/encode', $scope.urlInput).
                success(function (data, status, headers, config) {
                    $scope.message = "ENCODED";
                    $scope.urlInput = data;
                }).
                error(function (data, status, headers, config) {
                    $scope.message = "";
                    $scope.errorMessage = "Unexpected error happened";
                    console.log("error while calling validation service");
                });
        }
    };

    $scope.urlInputDecode = function () {
        if (!$scope.urlInput.isEmpty()) {
            $scope.message = "Uploading...";
            $scope.errorMessage = "";
            $http.post('/url/decode', $scope.urlInput).
                success(function (data, status, headers, config) {
                    $scope.message = "DECODED";
                    $scope.urlInput = data;
                }).
                error(function (data, status, headers, config) {
                    $scope.message = "";
                    $scope.errorMessage = "Unexpected error happened";
                    console.log("error while calling validation service");
                });
        }
    };
}]);

devOpsHammer.controller('base64Controller', ['$scope', '$http', function ($scope, $http) {
    $scope.base64Input = "";
    $scope.message = "";
    $scope.errorMessage = "";
    $scope.editorOptions = {
        lineNumbers: true,
        mode: 'scheme'
    };

    $scope.base64InputEncode = function () {
        if (!$scope.base64Input.isEmpty()) {
            $scope.message = "Uploading...";
            $scope.errorMessage = "";
            $http.post('/base64/encode', $scope.base64Input).
                success(function (data, status, headers, config) {
                    $scope.message = "ENCODED";
                    $scope.base64Input = data;
                }).
                error(function (data, status, headers, config) {
                    $scope.message = "";
                    $scope.errorMessage = "Unexpected Error happened";
                    console.log("error while calling validation service");
                });
        }
    };

    $scope.base64InputDecode = function () {
        if (!$scope.base64Input.isEmpty()) {
            $scope.message = "Uploading...";
            $scope.errorMessage = "";
            $http.post('/base64/decode', $scope.base64Input).
                success(function (data, status, headers, config) {
                    $scope.message = "DECODED";
                    $scope.base64Input = data;
                }).
                error(function (data, status, headers, config) {
                    $scope.message = "";
                    $scope.errorMessage = "Unexpected Error happened";
                    console.log("error while calling validation service");
                });
        }
    };
}]);

devOpsHammer.controller('certDecoderController', function($scope, FileUploader) {
    $scope.successMessage = "";
    $scope.errorMessage = "";
    $scope.certDecoded = "";
    $scope.refreshEditor = false;
    $scope.editorOptions = {
        lineNumbers: true,
        mode: 'javascript',
        readOnly: 'true'
    };
    $scope.uploader = new FileUploader();
    $scope.uploader.url = "/x509/decode";
    $scope.uploader.autoUpload = true;
    $scope.uploader.onBeforeUploadItem  = function(item) {
        $scope.successMessage = "Uploading...";
        $scope.errorMessage = "";
        $scope.certDecoded = "";
        $scope.refreshEditor = false;
    }
    $scope.uploader.onSuccessItem = function(item, response, status, headers) {
        $scope.certDecoded = JSON.stringify(response, null, 4);
        $scope.refreshEditor = true;
        $scope.successMessage = "Certificate Extracted";
    };
    $scope.uploader.onErrorItem = function(item, response, status, headers) {
        $scope.errorMessage = response;
        $scope.successMessage = "";
    }
});

