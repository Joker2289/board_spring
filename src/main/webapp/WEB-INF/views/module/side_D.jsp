<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.menu_nav {
	/* border-bottom:1px solid #333; */
	padding-bottom: 15px;
	margin-bottom: 0px;
	padding-top: 10px !important;
}

.cate_title {
	background: #666;
	color: #fff;
	padding: 10px;
	margin: 0 10px 0 10px;
	text-weight: bold;
}
</style>


     <div class="container-fluid">
     	<div class="row">
     		<div class="col-sm-3 col-md-2 sidebar">
     		
	     		<!-- 사용자 프로필 -->
				<div style="text-align: center;">
					<ul class="nav nav-sidebar menu_nav">
						<img style="border: 6px solid #666666; border-radius: 70px; -moz-border-radius: 70px; -khtml-border-radius: 70px; -webkit-border-radius: 70px;" width="240" height="240" src="${ pageContext.request.contextPath }/user/profileImg?userId=${ userVO_log.userId }" />
					</ul>
				</div>
				
				<ul class="nav nav-sidebar menu_nav">
					<!-- 링크를 클릭하는 행위는 get방식 -->
					<li style="text-align: center;"> ${ userVO_log.userId } <span class="sr-only">(current)</span></li>
					
					<c:if test="${ userVO_log.alias != null }">
						<li style="text-align: center;"> 닉네임 : ${ userVO_log.alias } <span class="sr-only">(current)</span></li>
					</c:if>
					<c:if test="${ userVO_log.alias == null }">
						<li style="text-align: center;"> 닉네임 : 미설정 <span class="sr-only">(current)</span></li>
					</c:if>
				</ul>
     		
     			<!-- 게시판 관리 -->
				<ul class="nav nav-sidebar menu_nav">
					<!-- 링크를 클릭하는 행위는 get방식 -->
					<li class="cate_title" style="text-align: center;">게시판 관리</li>
					<li style="text-align: center;"><a href="<%=request.getContextPath()%>/board/boardManagementView"> 게시판 설정 <span class="sr-only">(current)</span></a></li>
				</ul>
     		
	     	  <ul class="nav nav-sidebar menu_nav">
	          	<!-- 링크를 클릭하는 행위는 get방식 -->
	          	<li class="cate_title">사용자</li>
	            <li><a href="${cp}/user/userAllList"> 사용자 리스트 <span class="sr-only">(current)</span></a></li>
	            <li><a href="<%=request.getContextPath()%>/user/userPagingList"> 사용자 리스트 페이징 <span class="sr-only">(current)</span></a></li>
	            <li><a href="<%=request.getContextPath()%>/user/userPagingListAjaxView"> 사용자 리스트 페이징(ajax) <span class="sr-only">(current)</span></a></li>
	            <li><a href="<%=request.getContextPath()%>/user/userForm"> 사용자 등록 <span class="sr-only">(current)</span></a></li>
	          </ul>
	          
	          
	          
	          <ul class="nav nav-sidebar menu_nav">
	          	<!-- 링크를 클릭하는 행위는 get방식 -->
				<li class="cate_title" style="text-align: center;">게시판 리스트</li>

				<c:forEach items="${ boardList }" var="vo">
					<c:if test="${ vo.act_state == 'y' }">
						<li style="text-align: center;"><a href="${ pageContext.request.contextPath }/board/boardSelect?board_code=${ vo.board_code }&board_name=${ vo.board_name }"> ${ vo.board_name } <span class="sr-only">(current)</span></a></li>
					</c:if>
				</c:forEach>
	          </ul>
	          
	          
          </div> 
        </div> 
     </div>


