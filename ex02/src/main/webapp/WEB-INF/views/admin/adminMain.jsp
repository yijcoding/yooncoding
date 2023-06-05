<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자</title>
<style type="text/css">
  .wrap{
  	width:100%;
  	height:100%;
  	textalign:center;
  }
</style>

<script type="text/javascript">
  
	
</script>

</head>
<body>
<jsp:include page="/WEB-INF/views/menu.jsp"/>
<div class="wrap">
  <div>
    <h1>관리자</h1>
  </div>
	<button onclick="location.href='/adminMember'">회원 목록</button>
	<button onclick="location.href='/adminAmusement'">놀이공원</button>
</div>
</body>
</html>