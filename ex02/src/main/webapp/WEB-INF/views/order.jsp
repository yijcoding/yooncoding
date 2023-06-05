<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>Product Details</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
	integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8="
	crossorigin="anonymous"></script>
<!-- 부트스트랩 cdn -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
<!-- 폰트 설정 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Hahmlet&display=swap"
	rel="stylesheet">
<!-- 아이콘 cdn-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<style>
body {
	font-family: 'Hahmlet', serif;
	font-size: 16px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

table {
	border-collapse: separate;
	border-spacing: 0;
	width: 100%;
	max-width: 1400px;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
}

th, td {
	text-align: left;
	padding: 16px;
	border-bottom: 1px solid #eee;
}

th {
	background-color: #f2f2f2;
	font-weight: bold;
}

tr:hover {
	background-color: #f5f5f5;
}

td:first-child, th:first-child {
	border-top-left-radius: 10px;
	border-bottom-left-radius: 10px;
}

td:last-child, th:last-child {
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
}

button[id=buy-btn] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

button[id=buy-btn]:hover {
	background-color: #45a049;
}

.product {
	display: flex;
	align-items: center;
	margin-bottom: 20px;
}

.product img {
	width: 350px;
	height: 200px;
	margin-right: 20px;
}

.product .name {
	font-weight: bold;
	margin-right: 20px;
}

.product .price {
	font-weight: bold;
}

.minus, .plus {
	width: 30px;
	height: 30px;
	text-align: center;
	background-color: #ccc;
	border-radius: 50%;
	border: none;
	color: #fff;
	font-size: 20px;
	cursor: pointer;
}

.numbox {
	border: none;
	text-align: center;
	font-size: 20px;
	width: 50px;
	height: 30px;
}
</style>
<!-- 로딩 이벤트 -->
<script type="text/javascript" src="resources/js/loading.js"></script>
<script type="text/javascript">
$(function() {
	var tableRow = document.querySelectorAll('.table-row');
	var myMap = new Map();
	tableRow.forEach((row) => {
		var numbox = row.querySelector('.numbox');
		var minus = row.querySelector('.minus');
		var plus = row.querySelector('.plus');
		var total = row.querySelector('total');
		
		minus.addEventListener('click', function() {
			var PriceValue = parseInt($(row).find('.price').text().replace(/[^0-9]/g,''));
			if(numbox.value <= 0) {
				return;
			}
			var minusNum = parseInt(numbox.value) - 1;
			numbox.value = minusNum;
			
			if($('.ticket-id').val() != null){
				var dataa = '';
				dataa += "<td>" + PriceValue * minusNum + "</td>";
		        $(row).find('.total').html(dataa);
			}
		});
		plus.addEventListener('click', function() {
			var PriceValue = parseInt($(row).find('.price').text().replace(/[^0-9]/g,''));
			if(numbox.value >= 10) {
				return;
			}
			var plusNum = parseInt(numbox.value) +1;
			numbox.value = plusNum;
			
			if($('.ticket-id').val() != null){
				var dataa = '';
				dataa += "<td>" + PriceValue * plusNum + "</td>";
		        $(row).find('.total').html(dataa);
			}
		});
	});
	
	var buyBtn = document.querySelector('#buy-btn');
	
	buyBtn.addEventListener('click',function() {
		var myArray = [];
		tableRow.forEach((row) => {
			var myMap = new Map();
			var ticketId = row.querySelector('.ticket-id').value;
			var numbox = row.querySelector('.numbox');
			myMap.set('ticket_id', ticketId);
			myMap.set('quantity', numbox.value);
			myArray.push(myMap);
		});
		console.log(myArray);
		const myJSON = JSON.stringify(myArray.map(obj => {
			  return { 
			    ticket_id: obj.get('ticket_id'),
			    quantity: obj.get('quantity')
			  };
		}));
		console.log(myJSON);
		
		$.ajax({
			url:'/order',
			type:'post',
			data: {'myJSON' : myJSON},
			datatype : 'json',
			success: function(response) {
				if(response == 1){
					alert("구매해 주셔서 감사합니다!");
					window.location.href="/";
					<!-- 성공하면 할거 -->
				}else {
					<!-- 실패하면 할거 -->
				}
			},
			error: function(xhr, status, error){
				console.error(error);
			}
		})
	});
	
	
});
</script>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="container">
		<form action="/" method="post">
			<div class="product">
				<img src="${data[0]['promotion_img']}" alt="Product Image">
				<div>
					<div class="name">${data[0]['promotion_name']}</div>
					<div class="description">${data[0]['promotion_content']}</div>
				</div>
			</div>
			<table>
				<thead>
					<tr>
						<th>행사명</th>
						<th>상품명</th>
						<th>가격</th>
						<th>수량</th>
						<th>합계</th>
					</tr>
				</thead>
				<tbody>
					<fmt:setLocale value="ko_KR" />
					<c:forEach items="${data}" var="d">
						<tr class="table-row">
							<td>${d['promotion_name']}</td>
							<td>${d['ticket_name']}</td>
							<td class="price"><fmt:formatNumber
									value="${d['ticket_price']*(1-d['discount'])}" pattern="#,##0₩"
									type="currency" currencySymbol="₩" /></td>
							<td>
								<button type="button" class="minus">-</button> <input
								type="number" class="numbox" min="0" max="10" value="0"
								readonly="readonly"> <input type="hidden"
								class="ticket-id" value="${d['ticket_id']}">
								<button type="button" class="plus">+</button>
							</td>
							<td class="total"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
			<p class="mt-5">사용 가능한 포인트는 <fmt:formatNumber value="${data1[0]['sum_point']}" pattern="#,##0" /> 포인트 입니다.</p>
			<input ty>
			<input type="hidden" id="order-data">
				<button id="buy-btn" type="button" class="mt-0" style="float: right;">구매
					하기</button></div>
		</form>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>