angular.module('iban-client', ['ngAnimate'])

.controller('IbanCtrl', function($scope, $http) {
	$scope.kto = "";
	$scope.blz = "";
	$scope.iban = "";
	$scope.msg = {type: "", msg: "", icon: ""};

	$scope.convertToIban = function() {
		$scope.iban = "";
		$scope.msg = {type: "", msg: "converting...", icon: "glyphicon-transfer"};

 		var responsePromise = $http.get("/iban", {
    		params: { kto: $scope.kto, blz: $scope.blz }
		});

        responsePromise.success(function(data, status, headers, config) {
        	$scope.iban = data.iban;
        	$scope.msg = {type: "success", msg: "Hat geklappt.", icon: "glyphicon-ok-circle"};
        });

        responsePromise.error(function(data, status, headers, config) {
        	var error = data.message + " (" + data.exception + ")";
        	$scope.msg = {type: "error", msg: error, icon: "glyphicon-remove-circle"};
        });
	};
})