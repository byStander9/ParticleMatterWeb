<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/listup_admins.css' />" rel="stylesheet" type="text/css">

</head>
<body>

	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="./include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
			
			<div class="word">
			
				<h3>REVIEW LIST</h3>
				
			</div>
			
			<div class="admin_list">
			
				<table>
					<thead>
						<tr>
							<th>여행지명</th>
							<th>리뷰내용</th>
							<th>작성자명</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${reviewVos}">
							<tr>
								<td>${item.placeName}</td>
								<td>${item.review}</td>
								<td>${item.userName}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
		
		</div>
		
	</section>
	
	<jsp:include page="../include/footer.jsp" />
	
</body>
</html>