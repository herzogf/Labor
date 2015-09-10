var container = require("vertx/container");

container.deployModule("io.vertx~mod-web-server~2.0.0-final", {
	port: 9080,
	host: "0.0.0.0",
	bridge: true,
	inbound_permitted: [
	                    {address: 'hello.say'},
	                    {address: 'hello.list'}
	],
	outbound_permitted: [
  	                    {address: 'event.hello'}
	]
}, 2);

container.deployWorkerVerticle("hello.js", 1);
