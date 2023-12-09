<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/main.css' />" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	
	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="./include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
			<div class="word">
			
				<h3>Recommendation</h3>
				
			</div>
			
			<div class="recommendation">
			
				<ul>
					<li>
						<table>
							<caption>미세먼지 수치 가장 낮은 지역</caption>
							<tr>
								<td>지역명</td>
								<td id="city_name">Existing Data</td>
							</tr>
							<tr>
								<td>통합대기환경수치</td>
								<td id="city_airQuality">Existing Data</td>
							</tr>
							<tr>
								<td>미세먼지(PM10) 농도</td>
								<td id="city_pm10">Existing Data</td>
							</tr>
							<tr>
								<td>초미세먼지(PM2.5) 농도</td>
								<td id="city_pm2_5">Existing Data</td>
							</tr>
							
							<tr>
								<td>등록일</td>
								<td id="upload_date">Existing Data</td>
							</tr>
							<tr>
								<td>다음제안</td>
								<td>
									<button type="button" onclick="loadNextInfo()">가져오기</button>
								</td>
							</tr>
						</table>
					</li>
    
					<li>
						<table>
							<caption>추천 여행지</caption>
							<tr>
								<td>여행지명</td>
								<td>대관령 양떼목장</td>
							</tr>
							<tr>
								<td>주소</td>
								<td></td>
							</tr>
							<tr>
								<td>카테고리</td>
								<td></td>
							</tr>
							<tr>
								<td>사이트 링크</td>
								<td></td>
							</tr>
							<tr>
								<td>등록일</td>
								<td></td>
							</tr>
							<tr>
								<td>수정일</td>
								<td></td>
							</tr>
						</table>
					</li>
					<li>
						<table>
							<caption>추천 도서</caption>
							<tr>
								<td>도서명</td>
								<td>개미</td>
							</tr>
							<tr>
								<td>저자</td>
								<td></td>
							</tr>
							<tr>
								<td>출판사</td>
								<td></td>
							</tr>
							<tr>
								<td>구매링크</td>
								<td></td>
							</tr>
							
							<tr>
								<td>등록일</td>
								<td></td>
							</tr>
							<tr>
								<td>다음제안</td>
								<td></td>
							</tr>
						</table>
					</li>
				</ul>
				
			</div>
		</div>
		
	</section>
	
	
	
	<jsp:include page="../include/footer.jsp" />
	
	<script>
				        function loadNextInfo() {
				            // Ajax를 사용하여 서버에서 새로운 정보를 가져옵니다.
				            $.ajax({
				                url: "pm/getNextPmRec",  // 서버의 엔드포인트 URL
				                method: "GET",
				                success: function(data) {
				                    // 성공적으로 데이터를 받아왔을 때 동적으로 내용을 업데이트합니다.
				                    // 여기에서는 특정 행에 새로운 데이터를 추가하는 방식으로 예제를 작성했습니다.
				                    
				                    if(!data) {
				                    	alert("모든 추천을 확인하셨습니다. 다시 보시려면 버튼을 눌러주세요.")
				                    }
				                    else {
				                    	$("#city_name").html(data["city_name"]);
					                    $("#city_airQuality").html(data["city_airQuality"]);
					                    $("#city_pm10").html(data["city_pm10"]);
					                    $("#city_pm2_5").html(data["city_pm2_5"]);
					                    $("#upload_date").html(data["upload_date"]);
					                    console.log(data)
				                    }
				                    
				                },
				                error: function() {
				                    alert("다음 정보를 가져오는 데 실패했습니다.");
				                }
				            });
				        }
				    </script>
</body>
</html>