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
	
          <h1 class="page-header">사용자 정보 수정 </h1>
			
		  <form id="frm" action="${ cp }/user/userModifyForm" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
		  
		  		<div class="form-group">
					<ul class="nav nav-sidebar menu_nav">
						<li class="cate_title" > 필수 입력 정보</li>
					</ul>	
				</div>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-1 control-label">사용자 프로필</label>
					<div class="col-sm-6">
						<img src="" width="300" height="300"/>
						<input type="file" class="form-control" id="profile" name="profile" placeholder="사진">
					</div>
				</div>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-1 control-label">아이디</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="userId" name="userId" placeholder="아이디 입력" readonly>
						<!-- placeholder 값이 없을때 설정값 -->
					</div>
				</div>
				
				<div class="form-group">
					<label for="pass" class="col-sm-1 control-label">Password</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="pass" name="pass" placeholder="비밀번호 입력">
					</div>
				</div>

				<div class="form-group">
					<label for="userNm" class="col-sm-1 control-label">이름</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="userNm" name="userNm" placeholder="사용자 이름 입력">
					</div>
				</div>
			
				<div class="form-group">
					<label for="userNm" class="col-sm-1 control-label">닉네임</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="alias" name="alias" placeholder="닉네임 입력">
					</div>
				</div>
				
				<div class="form-group">
					<ul class="nav nav-sidebar menu_nav">
						<li class="cate_title" > 상세정보 </li>
					</ul>	
				</div>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-1 control-label">우편번호</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호 찾기" readonly>
					</div>
					<div class="col-sm-3">
						<button id="zipcodeBtn" type="button" class="btn btn-default">검색</button>
					</div>
				</div>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-1 control-label">주소</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="addr1" name="addr1" placeholder="주소" readonly>
					</div>
				</div>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-1 control-label">상세주소</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="addr2" name="addr2" placeholder="상세주소 입력">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-6">
						<button id="updBtn" type="button" class="btn btn-default">사용자 수정</button>
					</div>
				</div>
		 </form>
            
    </div>
	
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
    <!-- 다음 우편번호 API -->
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script>
		function initData(){
			$("#userId").val("${ userVO.userId }");
			$("#userNm").val("${ userVO.userNm }");
			$("#alias").val("${ userVO.alias }");
			$("#addr1").val("${ userVO.addr1 }");
			$("#addr2").val("${ userVO.addr2 }");
			//$("#pass").val("${ userVO.pass }");
			$("#zipcode").val("${ userVO.zipcode }");
			$("#profileNm").val("${ userVO.realFileName }");
			$("img").attr("src", "${ cp }/user/profileImg?userId=${userVO.userId}");
		}	
	
		$(document).ready(function(){
			initData();
			
			//server side에서 비교
			<c:if test="${ sessionScope.msg != null }">
				alert("${ sessionScope.msg }");
				<% session.removeAttribute("msg"); %>
			</c:if>
			
			//우편번호 검색 버튼 클릭 이벤트 : 다음 주소검색 팝업 open
			$("#zipcodeBtn").on("click", function(){
				new daum.Postcode({
			        oncomplete: function(data) {
			            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
			            
			            console.log(data);
			            
			            //새 우편번호 : data.zonecode
			            $("#zipcode").val(data.zonecode);
			            
		            	//기본주소(도로주소) : data.roadAddress
			            $("#addr1").val(data.roadAddress);
		            	
		            	//상세주소 input focus
		            	$("#addr2").focus();
			        }
			     }).open();
			});
		
			//사용자 등록 버튼 클릭 이벤트
			$("#updBtn").on("click", function(){
				//아이디
				if($("#userId").val().trim() == "") {
					alert("사용자 아이디를 입력해주세요	");
					$("#userId").focus();
					return false;
				}
				//비밀번호
// 				if($("#pass").val().trim() == "") {
// 					alert("비밀번호를 입력해주세요");
// 					$("#pass").focus();
// 					return false;
// 				}
				//이름
				if($("#userNm").val().trim() == "") {
					alert("사용자 아름을 입력해주세요");
					$("#userNm").focus();
					return false;
				}
				//닉네임
				if($("#alias").val().trim() == "") {
					alert("닉네임을 입력해주세요");
					$("#alias").focus();
					return false;
				} 
				//우편번호
				if($("#zipcode").val().trim() == "") {
					alert("우편번호를 입력해주세요");
					$("#zipcodeBtn").trigger("click");
					return false;
				}
				//주소1(생략)
				//주소2
				if($("#addr2").val().trim() == "") {
					alert("상세주소를 입력해주세요");
					$("#addr2").focus();
					return false;
				}
				//정상적으로 vaildation이 완료 --> form 전송
				$("#frm").submit();
				
			});
		});
	    
	</script>

</body>
</html>