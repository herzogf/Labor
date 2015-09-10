angular.module('hello-world', ['ngAnimate'])

.controller('HelloCtrl', function($scope) {
	var eb;
	
	$scope.msg = "";
	$scope.hellotext = "";
	$scope.hellolist = [];
	
	$scope.init = function() {
		// open the vert.x message bus
		eb = new vertx.EventBus(window.location.protocol + '//' + window.location.hostname + ':' + window.location.port + '/eventbus');
		eb.onopen = function() {

			// subscribe for hello events
			eb.registerHandler('event.hello', function(message) {
				// put new message in the list
				$scope.hellolist.unshift(message);
				// remove oldest message from list if more than 20 messages
				if ($scope.hellolist.length > 20) {
					$scope.hellolist.pop();					
				}
				$scope.$apply();
			});

			// read list with previous hello messages
			eb.send('hello.list', {}, function(message) {
				if(message.status == "ok") {
					$scope.hellolist = message.hellolist;
				} else {
					$scope.msg ="Cannot read list with previous messages: " + message.error;				
				}
				$scope.$apply();
			});

			$scope.msg ="Message Bus open for your message.";
			$scope.$apply();
		}
		
		eb.onclose = function() {
			alert("Server disconnected. Please reload page.");
		}

	};

	$scope.send = function() {
		// send hello message over message bus
		eb.send('hello.say', {msg: $scope.hellotext}, function(message) {
			if(message.status == "ok") {
				$scope.hellotext = "";
				$scope.msg ="Message send.";				
			} else {
				$scope.msg ="Something went wrong: " + message.error;				
			}
			$scope.$apply();
		});

	};
})
