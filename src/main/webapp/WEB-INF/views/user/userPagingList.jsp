<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
          <h1 class="page-header">전체 사용자 리스트</h1>
			
		  <!-- userList 정보를 화면에 출력하는 로직 작성 -->
		  <% request.getAttribute("userList"); %>
		  
		  <% List<UserVO> list = (List<UserVO>) request.getAttribute("userList"); %>
		  
		  <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>사용자 ID</th>
                  <th>사용자 이름</th>
                  <th>닉네임</th>
                  <th>등록일시</th>
                </tr>
              </thead>
              
              <tbody>
              
            <%--  <% for(int i=0; i<list.size(); i++) { %>
              						<!-- 클릭 했을 떄 id 값을 넘겨 주기위해 data-userid -->
            	  <tr class="userTr" data-userid="<%= list.get(i).getUserId() %>">
            	  
            	  		
            	  		<input type="hidden" class="userId" value="<%= list.get(i).getUserId() %>">
            	  		<td><%= list.get(i).getUserId() %></td>
            	  		<td><%= list.get(i).getUserNm() %></td>
            	  		<td><%= list.get(i).getPass() %></td>
            	  		<td><%= list.get(i).getReg_dt_fmt() %></td>
            	  </tr>
            	  
             <% } %>  --%>
             
             
             
             
             		<!-- EL / JSTL -->
             		<c:forEach items="${ userList }" var="vo">
						<tr class="userTr" data-userid="${ vo.userId }">
	            	  		<td>${ vo.userId }</td>
	            	  		<td>${ vo.userNm }</td>
	            	  		<td>${ vo.alias }</td>
	            	  		
	            	  		<%-- <td>${ vo.reg_dt_fmt }</td> --%>
	            	  		<td><fmt:formatDate value="${ vo.reg_dt }" pattern="yyyy/MM/dd"/></td>
            	  		</tr>      		
             		</c:forEach> 
             		
             		
              </tbody>
              
            </table>
            
            <!-- 사용자 등록 버튼 생성 -->
            <form action="${ cp }/user/userForm" method="get">
           		<button type="submit" class="btn btn-default">사용자 등록</button>
            </form>
            
            
            <!-- pageNation 생성 -->
            <%-- <%
            		int userCnt = (Integer) request.getAttribute("userCnt");
            		int pageSize = (Integer) request.getAttribute("pageSize");
            		int cpage = (Integer) request.getAttribute("page");
            		
            		//MOD함수 적용
            		//int lastPage = userCnt/pageSize + (userCnt%pageSize > 0 ? 1:0 );
            		
            		//ceil 메서드 이용
            		int lastPage = (int) Math.ceil(userCnt / pageSize);
            		
            		String cp = request.getContextPath();
            %> --%>
            
           
            
            <!-- JSP -->
            
            <!-- 스타일 적용 -->	
<%--        <nav style="text-align: center;"> 		
            <ul class="pagination">
            
             <!-- 1page일때 disabled 걸어주기 -->
			 <% if(cpage == 1) {%>
			    <li class="disabled">
			      <a href="<%=cp%>/userPagingList?page=1" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>	
			    
			    <% } else { %>	
			    <li>
			      <a href="<%=cp%>/userPagingList?page=1" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>	
			    <%} %>
            		
	            <!-- page 생성 -->
	            <% for(int i=1; i<=lastPage; i++) {%>
	            
	            	<li
	            		<% if(i == cpage){ %>
	            		
	            			class ="active"
	            		<% } %>
	            	
	            	><a href="<%=cp%>/userPagingList?page=<%= i %>"><%= i %></a></li>
	            <% } %>
            	
            	<!-- lastPage일때 disabled 걸어주기 -->
            	<% if(cpage==lastPage) {%>
		            <li class="disabled">
				      <a href="<%=cp%>/userPagingList?page=<%= lastPage %>" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
			    <% } else { %> 
				    <li>
				      <a href="<%=cp%>/userPagingList?page=<%= lastPage %>" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
			    <% } %>
		  	</ul>
			</nav> --%>
			
			
			<!-- EL / JSTL -->
			
			<%
           		new Integer((int) 11.5);
           		//형변환이 아니라 새로운 Integer 생성
      		%>
            <c:set var="lastPage" value="${ Integer(userCnt/pageSize + (userCnt%pageSize > 0 ? 1:0 )) }"/>
			
			<!-- 스타일 적용 -->	
           	<nav style="text-align: center;"> 		
            <ul class="pagination">
            
            	<!-- 1page일때 disabled 걸어주기 -->
			 	<c:choose>
			 		<c:when test="${ page == 1 }">
			 			<li class="disabled">
					      <a aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>	
			 		</c:when>
			 		<c:otherwise>
			 			 <li>
					       <a href="${ pageContext.servletContext.contextPath }/user/userPagingList?page=1" aria-label="Previous">
					         <span aria-hidden="true">&laquo;</span>
					       </a>
					     </li>	
			 		</c:otherwise>
			 	</c:choose>
			   
			    <!-- page 생성 -->
			    <c:forEach begin="1" end="${ lastPage }" var="i"> 
			    		<c:set var="active" value=""/>		<!-- Active 초기화 set을 쓰면 계속 남아서 초기화 해줘야 함-->
            		<c:if test="${ i == page }">
            			<c:set var="active" value="active"/>
            		</c:if>
            		
            		<li class=${ active }>
            			<a href="${ pageContext.servletContext.contextPath }/user/userPagingList?page=${ i }">${ i }</a>
            		</li>
            	</c:forEach>
            	
            	
			    
			    <!-- lastPage일때 disabled 걸어주기 -->
			    <c:choose>
			 		<c:when test="${ page == lastPage }">
			 			<li class="disabled">
					      <a aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>	
			 		</c:when>
			 		<c:otherwise>
			 			 <li>
					       <a href="${ pageContext.servletContext.contextPath }/user/userPagingList?page=${ lastPage }" aria-label="Next">
					         <span aria-hidden="true">&raquo;</span>
					       </a>
					     </li>	
			 		</c:otherwise>
			 	</c:choose>
		  	</ul>
			</nav>


          </div>
    </div>
	
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    	//문서로딩이 완료된 이후 이벤트 등록
    	$(document).ready(function(){
    		
    		//msg 속성이 존재하면 alert, 존재하지 않으면 넘어가기
    		<c:if test="${ msg != null }">
				alert("${ msg }");
				<% session.removeAttribute("msg"); %>
			</c:if>
    		
    		//사용자 tr 태그 클릭시 이벤트 핸들러
    		//방법1
    		/* $(".userTr").click(function(){
    			
    		}); */
    		
    		//방법2
    		$(".userTr").on("click", function(){
    			
    			//클릭한 userTr태그의 userId 값을 출력
    			var userId = $(this).data("userid");
    			
    			// /user
    			
    			// userId 파라미터값 넘기기
    			// 1.document 	이용한 방법
    			//document.location = "/user?userId=" + userId;
    			
    			// 2.form		이용한 방법
    			$("#userId").val(userId);
    			$("#frm").submit();	//form을 숨기고 강제 제출 하게 한다
    			
    			//$("#frm").attr("action", "/userAllList");		//속성을 따로 바꿀수도 있다
    			
    			
    		});
    		
    	});
    </script>
<form id="frm" action="<%= request.getContextPath()%>/user/user" method="get">
	<input type="hidden" id="userId" name="userId" />
</form>
</body>
</html>