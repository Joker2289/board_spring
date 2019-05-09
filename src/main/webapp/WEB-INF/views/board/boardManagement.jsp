<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




		<h1 class="page-header">게시판 관리</h1>
		<%-- pageContext.request.contextPath /userForm --%>

		<form id="frm" action="${ pageContext.request.contextPath }/board/insertBoard" method="post" class="form-horizontal" role="form">
			<div class="form-group">
				<ul class="nav nav-sidebar menu_nav">
					<li class="cate_title">게시판 등록</li>
				</ul>
			</div>

			<div class="form-group">
				<label for="userNm" class="col-sm-1 control-label">게시판 이름</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="board_name" name="board_name" placeholder="게시판 이름 입력">
				</div>
				<div class="col-sm-3">
					<button id="reg_board" type="button" class="btn btn-default">게시판
						등록</button>
				</div>
			</div>

			<div class="form-group">
				<ul class="nav nav-sidebar menu_nav">
					<li class="cate_title">게시판 설정</li>
				</ul>
			</div>
		</form>
		
         	<c:forEach items="${ boardList }" var="vo">
			<form id="frm_upd_${ vo.board_code }" action="${ pageContext.request.contextPath }/board/updateBoard" method="post" class="form-horizontal" role="form">
	         	<div class="form-group">
		         	<label for="userNm" class="col-sm-1 control-label">${ vo.board_code }</label>
		         	<div class="col-sm-3">
						<input type="text" class="form-control" name="board_name" value="${ vo.board_name }">
						<input type="hidden" class="form-control" name="board_code" value="${ vo.board_code }">
					</div>
					
		         	<!-- 비활성화 selectBox -->
		         	<div class="col-sm-1">
			         	<select id="act_state" name="act_state">
							<option value="y" <c:if test="${ vo.act_state == 'y' }"> selected </c:if> >활성화</option>
							<option value="n" <c:if test="${ vo.act_state == 'n' }"> selected </c:if> >비활성화</option>
						</select><br>
		         	</div>
		         	
		         	
		         	<div class="col-sm-3">
						<button id="${ vo.board_code }" type="button" class="btn btn-default">수정</button>
					</div>
				</div>
			</form>
           	</c:forEach>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

	<script>
		function initData(){
			<%-- $("#userId").val("<%= request.getParameter("userId") %>"                                                                                                                ); --%>
		}	
		
		$(document).ready(function(){
			initData();
			
			//메시지 출력
			<c:if test="${ msg != null }">
				alert("${ msg }");
				<% session.removeAttribute("msg"); %>
			</c:if>
			
			
			//server side에서 비교
			<c:if test="${ requestScope.msg != null }">
				alert("${ requestScope.msg }");
			</c:if>
			
		
			//게시판 등록 버튼 클릭 이벤트
			$("#reg_board").on("click", function(){
				if($("#board_name").val().trim() == "") {
					alert("게시판 이름을 입력해 주세요");
					$("#board_name").focus();
					return false;
				}
				//정상적으로 vaildation이 완료 --> form 전송
				$("#frm").submit();
			});
			
			
			//수정 버튼 클릭 이벤트
			<c:forEach items="${ boardList }" var="vo">
				$("#${ vo.board_code }").on("click", function(){
					
					$("#frm_upd_${ vo.board_code }").submit();
					
				});
       		</c:forEach>
			
			
		});
		
	</script>
