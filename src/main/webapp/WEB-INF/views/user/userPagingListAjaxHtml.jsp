<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- EL / JSTL -->
<c:forEach items="${ userList }" var="vo">
	<tr class="userTr" data-userid="${ vo.userId }">

		<td>${ vo.userId }</td>
		<td>${ vo.userNm }</td>
		<td>${ vo.alias }</td>

		<%-- <td>${ vo.reg_dt_fmt }</td> --%>
		<td><fmt:formatDate value="${ vo.reg_dt }" pattern="yyyy/MM/dd" /></td>
	</tr>
</c:forEach>


========== seperator ==========

<c:set var="lastPage" value="${ Integer(userCnt/pageSize + (userCnt%pageSize > 0 ? 1:0 )) }"/>

<!-- 1page일때 disabled 걸어주기 -->
<c:choose>
	<c:when test="${ page == 1 }">
		<li class="disabled"><a aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a></li>
	</c:when>
	<c:otherwise>
		<li><a
			href="javascript:getUserPageListHtml(1)"
			aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
		</a></li>
	</c:otherwise>
</c:choose>

<!-- page 생성 -->
<c:forEach begin="1" end="${ lastPage }" var="i">
	<c:set var="active" value="" />
	<!-- Active 초기화 set을 쓰면 계속 남아서 초기화 해줘야 함-->
	<c:if test="${ i == page }">
		<c:set var="active" value="active" />
	</c:if>

	<li class=${ active }><a
		href="javascript:getUserPageListHtml(${ i })">${ i }</a>
	</li>
</c:forEach>



<!-- lastPage일때 disabled 걸어주기 -->
<c:choose>
	<c:when test="${ page == lastPage }">
		<li class="disabled"><a aria-label="Next"> <span
				aria-hidden="true">&raquo;</span>
		</a></li>
	</c:when>
	<c:otherwise>
		<li><a
			href="javascript:getUserPageListHtml(${ lastPage })"
			aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</c:otherwise>
</c:choose>