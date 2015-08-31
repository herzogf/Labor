angular.module('Contacts', ['ngAnimate'])

.factory('ContactService', function() {
	var contacts = [];
	
	return {
		getContacts: function() {
			return contacts;
		},
		addContact: function(contact) {
			contacts.push(contact);
		},
		removeContact: function(index) {
			contacts.splice(index, 1);
		}
	};
})

.controller('ContactEditCtrl', function($scope, $timeout, ContactService) {
	
	$scope.add = function(contact) {
		$scope.submitted = true;
		$scope.messages = null;
		
		if ($scope.editForm.$valid) {
			ContactService.addContact(angular.copy(contact));
			$scope.contact = {};
			$scope.editForm.$setPristine();
			$scope.editForm.$setUntouched();
			$scope.submitted = false;
			$scope.messages = 'Kontakt angelegt.';
		}
		document.getElementById('nameInput').focus();
		// Hide status messages after three seconds.
    $timeout(function() {
      $scope.messages = null;
    }, 3000);
	};
	
})

.controller('ContactListCtrl', function($scope, ContactService) {
	$scope.contacts = ContactService.getContacts();

	$scope.remove = function(index) {
		ContactService.removeContact(index);
	}
	
});
