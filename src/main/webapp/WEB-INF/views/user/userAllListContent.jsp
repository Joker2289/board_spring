<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
	
          <h1 class="page-header">전체 사용자 리스트 (tiles)</h1>
			
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
	            	  		<td> <fmt:formatDate value="${ vo.reg_dt }" pattern="yyyy/MM/dd" /> </td>
            	  		</tr>      		
             		</c:forEach>
             		
              </tbody>
              
            </table>
          </div>
	
    <script type="text/javascript">
    	$(document).ready(function(){
    		console.log("document ready");
    		
    		
    		$(".userTr").on("click", function(){
    			
    			//클릭한 userTr태그의 userId 값을 출력
    			var userId = $(this).data("userid");
    			
    			$("#userId").val(userId);
    			$("#frm").submit();	//form을 숨기고 강제 제출 하게 한다
    			
    		});
    	});
    </script>
    
<form id="frm" action="${ pageContext.request.contextPath }/user/user" method="get">
	<input type="hidden" id="userId" name="userId" />
</form>
