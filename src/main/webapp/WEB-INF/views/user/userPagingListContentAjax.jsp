<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

          <h1 class="page-header">전체 사용자 리스트 (ajax Tiles)</h1>
			
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
              
              <tbody id="userListTbody">
              		<!-- 사용자 리스트 출력 -->
              </tbody>
              
            </table>
            
            <!-- 사용자 등록 버튼 생성 -->
            <form action="${ cp }/user/userForm" method="get">
           		<button type="submit" class="btn btn-default">사용자 등록</button>
            </form>
            
            
            <!-- pageNation 생성 -->
			<!-- EL / JSTL -->
            <c:set var="lastPage" value="${ Integer(userCnt/pageSize + (userCnt%pageSize > 0 ? 1:0 )) }"/>
			
			<!-- 스타일 적용 -->	
           	<nav style="text-align: center;"> 		
	            <ul id="pagination" class="pagination">    <!-- id 생성 -->
	            
	            	<!-- 페이지 네비게이션 구역 -->
	            	
			  	</ul>
			</nav>

          </div>
          
    <script type="text/javascript">
    	
    	//사용자 배열을 이용하여 사용자 리스 HTML을 생성
    	function makeUserList(userList){
    		var html = "";
    		
    		for(var i=0; i<userList.length; i++){
    			var user = userList[i];
    			
    			html += "<tr class='userTr' data-userid='" + user.userId + "'>";
    			html += "	<td>" + user.userId + "</td>";		
    			html += "	<td>" + user.userNm + "</td>";
    			html += "	<td>" + user.alias + "</td>";
    			html += "	<td>" + user.reg_dt_fmt + "</td>";
    			html += "</tr>";
    			
    		}
    		
    		$("#userListTbody").html(html);
    		
    	}	
    	
    	function makePagination(userCnt, pageSize, page){
    		var lastPage = parseInt(userCnt / pageSize) + (userCnt%pageSize > 0 ? 1 : 0);
    		var html = "";
    		
    		if(page == 1){
    			html += "<li class='disabled'>";
    			html += "	<a aria-label='Previous'>";
    			html += "		<span aria-hidden='true'> &laquo; </span>";
    			html += "	</a>";
    			html += "</li>";
    		} else {
    			 html += "<li>";
			     //html += "  <a href='${ cp }/user/userPagingList?page=1' aria-label='Previous'>";
			     html += "  <a href='javascript:getUserPageList(1)' aria-label='Previous'>";
			     html += "    <span aria-hidden='true'>&laquo;</span>";
			     html += "  </a>";
			     html += "</li>	";
    		}
    		
    		for(var i=1; i<=lastPage; i++){
    			var active = "";
    			if( i == page ){
					active = "active";    				
    			}
    			
    			html += "<li class='" + active + "'>";
    			html += "	<a href='javascript:getUserPageList(" + i + ");'>" + i + "</a>";
    			html += "</li>";
    		}
    		
    		if(page == lastPage){
    			html += "<li class='disabled'>";
    			html += "	<a aria-label='Next'>";
    			html += "		<span aria-hidden='true'> &raquo; </span>";
    			html += "	</a>";
    			html += "</li>";
    		} else {
    			 html += "<li>";
			     html += "  <a href='javascript:getUserPageList(" + lastPage + ")' aria-label='Next'>";
			     html += "    <span aria-hidden='true'>&raquo;</span>";
			     html += "  </a>";
			     html += "</li>	";
    		}
    		$("#pagination").html(html);
    	}
    	
    	function getUserPageList(page){
    		$.ajax({
    			url : "${ cp }/user/userPagingListAjax",
    			data : "page=" + page,
    			success : function(data) {
    				console.log(data);
    				makeUserList(data.userList);
    				makePagination(data.userCnt, data.pageSize, data.page);
    				
    			}
    			
    		});
    	}
    	
    	function getUserPageListHtml(page){
    		$.ajax({
    			url : "${ cp }/user/userPagingListAjaxHtml",
    			data : "page=" + page,
    			success : function(data) {
    				console.log(data);
    				
    				var htmlArr = data.split("========== seperator ==========");
    				
    				$("#userListTbody").html(htmlArr[0]);
    				$("#pagination").html(htmlArr[1]);
    				
    				$(".userTr").on("click", function(){
    	    			
    	    			var userId = $(this).data("userid");
    	    			
    	    			$("#userId").val(userId);
    	    			$("#frm").submit();	//form을 숨기고 강제 제출 하게 한다
    	    			
    	    		});
    			}
    			
    		});
    	}
    	
    	$(document).ready(function(){
    		
    		//getUserPageList(1);
    		
    		getUserPageListHtml(1);
    		
    		<c:if test="${ msg != null }">
				alert("${ msg }");
				<% session.removeAttribute("msg"); %>
			</c:if>
			
			$("#userListTbody").on("click", ".userTr", function(){
    			
    			var userId = $(this).data("userid");
    			
    			$("#userId").val(userId);
    			$("#frm").submit();	//form을 숨기고 강제 제출 하게 한다
    			
    		});
    		
    	});
    </script>
    
<form id="frm" action="<%= request.getContextPath()%>/user/user" method="get">
	<input type="hidden" id="userId" name="userId" />
</form>

