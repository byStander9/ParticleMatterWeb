<%@page import="com.hallym.getawayfrompm.admin.member.AdminMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="javax.servlet.ServletException" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.File" %>
<%
    // 서블릿 컨텍스트를 통해 루트 경로를 가져옴
    String contextPath = request.getContextPath();
    String rootPath = request.getServletContext().getRealPath("/");

    // 이미지의 상대 경로
    String imagePath = "resources/image/thumbsUp.jpg";

    // 이미지의 절대 경로
    String absoluteImagePath = rootPath + imagePath;
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/main.css' />" rel="stylesheet" type="text/css">

<link href="<c:url value='/resources/css/admin/login_form.css' />" rel="stylesheet" type="text/css">

<jsp:include page="./include/login_js.jsp" />

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>

<script>
    // /admin 이외의 경로에서 main.jsp를 열면 /admin으로 리다이렉트
    if (window.location.pathname !== '/getawayfrompm/admin') {
        window.location.replace('/getawayfrompm/admin');
    }
</script>
	
	<jsp:include page="../include/header.jsp" />
	
	<jsp:include page="./include/nav.jsp" />
	
	<section>
		
		<div id="section_wrap">
		
		

		<%
		AdminMemberVo loginedAdminMemberVo = (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
		if (loginedAdminMemberVo != null) {
			%>
	        <input type="hidden" id="loginedAdminMemberVo" value="<%= loginedAdminMemberVo %>" />
	        <%
		%>
		
			<div class="word">
			
				<h3>Recommendation</h3>
				
			</div>
			
			<div class="recommendation">
			
				<ul id=pmCity>
					<li>
						<table>
							<caption>미세먼지 수치가 좋은 지역</caption>
							<tr>
								<td>지역명</td>
								<td id="city_name"></td>
							</tr>
							<tr>
								<td>통합대기환경수치</td>
								<td id="city_airQuality"></td>
							</tr>
							<tr>
								<td>미세먼지(PM10) 농도</td>
								<td id="city_pm10">지역추천 대기중 - 하단 버튼을 눌러주세요</td>
							</tr>
							<tr>
								<td>초미세먼지(PM2.5) 농도</td>
								<td id="city_pm2_5"></td>
							</tr>
							
							<tr>
								<td>등록일</td>
								<td id="upload_date"></td>
							</tr>
							<tr>
								<td>다음제안</td>
								<td>
									<button type="button" onclick="loadNextPmInfo()">NEW</button>
								</td>
							</tr>
						</table>
					</li>
    
					<li id="place">
						<table>
							<caption>추천 여행지</caption>
							<tr>
								<td>관광지명</td>
								<td id="place_name"></td>
							</tr>
							<tr>
								<td>주소</td>
								<td id="place_roadAddress">미세먼지를 먼저 확인하시고 하단 버튼을 눌러주세요</td>
							</tr>
							<tr>
								<td>사이트</td>
								<td> <a href="" id="place_link"></a></td>
							</tr>
							<tr>
								<td>다음제안</td>
								<td><button type="button" onclick="loadNextPlaceInfo()">NEW</button></td>
							</tr>
						</table>
						 
					</li>
					
				</ul>
				<ul>
					<li>
						<img id="placeImg" src="">
					</li>
					<li>
						<img src="resources/image/empty-star.png" id="star" onclick="showInputBox()">
						<div id="inputTextBox"></div>
					</li>
				</ul>
				
				
			</div>
			<%
		} else {
		%>
		<div id="section_wrap">
			
			<div class="word">
			
				<h3>LOGIN FORM</h3>
				
			</div>
			
			<div class="login_form">
			
				<form action="<c:url value='/admin/member/loginConfirm' />" name="login_form" method="post">
					
					<input type="text"		name="a_m_id" 		placeholder="INPUT ADMIN ID."> <br>
					<input type="password"	name="a_m_pw" 		placeholder="INPUT ADMIN PW."> <br>
					<input type="button"	value="login" onclick="loginForm();"> 
					<input type="reset"		value="reset">
					
				</form>
				
			</div>
			
			<div class="find_password_create_account">
				
				<a href="<c:url value='/admin/member/createAccountForm' />">create account</a>
				
			</div>
		
		</div>
		<%
		}
		%>
		</div>
		
	</section>
	
	
	
	<jsp:include page="../include/footer.jsp" />
	
	<script>
        function loadNextPmInfo() {
            $.ajax({
                url: "pm/getNextPmRec",  // 서버의 엔드포인트 URL
                method: "GET",
                success: function(data) {
                    
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
            
            var hiddenItem = document.getElementById("place");
            hiddenItem.style.display = "list-item";
        }
        
        function loadNextPlaceInfo() {
        	var city_name = $('#city_name').text();
        	if(city_name == "") {
        		alert('미세먼지 수치를 먼저 조회해주세요!');
        		return;
        	}
            $.ajax({
                url: "/getawayfrompm/place/getNextPlaceRec", // 위에서 작성한 JSP 페이지의 경로
                method: "GET",
                data: { param: city_name },
                success: function(response) {
                	if(!response) {
                    	alert("모든 추천을 확인하셨습니다. 다시 보시려면 버튼을 눌러주세요.")
                    }
                	else {
                		$("#place_name").html(response["place_name"])
                		$("#place_roadAddress").html(response["place_roadAddress"])
                		$("#place_link").html(response["place_link"])
                		$("#place_link").attr('href', response["place_link"])
                		$('#placeImg').attr('src', "/getawayfrompm/" + response["place_img"]);
                	}
                    
                },
                error: function() {
                    // 실패했을 때의 처리
                    alert('Failed to fetch file content.');
                }
            });
        }
        
        function showInputBox() {
        	// 노란색 별이미지로 변경
        	$('#star').attr('src', "resources/image/filled-star.png");

            // 입력창 생성
            var inputField = document.createElement("input");
            inputField.type = "text";
            inputField.placeholder = "리뷰를 입력해주세요";

            var placeName = $('#place_name').text();
            var loginedAdminMemberVo = <%= loginedAdminMemberVo %>;
            var userName = loginedAdminMemberVo.a_m_name;
            console.log(userName);
            // 저장 버튼 생성
            var saveButton = document.createElement("button");
            saveButton.innerHTML = "저장";
            saveButton.onclick = function() {
           		var review = inputField.value;
	            var xhr = new XMLHttpRequest();
	            xhr.open("POST", "saveText?review=" + review + "&placeName=" + placeName + "&userName=" + userName, true);
	            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	            xhr.onreadystatechange = function () {
	                if (xhr.readyState === 4 && xhr.status === 200) {
	                    alert(xhr.responseText);
	                }
	            };
	            xhr.send();
	        
            
	            // 입력창 닫기
	            inputField.style.display = "none";
	            saveButton.style.display = "none";
	            $('#star').attr('src', "resources/image/empty-star.png");
            };

            inputField.style.width = "400px"; // 너비 설정
            inputField.style.height = "350px"; // 높이 설정
            
			
            var inputTextBox = document.getElementById("inputTextBox");
            inputTextBox.appendChild(inputField);
            inputTextBox.appendChild(saveButton);
            
        }
    </script>
</body>
</html>
