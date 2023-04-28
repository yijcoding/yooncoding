<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/menu.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<style>
input {
	height: 27px;
	font-size: 13px;
}

button {
	font-size: 11px;
	padding: 0;
}
</style>
</head>
<body>
	<div class="container-md mx-auto"
		style="width: 590px; margin-top: 100px">
		<form id="todo-form" action="/newPw" method="post">
			<input type='hidden' name='member_id' value='${member_id}' /> <input
				type='hidden' id='oldpass' value='${m_pass}' />
			<div class="row border-bottom border-1" style="height: 35px">
				<div class="text-center border-bottom border-2 border-info input"
					style="width: 150px; font-weight: 600;">비밀번호 재설정</div>
				<div class="col-2"></div>
				<div class="text-muted" style="font-size: 15px; width:320px; text-align:right">
					01. 본인 확인 <span style="color: rgb(125, 182, 40)"> > 02. 비밀번호
						재설정</span>
				</div>
			</div>
			<div class="row text-muted mt-2">
				<div class="col-5 px-0" style="font-size: 14px">비밀번호를 변경해주세요.</div>
				<div class="col-10 px-0" style="font-size: 14px">다른 아이디나 사이트에서
					사용한적 없는 안전한 비밀번호로 변경해 주세요.</div>
			</div>

			<div class="border mt-4" style="padding-bottom: 60px;">
				<div class="row" style="margin-top: 70px;">
					<div style="width: 150px;"></div>
					<div class="col-8">
						<span style="font-weight: 600;">아이디</span> &nbsp;:&nbsp; <span
							style="color: rgb(125, 182, 40); font-weight: 600;">&nbsp;&nbsp;${member_id}</span>
					</div>
				</div>
				<div class="row mt-3">
					<div style="width: 150px;"></div>
					<div class="border-bottom" style="width: 285px;"></div>
				</div>

				<div class="row" style="margin-top: 70px;">
					<div style="width: 150px;"></div>
					<div class="col-3" style="font-size: 15; font-weight: 600">새
						비밀번호</div>
					<input type="text" class="col-3" id="m_pass" name="m_pass"
						style="height: 27px;"></input>
				</div>
				<div class="row">
					<div style="width: 150px;"></div>
					<div class="col-7" id="alert_pw"></div>
				</div>
				<div class="row mt-3">
					<div style="width: 150px;"></div>
					<div class="col-3" style="font-size: 15; font-weight: 600">비밀번호
						확인</div>
					<input type="text" class="col-3" id="m_pass2" name="m_pass2"
						style="height: 27px;"></input>
				</div>
				<div class="row">
					<div style="width: 150px;"></div>
					<div class="col-7" id="alert_pw2"></div>
				</div>

				<div class="row mt-5">
					<div class="col-1"></div>
					<div class="col-10 border-bottom"></div>
					<div class="col-1"></div>
				</div>

				<div class="row mt-5">
					<div class="col-1"></div>
					<div class="col-9 text-muted" style="font-size: 13px;">
						*영문,숫자,특수문자를 함께 사용하면(8자이상)보다 안전합니다.</div>
					<div class="col-2"></div>
					<div class="col-1"></div>
					<div class="col-10 text-muted" style="font-size: 13px;">*다른
						사이트와 다른 EXCITING AMUSEMENT만의 비밀번호를 만들어 주세요.</div>
				</div>
			</div>

			<div class="row" style="margin-top: 60px; margin-bottom: 100px">
				<button type="submit" class="col-2 m-auto border-info"
					id="submitBtn" style="font-size: 17px;">다음</button>
			</div>
		</form>

	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	<script>
  $('#submitBtn').attr("disabled", true);
  $("#m_pass2").keyup(function(){
       var m_pass = $("#m_pass").val();
       var m_pass2 = $("#m_pass2").val();
       console.log("m_pass :"+m_pass);
       console.log("m_pass2 :"+m_pass2);
         if(m_pass != "" || m_pass2 != ""){
             if(m_pass == m_pass2){
                 $("#alert_pw2").html("<font size='2' color='blue'>비밀번호가 일치합니다.</font>");
                   $('#submitBtn').attr("disabled", false);
                    }else{
                    	$("#alert_pw2").html("<font size='2' color='red'>비밀번호가 일치하지 않습니다.</font>");
                           }
                }
   });
  
 	
   	$("#m_pass").blur(function(){
    	   var m_pass = $(this).val();
    	   console.log("pass blur 진입");
    	   
    	   if(m_pass == ""){
    		   console.log("id null 진입");
    		   $("#alert_pw").html("<font size='2' color='red'>비밀번호를 입력하세요.</font>");
    		   return false;
    	   }
    	   
    	   if(m_pass.search(/\s/) != -1){
    		   console.log("search 진입");
    		   $("#alert_pw").html("<font size='2' color='red'>비밀번호에 공백을 입력할수 없습니다.</font>");
    		   return false;
    	   }
  
	 	   pwCheck();
			
    	});
   	
   	function pwCheck(){
   		
   			var pw = $("#m_pass").val();
   			var oldpw = $("#oldpass").val();
   				
   			var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
   			var hangulcheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
   			 
   			if(false === reg.test(pw)) {
   			$("#alert_pw").html("<font size='2' color='red'>비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.</font>");
   			return false;
   			}else if(/(\w)\1\1\1/.test(pw)){
   		   		$("#alert_pw").html("<font size='2' color='red'>같은 문자를 4번 이상 사용하실 수 없습니다.</font>");
   			 return false;
   			 }else if(pw.search(oldpw) > -1){
   		   		$("#alert_pw").html("<font size='2' color='red'>이전 비밀번호와 같습니다.</font>");
   			 return false;
   			 }else if(pw.search(/\s/) != -1){		
   		   		$("#alert_pw").html("<font size='2' color='red'>비밀번호는 공백 없이 입력해주세요.</font>");
   			 return false;
   			 }else if(hangulcheck.test(pw)){
   		   		$("#alert_pw").html("<font size='2' color='red'>비밀번호에 한글을 사용 할 수 없습니다.</font>");
   			 return false;
   			 }else {
   		   		$("#alert_pw").html("<font size='2' color='blue'>사용가능한 비밀번호 입니다.</font>");
   			 }
   	}

  </script>

</body>
</html>
