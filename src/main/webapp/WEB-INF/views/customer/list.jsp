<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顧客一覧画面</title>
<!--
<script src="<c:url value="/resources/js/jquery-3.1.1.js" />"></script>
 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>

<script src="<c:url value="/resources/js/sockjs.js" />"></script>
<script src="<c:url value="/resources/js/stomp.js" />"></script>
<script src="<c:url value="/resources/js/Concurrent.Thread-full-20090713.js" />"></script>

<script>



$(function() {
/* 	var socket = new SockJS("/mvc/endPoint.do");
// 	 var socket = new WebSocket('ws://' + location.host + '/mvc/endPoint.do');
	 var stompClient = Stomp.over(socket);
 */

 var socket;
 var stompClient;

	// callback function to be called when stomp client is connected to server (see Note 2)
	 var connectCallback = function(frame) {

		 whoami = frame.headers['user-name'];
		 console.log('whoami: ' + whoami);

		 console.log('Connected: ' + frame);

		alert("connected!");

	      stompClient.subscribe('/topic/greetings', function(greeting){
	    	  console.log("ok1");
	    	  console.log(JSON.parse(greeting.body).content);
	      });
//	       stompClient.send("/app/hello1", JSON.stringify({ 'name': 'Joe' }));

	     //「/topic/room/1」のような宛先のメッセージの購読を開始します。メッセージを受信した際のコールバック関数も設定
           stompClient.subscribe('/topic/greetings2/', function(greeting){
        	   console.log("ok2");
        	   console.log(greeting);
 	      });

           stompClient.subscribe('/user/queue/errors', function (error) { // エラー通知用のQueue(/user/queue/errors)を購読
               alert(JSON.parse(error.body).message);
           });

           stompClient.subscribe('/user/queue/join/' + "1", function(message) {
        	   console.log(JSON.parse(message.body));
        	    });

	 };


	 // callback function to be called when stomp client could not connect to server (see Note 3)
	 var errorCallback = function(error) {
	      // display the error's message header:
	      console.log(error.headers);
	 };

/*      var countup = function(){
	       stompClient.send("/app/hello1", {}, JSON.stringify({ 'name': 'Joe' }));
  	  }
 */
	$('#connect').on('click',function(){

		socket = new SockJS("/mvc/endPoint.do");
//	 	 var socket = new WebSocket('ws://' + location.host + '/mvc/endPoint.do');
		 stompClient = Stomp.over(socket);

//    	stompClient.connect("guest", "guest", connectCallback, errorCallback);
		stompClient.connect("guest", "guest", connectCallback, errorCallback);
	     console.log("connectCallback",connectCallback,stompClient);

	});

	$('#send').on('click',function(){
	     alert("test");


 	       var countup = function(){
		       stompClient.send("/app/hello1", {}, JSON.stringify({ 'name': 'Joe' }));
		       setTimeout(countup, 1000);

 	       }
 setTimeout(countup, 1000);

	});

	$('#send2').on('click',function(){
//	     alert("test");

	       stompClient.send("/app/hello2", {}, "message");

	});

	$('#send3').on('click',function(){
//	     alert("test");

	       stompClient.send("/app/hello3", {}, "error");

	});

	$('#sendqueue').on('click',function(){
//	     alert("test");
	       stompClient.send('/app/join/' + "1",{},JSON.stringify({ 'name': 'Joe' }));

	});




	$('#sendcopy').on('click',function(){

console.log("stompClient",stompClient);


		this.replacer = function(key, val) {
	    	console.log("replacer");
		    return typeof val == 'function' ? val.toString() : val
		};

		this.parser = function(key, val) {
	  		try {
				return (typeof val == 'string'
					  && val.match(/^function [\s\S]+?}$/)) ? eval('(' + val + ')') : val
			} catch(e) {
				return val
			}
		};

		var json = JSON.stringify(stompClient, this.replacer());
		console.log("json",json);

		var obj = JSON.parse(json, this.parser());
		console.log("obj",obj);
		obj.send("/app/hello1", {}, JSON.stringify({ 'name': 'Joe' }));
	});



	 var connectCallback2 = function() {
	      alert("connected2!");
	 };

	 var errorCallback2 = function(error) {
	      // display the error's message header:
	      alert(error.headers);
	 };

	 var disconnectCallback2 = function() {
	      alert("disconnectCallback2!");
	 };

	 var socket2;
		var stompClient2;
	$('#sendcopy2').on('click',function(){

		console.log("sendcopy2");

		socket2 = new SockJS("/mvc/endPoint.do");
//	 	 var socket = new WebSocket('ws://' + location.host + '/mvc/endPoint.do');
		 stompClient2 = Stomp.over(socket2);

		stompClient2.connect("guest", "guest", connectCallback2, errorCallback2);

	});

	$('#disconnect1').on('click',function(){

		stompClient.disconnect(disconnectCallback2);

	});

	$('#disconnect2').on('click',function(){

		stompClient2.disconnect(disconnectCallback2);

	});

	$('#worker').on('click',function(){

		var worker = new Worker("resources/js/worker_test.js");
		worker.onmessage = function(event) {//ワーカーから受け取り
		    var BB = event.data;
		    console.log("BB", BB);
		}
		worker.postMessage("start");
	});


	$('#worker3').on('click',function(){

		console.log("worker3 new:", new Date());
		var worker = new Worker("resources/js/worker_test3.js");
 		worker.onmessage = function(event) {//ワーカーから受け取り

console.log("wait start", new Date());
 			var BB = event.data;
		    console.log("BB", BB);
			var d1 = new Date();
	 		while (true) {
			  // Concurrent.Thread.yield();
			  var d2 = new Date();
			  if (d2 - d1 > 5000) {
			    break;
			  }
			}
	 		console.log("wait end", new Date());

		}
		worker.postMessage({"ret":"0","data":self.obj});

		console.log("ajax start", new Date());
		$.ajax({
			   type: "GET",
			   url: "/mvc/api/text/",
			   success: function(msg){
					console.log("ajax success", new Date());
			   }
			 });
		console.log("ajax end", new Date());

 		var d1 = new Date();
 		while (true) {
		  // Concurrent.Thread.yield();
		  var d2 = new Date();
		  if (d2 - d1 > 5000) {
		    break;
		  }
		}

	});


	var socket3;
	var stompClient3;
	 var send3 = function(id, stompClient3, JSON) {
		 stompClient3.send("/app/hello1", {}, JSON.stringify({ 'name': 'Joe' }));
	 };

	 var connectCallback3 = function() {
	      alert("connected3!");

	      Concurrent.Thread.create(send3, 1,stompClient3, JSON);
	 };

	 var errorCallback3 = function(error) {
	      // display the error's message header:
	      alert(error.headers);
	 };



	$('#Concurrent').on('click',function(){

		console.log("Concurrent");

		socket3 = new SockJS("/mvc/endPoint.do");
		stompClient3 = Stomp.over(socket3);

		stompClient3.connect("guest", "guest", connectCallback3, errorCallback3);

	});



	//stompClient.connect("guest", "guest", connectCallback, errorCallback);

})

