<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

          <h1 class="page-header">${ board_name }</h1>
			
		 
		  <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>등록일시</th>
                </tr>
              </thead>
              
              <tbody>
              
					
					<c:forEach items="${ noticeList }" var="vo">
						<tr class="noticeTr" data-notice_num="${ vo.notice_num }">
							<td>${ vo.notice_num }</td>
							<td>${ vo.title }</td>
							<td>${ vo.userId }</td>
							<td><fmt:formatDate value="${ vo.rep_dt }" pattern="yyyy/MM/dd" /></td>
						</tr>
					</c:forEach>
					
					<c:if test="${ noticeList == null }">
						<tr class="noticeTr" data-notice_num="${ vo.notice_num }">
							<td>등록된 게시글이 없습니다</td>
						</tr>
					</c:if>

			  </tbody>
            </table>
            
            <!-- 게시글 등록 버튼 생성 -->
            <form action="${ pageContext.request.contextPath }/notice/noticeForm" method="get">
           		<button type="submit" class="btn btn-default">글쓰기</button>
           		<input type="hidden" id="board_name" name="board_name" value="${ board_name }" />
           		<input type="hidden" id="board_code" name="board_code" value="${ board_code }"/>
            </form>
            
             <c:set var="lastPage" value="${ Integer(noticeCnt/pageSize + (noticeCnt%pageSize > 0 ? 1:0 )) }"/>
			
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
					       <a href="${ pageContext.servletContext.contextPath }/board/boardSelect?page=1&board_code=${ board_code }&board_name=${ board_name }" aria-label="Previous">
					         <span aria-hidden="true">&laquo;</span>
					       </a>
					     </li>	
			 		</c:otherwise>
			 	</c:choose>
			 	
			 	<c:choose>
			 		<c:when test="${ page == 1 }">
			 			<li class="disabled">
					      <a aria-label="Next">
					        <span aria-hidden="true">&lt;</span>
					      </a>
					    </li>	
			 		</c:when>
			 		<c:otherwise>
			 			 <li>
					       <a href="${ pageContext.servletContext.contextPath }/board/boardSelect?page=${ page -1 }&board_code=${ board_code }&board_name=${ board_name }" aria-label="Next">
					         <span aria-hidden="true">&lt;</span>
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
            			<a href="${ pageContext.servletContext.contextPath }/board/boardSelect?page=${ i }&board_code=${ board_code }&board_name=${ board_name }">${ i }</a>
            		</li>
            	</c:forEach>
            	
            	
            	<c:choose>
			 		<c:when test="${ page == lastPage }">
			 			<li class="disabled">
					      <a aria-label="Next">
					        <span aria-hidden="true">&gt;</span>
					      </a>
					    </li>	
			 		</c:when>
			 		<c:otherwise>
			 			 <li>
					       <a href="${ pageContext.servletContext.contextPath }/board/boardSelect?page=${ page + 1 }&board_code=${ board_code }&board_name=${ board_name }" aria-label="Next">
					         <span aria-hidden="true">&gt;</span>
					       </a>
					     </li>	
			 		</c:otherwise>
			 	</c:choose>
			    
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
					       <a href="${ pageContext.servletContext.contextPath }/board/boardSelect?page=${ lastPage }&board_code=${ board_code }&board_name=${ board_name }" aria-label="Next">
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
    
    	$(document).ready(function(){
    		
    		//메시지 출력
			<c:if test="${ msg != null }">
				alert("${ msg }");
				<% session.removeAttribute("msg"); %>
			</c:if>
    		
    		$(".noticeTr").on("click", function(){
    			
    			//클릭한 userTr태그의 userId 값을 출력
    			var notice_num = $(this).data("notice_num");
    			
    			$("#notice_num").val(notice_num);
    			
    			$("#frm").submit();	
    		});
    		
    	});
    </script>
    

<!-- EL / JSTL -->
<form id="frm" action="${ pageContext.servletContext.contextPath }/notice/noticeView" method="get">
	<input type="hidden" id="notice_num" name="notice_num" />
	<input type="hidden" id="board_name" name="board_name" value="${ board_name }"/>
	<input type="hidden" id="board_code" name="board_code" value="${ board_code }"/>
</form>
