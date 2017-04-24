importScripts("./sockjs.js");
importScripts("./stomp.js");

self.obj = {};

self.onmessage = function(event) {

	console.log(self.obj);
//    setTimeout('mes()',1000);
	self.obj.socket2 = new SockJS("/mvc/endPoint.do");
	self.obj.stompClient2 = Stomp.over(self.obj.socket2);

	self.obj.stompClient2.connect("guest", "guest", self.connectCallback2, self.errorCallback2);


}

self.mes = function() {
	console.log("3秒経ちました！");
	console.log(new Date());
	self.obj.stompClient2.send("/app/hello1", {}, JSON.stringify({ 'name': 'Joe' }));

	setTimeout('self.mes()',1000);
}

self.connectCallback2 = function(frame) {
	console.log("connectCallback2:", new Date());

    console.log("connected2!", frame);
    self.obj.stompClient2.subscribe('/topic/greetings', function(greeting){
  	  console.log("ok1");
  	  console.log(JSON.parse(greeting.body).content);
    });
    self.postMessage({"ret":"0","data":"dt"});
  setTimeout('self.mes()',1000);

};

self.errorCallback2 = function(error) {
    // display the error's message header:
	console.log(error.headers);
};


