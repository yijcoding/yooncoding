<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 놀이공원 업데이트</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

<style type="text/css">
  .wrap{
  	padding:10px;
  }
  .origin-imgs .image-wrap{
  	display:inline-block;
  	
  }
  .origin-imgs img,.image-wrap img{
  
  	width:200px;
  }
</style>
<script type="text/javascript">
var imgsInput=[];

$(document).ready(()=>{
	originImgView()
});

function imageInput(){
  var imgs = document.getElementById('imgs');
	var img =imgs.value;
	imgsInput.push(img);
	const imgpreview = document.createElement('img');
	imgpreview.setAttribute('src','');
	$('.image-wrap').append(imgpreview);
	for(row of imgsInput){
	  imgpreview.setAttribute('src',row);
	  $('.image-wrap').append(imgpreview);
	}
	
	imgpreview.addEventListener('click', function() {
	    var index = imgsInput.indexOf(imgpreview.getAttribute('src'));
	    if (index > -1) {
	      imgsInput.splice(index, 1);
	    }
	    imgpreview.remove();
	  });
}
	console.log(imgsInput);
	
function updateAmusementCheck(){
	  const form = document.getElementById('form');
	  const imgforminput = document.createElement('input');
	  imgforminput.type = "hidden";
	  imgforminput.setAttribute('name', 'imgs');
	  
	  if($('.a_name').val()=='') {
		  alert('놀이공원 제목을 입력해주세요');
		  return false;
	  }else if($('.a_time').val().length!=11){
		  alert("00:00~00:00 형식으로 입력해주세요!!!!!!");
		  return false;
	  }else if($('.a_lat').val()=="" && $('.a_lng').val()==""){
		  alert("좌표를 입력해주세요!!!!!!");
		  return false;
	  }else if($('.a_info').val().length==0){
		  alert("놀이공원 설명을 입력해주세요!");
		  return false;
	  }else{
		for(row of imgsInput){
			imgforminput.value += row + ",";
			alert(imgforminput.value);
		}
		imgforminput.value = imgforminput.value.slice(0, -1);
		form.appendChild(imgforminput);
		console.log(imgsInput);
		console.log(imgforminput);
	  }
}

function imgDelete(e){
	console.log(e.alt)
	
	 $.ajax({
		type:"post",
		url:"/updateAmusmentDetailImgDelete",
		data:{
			"aimg_id":e.alt
		},success : (data)=>{
			console.log(data);
			/* if(data === 1 || data==="1"){
				console.log(data)
			}  */
			originImgView();
		}
	})
	
}

function originImgView(){
	$.ajax({
		type:"get",
		url:"/updateAmusmentDetailGetImg",
		data:{
			"amuse_id":"${amuse[0].amuse_id}"
		},
		success : (data)=>{
			console.log(data)
			tr='';
			for(row of data){
				console.log(row.url);
				tr='<img src="'+row.url+'"name="a_img" id="origin-img"alt="'+row.aimg_id+'"onclick="imgDelete(this)">';
				$('.origin-imgs').append(tr);
			}
		}
	})
}
</script>
</head>
<body>
 <jsp:include page="/WEB-INF/views/menu.jsp"/>
  <div class="wrap">
  <form action="/updateAmusmentDetail" method="post" id="form" onsubmit="return updateAmusementCheck()">
    <div>
      <p style="font-size:20px;font-weight: bolder">놀이공원 이름</p>
      <p> <input type="text" name="a_name" class="a_name"value="${amuse[0].a_name }"> </p>
      
      <p  style="font-size:20px;font-weight: bolder">운영시간</p>
      <p> <input type="text" name="a_time" class="a_time"value="${amuse[0].a_time }"></p>
      
      <p style="font-size:20px;font-weight: bolder">설명</p>
      <p><textarea cols="60" rows="10" name="a_info" class="a_info">${amuse[0].a_info }</textarea></p>
      
      <p style="font-size:20px;font-weight: bolder">좌표</p>
      <p><input type="text" name="a_lat" class="a_lat"value=" ${amuse[0].a_lat }">
         <input type="text" name="a_lng" class="a_lng"value="${amuse[0].a_lng }"></p>
      
      <p style="font-size:20px;font-weight: bolder">기존 이미지</p>
      <div class="origin-imgs"style="display:inline-block">
      </div>
      
      <p style="font-size:20px;font-weight: bolder">추가 이미지</p>
      	<p><input type="text"id="imgs"placeholder="이미지경로">
      	<button type="button" onclick="imageInput()">입력</button></p>
      <div class="image-wrap">
      <!-- 추가할 이미지 들어갈 자리 -->
      </div>
    <input type="hidden" name="amuse_id" value="${amuse[0].amuse_id}">
       
	   <div style="text-align:right">
	     <button type="submit">수정 확인</button>
	     <button type="button" onclick="location.href='/adminAmusementDetail?amuse_id=${amuse[0].amuse_id}'">돌아가기</button>
	   </div>       
    </div>
  </form>
  </div>
	
</body>
</html>