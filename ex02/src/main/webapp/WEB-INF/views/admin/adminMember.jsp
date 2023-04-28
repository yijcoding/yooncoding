<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 멤버</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
	integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
	crossorigin="anonymous"></script>
	
<style type="text/css">

  .wrap{
  	padding:10px;
  	both:clear;
  }
  .table,th,td{
    border:1px solid #888;
    padding:0 5px;
    text-align:center;
  }
  
  .paging a{
  	float:left;
  	padding:5px;
  }
</style>

<script type="text/javascript">
  $(document).ready(()=>{
	
  })//ready 끝
  
  
  
  //아이디 권한 변경
  function changeGrade(member_id,m_admin){
	const prom = prompt('이 아이디의 권한을 변경하시겠습니까?','관리자/회원');
	
	//동적으로 form 생성
	var form = document.createElement('form'); 
	form.setAttribute("method", "POST");
	form.setAttribute("action", "/adminMember");
	
	//매개변수를 담기위한 배열 생성
	const inputs = [
        { name: 'member_id', value: member_id },
        { name: 'm_admin', value: m_admin }
      ];
	
	//form에 담기위한 배열반복문
	for (let i = 0; i < inputs.length; i++) {
		const input = document.createElement('input');
		input.setAttribute('type', 'hidden');
		input.setAttribute('name', inputs[i].name);
		input.setAttribute('value', inputs[i].value);
		form.appendChild(input);
	}
	
   
	//유효성 검사
	if(prom){
		
		if(prom !== '관리자' && prom !=='회원'){
			alert('입력하신 값은 유효하지 않습니다!!');
		}else if(prom ==='관리자'){
			if(m_admin ==='1'){
				alert('이미 관리자입니다!');
			}else {
				console.log(11);
	    		
	    		document.body.appendChild(form);
	    		form.submit();
			}
    		
    	}else if(prom ==='회원'){
    		if(m_admin === '0'){
 	    		alert('이미 회원입니다!');
	 	    }else{
	 	    	
		   		document.body.appendChild(form);
		   		form.submit();
	 	   	} 
	    		
	    		
	    }//회원 if문
	    
	 }//첫 if문
  }
  
  //회원 강제 삭제
  
  /* function forceMeberDelete(member_id){
	  //1. 정말 삭제할건지 물어봄
	  //2. 다시한번 물어봄
	  //3. 삭제 casecade
	  const prom = prompt('이 아이디의 권한을 변경하시겠습니까?','"회원삭제"를 입력해주세요');
	  
	  if(prom === '회원삭제'){
		if(confirm('정말로 삭제하시겠습니까??')){
		  location.href="/adminMemberDelete?member_id="+member_id;		  
		}
	  }else{
	    alert('입력하신 값이 잘못되었습니다!!');
	  }
	  
  } */
  
  function searchMember(){
    var input = document.getElementsByName('search')[0];
    var search = input.value;
	location.href ="/adminMember?search="+search; 
  }
</script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/menu.jsp"/>
</div>
  <div class="wrap">
    <div>
      <a href="/admin ">관리자메인</a>
      <h1>관리자 멤버</h1>
      아이디검색<br>
      <input type="text" value="${search }" name="search">
      <button type="button" onclick="searchMember()">검색</button>
        <table class="table">
          <tr>
    	    <th>아이디</th>
    	    <th>이름</th>
    	    <th>생일</th>
    	    <th>email</th>
    	    <th>번호</th>
    	    <th>권한</th>
            <th>가입날짜</th>
    	    </tr>	
            <c:forEach var="list" items="${member}">
    	      <tr>
    	        <td>${list.member_id }</td>
    	        <td>${list.m_name }</td>
    	        <td>${list.m_birth }</td>
    	        <td>${list.m_email }</td>
    	        <td>${list.m_phone }</td>
    	        <c:if test="${list.m_admin eq 1 }">
    	      	  <td><a href="#"  id="changeGrade" onclick="changeGrade('${list.member_id}','${list.m_admin }')">관리자</a></td>
    	        </c:if>
    	        <c:if test="${list.m_admin eq 0 }">
    	          <td><a href="#"  id="changeGrade" onclick="changeGrade('${list.member_id}','${list.m_admin }')">회원</a></td>
    	        </c:if>
    	        <td>${list.m_regidate }</td>
    	    </tr>
    	  </c:forEach>
        </table>
        <div class="paging">
       	 	${paging}
        </div>
    </div>
  </div>
</body>
</html>