<%@page import="com.hallym.getawayfrompm.admin.member.AdminMemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value='/resources/css/admin/include/nav.css' />" rel="stylesheet" type="text/css">

<jsp:include page="./nav_js.jsp" />

<nav>
	
	<div id="nav_wrap">
		
		<%
		AdminMemberVo loginedAdminMemberVo = (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
		if (loginedAdminMemberVo != null) {
		%>
		
		<div class="menu">
			<ul>
				<li><a href="<c:url value='/admin/member/logoutConfirm' />">로그아웃</a></li>
				<li><a href="<c:url value='/admin/member/modifyAccountForm' />">계정수정</a></li>
				<li><a href="<c:url value='/reviewPage' />">리뷰게시판</a></li>
				<c:if test="${loginedAdminMemberVo.a_m_id eq 'super admin'}">
					<li><a href="<c:url value='/admin/member/listupAdmin' />">관리자목록</a></li>
				</c:if>
			
			</ul>
		</div>
		<%
		} else {
		%>
		<div class="menu">
			<ul>
				<li><a href="<c:url value='/admin/member/loginForm' />">로그인</a></li>
				<li><a href="<c:url value='/admin/member/createAccountForm' />">회원가입</a></li>
			</ul>
		</div>
		<%
		}
		%>
		
		
	</div>
	
</nav>