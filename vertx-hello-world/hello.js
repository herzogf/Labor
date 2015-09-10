var vertx = require('vertx');
var eventBus = require('vertx/event_bus');
var container = require('vertx/container');

var hellolist = "data/hellolist.json";
var logger = container.logger;

eventBus.registerHandler('hello.say', function(message, responder){
	logger.info("hello.js - event received 'hello.say'");
	var hello = {msg: message.msg, date: new Date()};
	
	// read list of hello messages from file
	vertx.fileSystem.readFile(hellolist, function(err, file) {
		if (err) {
			logger.error("hello.js - Error reading file: " + err);
			responder({status: "error", error: err});
		} else {
			var data = JSON.parse(file.getString(0, file.length()));
			// put new message in the list
			data.unshift(hello);
			// remove oldest message from list if more than 20 messages
			if (data.length > 20) {
				data.pop();					
			}

			// write new list of hello messages to file
			vertx.fileSystem.writeFile(hellolist, JSON.stringify(data), function(err) {
				if (err) {
					logger.error("hello.js - Error writing file: " + err);
					responder({status: "error", error: err});
				} else {
					// respond to sender
					responder({status: 'ok'});
					// publish hello event
					eventBus.publish('event.hello', hello);
				}
			});		
		}
	});	

});

eventBus.registerHandler('hello.list', function(message, responder){
	logger.info("hello.js - event received 'hello.list'");
	
	// read list of hello messages from file
	vertx.fileSystem.readFile(hellolist, function(err, file) {
		if (err) {
			logger.error("hello.js - Error reading file: " + err);
			responder({status: "error", error: err});
		} else {
			var data = JSON.parse(file.getString(0, file.length()));
			responder({status: "ok", hellolist: data});
		}
	});	

});

logger.info("hello.js is started");

