angular.module('Add', [])

.controller('AddCtrl', function($scope) {
	
	 $scope.zahl1 = 0;
	 $scope.zahl2 = 0;
  
   $scope.addiere = function() {
       return parseInt($scope.zahl1) + parseInt($scope.zahl2);   	
 	 }
});
