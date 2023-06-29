<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table table-borderless">
	<tbody id="reviews">
	<c:forEach items="${reviewList }" var="rList" varStatus="status">
		<c:set var="rList_member_id" value="${rList.member_id }"/>
		<c:set var="length" value="${fn:length(rList_member_id)}"/>
		<c:set var="last_two" value="${fn:substring(rList_member_id, length-3, length)}"/>
		<tr>
			<c:choose>
				<c:when test="${rList_member_id eq 'hong1' }">
					<th>관리자</th>
					<th>&nbsp;&nbsp;&nbsp;ㄴ ${rList.r_content }</th>
				</c:when>
				<c:otherwise>
					<th>${fn:replace(rList_member_id,last_two,"***")}</th>
					<th>${rList.r_content }</th>
				</c:otherwise>
			</c:choose>
			<th>${rList.r_regidate }</th>
			<c:if test="${member_id eq rList_member_id }">
			<th><button type="button" class="btn btn-outline-dark mt-auto" onclick="reviewDelete(${rList.review_id});">Delete</button></th>
			</c:if>
			<c:if test="${member_id eq 'hong1' && rList_member_id ne 'hong1'}">
			<th><button type="button" class="btn btn-outline-dark mt-auto" onclick="reviewAnswer(${rList.review_id});">Answer</button></th>
			</c:if>
		</tr>
	</c:forEach>
	</tbody>
</table>
</body>
</html>