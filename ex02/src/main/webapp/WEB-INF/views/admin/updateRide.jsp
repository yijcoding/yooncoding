<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>놀이기구 수정</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
<style type="text/css">
  img{
    width:200px;
  }
  .image-wrap{
  	display:inline-block;
  }
</style>

<script type="text/javascript">
  var imgsInput=[];
  
  $(document).ready(()=>{
	  
  })
  
  function imageInput(){
    var imgs = document.getElementById('imgs');
  	var img =imgs.value;
  	if(img !=''){
		imgsInput.push(img);
	  	const imgpreview = document.createElement('img');
	  	imgpreview.setAttribute('src','');
	  	$('.image-wrap').append(imgpreview);
	  	for(row of imgsInput){
		  imgpreview.setAttribute('src',row);
		  $('.image-wrap').append(imgpreview);
	  	}
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
  	
  	
  function insertAmusementCheck(){
	  const form = document.getElementById('form');
	  const imgforminput = document.createElement('input');
	  imgforminput.type = "hidden";
	  imgforminput.setAttribute('name', 'imgs');
	  
	  if($('.r_name').val()=='') {
		  alert('놀이기구 제목을 입력해주세요');
		  return false;
	  }else if($('.r_location').val()==''){
		  alert("0000-00-00 형식으로 입력해주세요!!!!!!");
		  return false;
	  }else if($('.r_info').val().length==0){
		  alert("놀이공원 설명을 입력해주세요!");
		  return false;
	  }else{
		for(row of imgsInput){
			imgforminput.value += row + ",";
			alert(imgforminput.value);
		}
		imgforminput.value = imgforminput.value.slice(0, -1);
		form.appendChild(imgforminput);
	  }
  }
</script>
</head>
<body>
<div>
  <div>
    <form action="/updateRide" method="post" id="form" onsubmit="return insertAmusementCheck()">
       <p>놀이기구이름</p>
       <p><input type="text" name="r_name" class="r_name" value="${ride.r_name }"><br></p>
       <p>위치</p>
       <p><input type="text" name="r_location" class="r_location" value="${ride.r_location }"></p>
       <p>키 제한</p>
       <input type="text" name="r_min_height" class="r_min_height" value="${ride.r_min_height }" >
       <input type="text" name="r_max_height" class="r_max_height" value="${ride.r_max_height }" >
       <p>놀이공원 설명</p>
       <p><textarea rows="10" cols="80"name="r_info" class="r_info" >${ride.r_info }</textarea></p>
       
       <p>이미지교체(*1개만 넣으시오*)</p>
       <p><input type="text" id="imgs"placeholder="이미지경로">
       <button type="button" onclick="imageInput()">입력</button></p>
       <div class="image-wrap">
       
       </div>
       <p>비디오</p>
       <p><input type="text" id="r_video" name="r_video" placeholder="비디오경로"></p>
       <br>
       <input type="button" value="돌아가기" onclick="location.href='/adminAmusementDetail?amuse_id=${ride.amuse_id}'"> 
       <input type="submit" value="수정">
       <input type="hidden" name="amsue_id" value="${ride.amuse_id}">
       <input type="hidden" name="rides_id" value="${ride.rides_id}">
    </form>
   
  </div>		
</div>
</body>
</html>