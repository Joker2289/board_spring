 <%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Dashboard Template for Bootstrap</title>
	<!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath() %>/css/dashboard.css" rel="stylesheet">
    
    
</head>
<body>
	
	
	<%@ include file="/WEB-INF/views/module/header_D.jsp" %>
	
	<%@ include file="/WEB-INF/views/module/side_D.jsp" %>
	
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	
          <h1 class="page-header">사용자 정보 조회</h1>
			
		  <form action="${ cp }/userModifyForm" method="get" class="form-horizontal" role="form">
				
				<div class="form-group">
					<label for="userImg" class="col-sm-3 control-label" style="display:table-cell; height:300px; background:#eee; vertical-align:middle;">사용자 프로필</label>
					<div class="col-sm-9">
					
						<!-- Servlet 요청으로 사진 출력 -->
						<img src="${ cp }/user/profileImg?userId=${userVO.userId}" width="300" height="300">
					
						<!-- 직접 사진 유무 비교후 출력 -->
						
						<!-- choose 사용 -->
						<%-- <c:choose>
							<c:when test="${ userVO.fileName != null }">
								<img src="${ cp }/upload/${ userVO.fileName }" width="300" height="300">
							</c:when>
							<c:otherwise> 
								<img src="${ cp }/upload/noimg2.png" width="300" height="300">
							</c:otherwise> 
						</c:choose> --%>
						
						<!-- if 사용 -->
						<%-- <c:set var="pic" value="${ userVO.fileName }">
						</c:set> --%>
						
					</div>
				</div>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-3 control-label">아이디</label>
					<div class="col-sm-9">
						<label class="control-label">${ userVO.userId }</label>
					</div>
				</div>

				<div class="form-group">
					<label for="userNm" class="col-sm-3 control-label">이름</label>
					<div class="col-sm-9">
						<label class="control-label">${ userVO.userNm }</label>
					</div>
				</div>
			
				<div class="form-group">
					<label for="userNm" class="col-sm-3 control-label">닉네임</label>
					<div class="col-sm-9">
						<label class="control-label">${ userVO.alias }</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="reg_dt" class="col-sm-3 control-label">우편번호</label>
					<div class="col-sm-9">
						<label class="control-label">${ userVO.zipcode }</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="reg_dt" class="col-sm-3 control-label">주소</label>
					<div class="col-sm-9">
						<label class="control-label">${ userVO.addr1 }</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="reg_dt" class="col-sm-3 control-label">상세주소</label>
					<div class="col-sm-9">
						<label class="control-label">${ userVO.addr2 }</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="reg_dt" class="col-sm-3 control-label">등록일자</label>
					<div class="col-sm-9">
						<label class="control-label">${ userVO.reg_dt_fmt }</label>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<!-- 사용자 수정 버튼 생성 -->
			           	<button id="updBtn" type="button" class="btn btn-default">사용자 수정</button>
					</div>
				</div>
				
				<%-- <div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<!-- 사용자 수정 버튼 생성 -->
						<input type="hidden" name="userId" value="${ userVO.userId }"/>
			           	<button type="submit" class="btn btn-default">사용자 수정</button>
					</div>
				</div>   --%>
		 </form>
		
            
    </div>
	
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
    <script>
    
    $(document).ready(function(){
    	<c:if test="${ msg != null }">
			alert("${ msg }");
			<%-- <% session.removeAttribute("msg"); %> --%>
		</c:if>
		
	  	//사용자 수정 버튼 클릭 이벤트
		$("#updBtn").on("click", function(){
			
			$("#userId").val("${ userVO.userId }");
			
			//정상적으로 vaildation이 완료 --> form 전송
			$("#frm").submit();
			
		});
    }); 
    
    
    </script>

<form id="frm" action="${ cp }/user/userModifyForm" method="get">
	<input type="hidden" id="userId" name="userId" />
</form>
</body>
</html>