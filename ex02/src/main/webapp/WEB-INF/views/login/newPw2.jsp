<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/menu.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <!-- bg : background // primary : 특정색 -->
  <body class="">
  <!-- overflow-hidden : 넘치는 요소는 안보이게 된다. -->
  <section class="container text-center overflow-hidden px-5 border border-3 mt-5">
    <div class="mt-5 mb-5 pb-2 border-bottom border-1">
      <!-- col-5를 설정함으로써 div가 12개의 넓이중 라인의 왼쪽부터 5개만큼의 넓이를 가지게 된다. -->
      <div class="col-5">
        <span class="border-bottom pb-2 border-primary border-2"
          >비밀번호 설정</span
        >
      </div>
      <!-- text-muted : 글자색을 옅게 만들어줌 -->
      <!-- <p class="text-muted">Lorem ipsum dolor sit amet consectet</p> -->
    </div>
    <!-- g:내부 구성원들 모두에게 여백 넣을때 // w: 폭 넓이 // m-auto : 가운데정렬-->
    <!-- justify-content-center : 주축으로 정렬 m-auto와의 차이점은 m-auto만 할 경우 양쪽 마진을 똑같이 맞춰서 화면이
    증가할 때 똑같은 마진에만 맞춰서 콘텐츠가 증가하는데 비해 justify-content는 content의 정 중앙을 기준으로 콘텐츠가 증가한다. -->
    <form action="/newPw" class="row g-2 w-75 m-auto justify-content-center" method="post">
      <!-- md : 미디움사이즈 이상에서 6:6칸 넓이를 합친다. xl : 이상사이즈에서 5칸 넓이를 한다.-->
      <!-- 하나의 row는 총 12칸이고 5칸 5칸 10칸을 썼는데 다음 칸이 10칸이라면 밑의 row로 내려간다. row 설정이 없어도 저절로 내려가는듯 하다. -->
      <div class="col-md-6 col-xl-5">
        <div class="">
          <label for="m_pass">새 비밀번호</label>
          <input
            type="password"
            class=""
            id="m_pass"
            name="m_pass"
            placeholder="비밀번호 입력"
          />
          <input type='hidden' name='member_id' value='${member_id}'/>
          <input type='hidden' id='oldpass' value='${m_pass}'/>
          <div id="alert_pw"></div>
        </div>
      </div>
      <div class="col-md-6 col-xl-5">
        <div class="">
          <label for="m_pass2">비밀번호 확인</label>
          <input
            type="password"
            class=""
            id="m_pass2"
            name="m_pass2"
            placeholder="비밀번호 확인"
          />
          <div id="alert_pw2"></div>
        </div>
      </div>

      <div class="col-12 my-5">
        <button type="submit" id="submitBtn" class="btn btn-danger rounded-0">전송하기</button>
      </div>
    </form>
  </section>
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
