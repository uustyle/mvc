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

<script>
$(function() {

 	var socket = new SockJS("/mvc/endPoint");
// 	 var socket = new WebSocket('ws://' + location.host + '/mvc/hello1');
	 var stompClient = Stomp.over(socket);

	 // callback function to be called when stomp client is connected to server (see Note 2)
	 var connectCallback = function() {
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

	 };


	 // callback function to be called when stomp client could not connect to server (see Note 3)
	 var errorCallback = function(error) {
	      // display the error's message header:
	      console.log(error.headers);
	 };

	$('#connect').on('click',function(){

//    	stompClient.connect("guest", "guest", connectCallback, errorCallback);
	     console.log("connectCallback",connectCallback,stompClient);

	});

	$('#send').on('click',function(){
//	     alert("test");

	       stompClient.send("/app/hello1", {}, JSON.stringify({ 'name': 'Joe' }));

	});

	$('#send2').on('click',function(){
//	     alert("test");

	       stompClient.send("/app/hello2", {}, "message");

	});


    stompClient.connect("guest", "guest", connectCallback, errorCallback);

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

</body>
</html>
