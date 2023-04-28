<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ChatRoom</title>

<!-- sockjs 라이브러리 -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js" 
integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" 
crossorigin="anonymous"></script>

<style type="text/css">
*{ margin: 0; padding: 0; }

.header { 
font-size: 14px; 
background: lightblue; 
color: black; 
text-align: center;  
position: fixed;
width: 100%;
height: 50px;
line-height: 50px;
border-radius: 20px;
}

.chat { 
padding-bottom: 50px;
padding-top: 50px;
}

.left { text-align: left; left: 10px;}
.right { text-align: right; right: 10px; }
.center { text-align: center; }
 
.chat_wrap .chat div { font-size: 13px;  }

.sender { 
margin: 10px 20px 0 20px; 
font-weight: bold; 
}

.message { 
display: inline-block; 
word-break:break-all; 
margin: 5px 20px; 
max-width: 75%; 
border: 1px solid #888; 
padding: 10px; 
border-radius: 5px; 
background-color: #FCFCFC; 
color: #555; 
text-align: left; 
}

.input-div { 
position: fixed; 
bottom: 0; 
width: 100%;
background-color: #FFF; 
text-align: center;
border: none;
margin: auto;
}

.chat_wrap .input-div > textarea { 
height: 20px; 
width: 300px;
border: 2px solid lightblue; 
border-radius: 15px;
padding: 10px;
margin: auto;
resize: none;
}

.chat_wrap::-webkit-scrollbar {
  display: none;
}
.btn{
	font-size: 1.4rem;
}
</style>

</head>
<body>
<input type="hidden" id="member_id" value="${member_id }">
<div class="chat_wrap container">
	<div class="header">CHAT</div>
	<div class="chat">
	</div>
	<div class="input-div">
		<textarea id="textarea" style="overflow:hidden" placeholder="Press Enter for send message."></textarea>
	</div>
</div>
</body>

<script type="text/javascript">
	const chatDiv = document.querySelector(".chat");
	
	function onOpen(sessionId){
		//alert("웹 소켓 서버 open");
		var str = "<div class='center'>";
		str += "<span>" + sessionId + "님이 채팅방에 입장했습니다</span></div>";
		
		$(".chat").append(str);
	}

	let sock = new SockJS("http://localhost:8080/chat?userId=" + $("#member_id").val());
	
	sock.onmessage = onMessage;
	sock.onclose = onClose;

	$(document).on("mousedown", ".input-div textarea", function(e){
		$(".input-div textarea").css("outline-color", "#FE6B8B")
	})

	//enter 눌렀을 때 메세지 전송되도록
	$(document).on("keydown", ".input-div textarea", function(e){
		//enter의 코드가 13이라고 함
		if(e.keyCode == 13){
			if(!e.shiftKey) {
				if(confirm("send?")){
					sendMessage();
					$("#textarea").val("");
				}
			}
		}
	})
	
	function sendMessage() {
		sock.send($(".input-div textarea").val());
	}
	
	// 서버로부터 메시지를 받았을 때
	function onMessage(msg) {
		var data = msg.data;
		var sessionId = null;
		var message = null;
		
		console.log(data)
		console.log(data.includes(":"))
		
		if(!data.includes(":")){
			//채팅방에 메세지 입력란에 입력하지 않고 전달된 메세지
			//afterConnectionEstablished 메소드에서 생성된 메세지
			sock.onopen = onOpen(data);
		}
		else{
			//handler에서 session 아이디와 메세지를 :로 구분해서 보냄
			var arr = data.split(":")
			
			for(let i = 0; i < arr.length; i++){
				console.log("arr['" + i + "']:" + arr[i])
			}
			
			var current_session = $("#member_id").val()
			console.log("current_session = " + current_session)
			
			sessionId = arr[0];
			message = arr[1];
			
			if(sessionId === current_session){
				//right
				console.log("same")
				
				var str = "<div class='right'>";
				str += "<div class=" + "'sender'><span>" + sessionId + "</span></div>"
				str += "<div class=" + "'message'><span>" + message + "</span></div>"
				str += "</div>";
				
				$(".chat").append(str);
				
				//스크롤 자동으로 내려가기
				window.scroll({
				  top: document.querySelector(".chat").scrollHeight,
				  left: 0,
				  behavior: "smooth",
				});
			}
			else if(sessionId !== current_session){
				//left
				console.log("different")
				
				var str = "<div class='left'>";
				str += "<div class=" + "'sender'><span>" + sessionId + "</span></div>"
				str += "<div class=" + "'message'><span>" + message + "</span></div>"
				str += "</div>";
				
				$(".chat").append(str);
				
				window.scroll({
				  top: document.querySelector(".chat").scrollHeight,
				  left: 0,
				  behavior: "smooth",
				});
			}
		}
	}
	
	// 서버와 연결을 끊었을 때
	function onClose(evt) {
		$(".chat").append("연결 끊김");
		console.log(evt);
	}
</script>
</html>