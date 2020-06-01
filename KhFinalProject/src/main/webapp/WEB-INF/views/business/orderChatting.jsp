<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문메세지</title>
</head>
<body>

	<div id="ss">
		
	</div>


<script type="text/javascript">
const websocket = new WebSocket("ws://localhost:9090${pageContext.request.contextPath}/orderalert")
websocket.onopen=function(data){
	websocket.send(JSON.stringify(new SocketMessage("business","${no}","","")));
}

function SocketMessage(type,sender,receiver,msg){
	this.type=type;
	this.sender=sender;
	this.receiver=receiver;
	this.msg= msg;
};

websocket.onmessage=function(data){
	
	const msg= JSON.parse(data.data);
	$("#ss").append("<h2>"+msg.msg+"</h2>");
}


</script>


</body>
</html>