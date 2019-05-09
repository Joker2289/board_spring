<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
		<h1 class="page-header">글 상세보기</h1> 
		
		<!-- 게시글 정보 출력 폼 -->
		<div class="form-group">
			<div class="col-sm-12">
				<li style="margin: 10px; padding: 10px; font-size: 14pt;"> 제목 : ${ noticeVO.title } </li>
				<li style="margin: 10px; padding: 10px; font-size: 14pt;"> 작성자 : ${ noticeVO.userId } </li>
				<li style="margin: 10px; padding: 10px; font-size: 14pt;"> 작성일시 : <fmt:formatDate value="${ noticeVO.rep_dt }" pattern="yyyy/MM/dd hh:mm:ss" /> </li>
				
				
				<!-- 첨부파일 출력 -->
				<c:forEach var="vo" items="${ attachmentList }">
					<a href="${ pageContext.servletContext.contextPath }/attachment/fileDownload?file_num=${ vo.file_num }">${ vo.file_name }</a><br>
				</c:forEach>
				
				<!-- 구분선 -->
				<hr style="border: 1px solid #333;"/>
			</div>
		</div>
		
		<!--게시글 내용 출력 폼-->
		<div class="form-group">
			
			<div class="col-sm-12" style="margin: 10px; padding: 10px; font-size: 14pt; /* border-top:1px solid #333; border-bottom:1px solid #333 */"> 
			
				${ noticeVO.content }
				
			</div>
		</div>
		
		<!-- 게시글 메뉴 폼 -->
		<div class="form-group">
			<div class="col-sm-12">
			
				<!-- 구분선 -->
				<hr style="border: 1px solid #333;"/>
					
				<div style="float: right;">
				
					<!-- 작성자와 login ID 가 같을 때 버튼 생성 -->
					<c:if test="${ noticeVO.userId == loginId }">
						<button type="button" id="updNotice" class="btn btn-default">수정</button>
						<button type="button" id="delNotice" class="btn btn-default">삭제</button>
					</c:if>
					
					<button type="button" id="addNotice" class="btn btn-default">답글</button>
					<button type="button" id="goList" class="btn btn-default">목록</button>
				</div>
			</div>
		</div>
		
		<!-- 댓글 작성 폼 -->
		<div class="form-group">
			<div class="col-sm-12" style="text-align: center; margin: 30px; padding: 30px; height:100px;">
				<div class="col-sm-10">
					<form id="frm_comment" action="${ pageContext.servletContext.contextPath }/comments/addComment" method="post">
						<textarea name="comment_content" id="comment_content" rows="10" cols="100" style="width:1000px; height:100px; "></textarea>
						<input type="hidden" id="notice_num" name="notice_num" value="${ noticeVO.notice_num }"/>
						<input type="hidden" id="board_name" name="board_name" value="${ board_name }"/>
						<input type="hidden" id="board_code" name="board_code" value="${ board_code }"/>
					</form>
				</div>
				<div class="col-sm-1">
					<button type="button" id="addComment" class="btn btn-default" style="width:100px; height:100px;" >댓글 작성</button>
				</div>	
			</div>
		</div>

		
		<!-- 댓글 출력 폼 -->
		<div class="form-group">
			<div class="col-sm-12" style="text-align: center; padding: 30px;">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th><h3>Comment</h3></th>
							</tr>
						</thead>

						<tbody>
							
							<c:forEach items="${ commentsList }" var="vo">
								<c:if test="${ vo.del_state == 'n' }">
									<tr class="noticeTr" data-notice_num="${ vo.notice_num }">
										<td>${ vo.comment_num }</td>
										<td>${ vo.comment_content }</td>
										<td>${ vo.userId }</td>
										<td><fmt:formatDate value="${ vo.rep_dt }" pattern="yyyy/MM/dd hh:MM:ss" /></td>
										<c:if test="${ vo.userId == loginId }">
											<td><a href="${ pageContext.servletContext.contextPath }/comments/updComment?comment_num=${ vo.comment_num }&notice_num=${ noticeVO.notice_num }&board_code=${ board_code }&board_name=${ board_name }">삭제</a></td>
										</c:if>
										<c:if test="${ vo.userId != loginId }">
											<td></td>
										</c:if>
									</tr>
								</c:if>
								<c:if test="${ vo.del_state == 'y' }">
									<tr class="noticeTr" data-notice_num="${ vo.notice_num }">
										<td colspan="5">삭제된 댓글 입니다</td>
									</tr >
								</c:if>
							</c:forEach>



						</tbody>
					</table>

					
				</div>
			</div>
		</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		
		//메시지 출력
		<c:if test="${ msg != null }">
			alert("${ msg }");
			<% session.removeAttribute("msg"); %>
		</c:if>
		
		//수정버튼 이벤트
		$("#updNotice").click(function() {
			document.location = "${ pageContext.servletContext.contextPath }/notice/noticeUpdate?notice_num=${ noticeVO.notice_num }&board_name=${ board_name }&board_code=${ board_code }";
		});
		
		//삭제버튼 이벤트
		$("#delNotice").click(function() {
			document.location = "${ pageContext.servletContext.contextPath }/notice/deleteNotice?notice_num=${ noticeVO.notice_num }&board_name=${ board_name }&board_code=${ board_code }";
		});

		//목록버튼 이벤트
		$("#goList").click(function() {
			document.location = "${ pageContext.servletContext.contextPath }/board/boardSelect?board_code=${ board_code }&board_name=${ board_name }";
		});
		
		//답글버튼 이벤트
		$("#addNotice").click(function() {
			document.location = "/notice/noticeForm?board_code=${ board_code }&board_name=${ board_name }&reply=y&parent_num=${ noticeVO.notice_num }";
		});
		
		//댓글작성 버튼 이벤트
		$("#addComment").click(function() {
			
			//$("#comment").val($("#commnet_content").val());
			$("#frm_comment").submit();
		});
		
		
	});
	
</script>