</script>


</head>
<body>
<h1>顧客一覧画面</h1>
<c:if test="${editedCustomer != null}">
以下の顧客が更新されました。
<dl>
  <dt>名前</dt>
  <dd><c:out value="${editedCustomer.name}"/></dd>
  <dt>Eメールアドレス</dt>
  <dd><c:out value="${editedCustomer.emailAddress}"/></dd>
  <dt>誕生日</dt>
  <dd><fmt:formatDate pattern="yyyy/MM/dd" value="${editedCustomer.birthday}"/></dd>
  <dt>好きな数字</dt>
  <dd><c:out value="${editedCustomer.favoriteNumber}"/></dd>
</dl>
</c:if>
<table border="1">
  <tr>
    <th>ID</th>
    <th>名前</th>
    <th>Eメールアドレス</th>
    <th></th>
  </tr>
  <c:forEach items="${customers}" var="customer">
  <tr>
    <td><c:out value="${customer.id}"/></td>
    <td><c:out value="${customer.name}"/></td>
    <td><c:out value="${customer.emailAddress}"/></td>
    <td>
      <c:url value="/customer/${customer.id}" var="url"/>
      <a href="${url}">詳細</a>
      <c:url value="/customer/${customer.id}/edit" var="url"/>
      <a href="${url}">編集</a>
    </td>
  </tr>
  </c:forEach>
</table>
<input type="button" id="connect" value="connect">
<input type="button" id="send" value="send">
<input type="button" id="send2" value="send2">
<input type="button" id="send3" value="send3">
<input type="button" id="sendqueue" value="sendqueue">

<input type="button" id="sendcopy" value="sendcopy">
<input type="button" id="sendcopy2" value="sendcopy2">
<input type="button" id="disconnect1" value="disconnect1">
<input type="button" id="disconnect2" value="disconnect2">

<input type="button" id="worker" value="worker">
<input type="button" id="worker3" value="worker3">

<input type="button" id="Concurrent" value="Concurrent">



</body>
</html>
