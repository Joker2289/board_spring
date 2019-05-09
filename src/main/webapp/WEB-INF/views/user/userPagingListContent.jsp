<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

          <h1 class="page-header">전체 사용자 리스트</h1>
			
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
    		
    		$(".userTr").on("click", function(){
    			
    			//클릭한 userTr태그의 userId 값을 출력
    			var userId = $(this).data("userid");
    			
    			$("#userId").val(userId);
    			$("#frm").submit();	//form을 숨기고 강제 제출 하게 한다
    			
    		});
    		
    	});
    </script>
<form id="frm" action="<%= request.getContextPath()%>/user/user" method="get">
	<input type="hidden" id="userId" name="userId" />
</form>
