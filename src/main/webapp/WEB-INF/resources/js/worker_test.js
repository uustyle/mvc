importScripts("./jquery-1.9.0.min.js");
importScripts("./sockjs.js");
importScripts("./stomp.js");

self.obj = {};

self.onmessage = function(event) {

	var socket2 = new SockJS("/mvc/endPoint.do");
//	 var socket = new WebSocket('ws://' + location.host + '/mvc/endPoint.do');
	 stompClient2 = Stomp.over(socket2);

	stompClient2.connect("guest", "guest", connectCallback2, errorCallback2);

console.log(self.obj);

}

var connectCallback2 = function() {
    console.log("connected2!");
};

var errorCallback2 = function(error) {
    // display the error's message header:
	console.log(error.headers);
};