<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script src="/SE2/js/HuskyEZCreator.js"></script>
<!-- 에디터 사용 -->
<script type="text/javascript">
	var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.
	var index = 1;
	
	$(document).ready(function() {
		
		//최대 첨부파일 수
		var max_file = 5;

		remove_init();
		
		// 파일첨부 추가하기 버튼
		$("#file_add_div").on("click",function(){
			//사용중지
			file_add();
		});


		
		
		function remove_init(){
			$(".file_list input").off();
			$(".btn_remove").off();
			
			
			// 파일첨부 추가하기 보여주기
			$("#file_add_div").css("display","none");
			if($(".btn_remove").length == 0){
				$("#file_add_div").css("display","block");
			}
			
			
			// 마이너스 숨기기
// 			$(".btn_remove").css('display','block');
// 			$(".btn_remove").eq(0).css('display','none');
			
			
			// 파일첨부 제거하기
			$(".btn_remove").on("click",function(){
				$(this).closest(".file_list").remove();
				remove_init();
			});
			
			// 파일첨부 추가
			$(".file_list input").on("change",function(){
// 				console.log($(".file_list input").length + " / " + $(".file_list input").index(this))
				
				if($(".file_list input").index(this)+1 >= max_file){
					return;
				}
				
				if($(".file_list input").length == $(".file_list input").index(this)+1){
					file_add();
				}

			})
				
		}
		
		function file_add(){

			$("#file_container").append("\
					<div class='file_list' style='padding-top:5px; clear:both;' > \
					<input type='file' name='uploadFile' style='float:left;'/> \
					<button class='btn_remove' type='button' style='float:left; background:#333; color:#fff; border:1px solid #333;'>-</button></div> \
					");

 			remove_init();
		}

		//파일첨부 종료
		
		
				// Editor Setting
				nhn.husky.EZCreator.createInIFrame({
					oAppRef : oEditors, // 전역변수 명과 동일해야 함.
					elPlaceHolder : "content", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
					sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
					fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
					htParams : {
						// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseToolbar : true,
						// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseVerticalResizer : true,
						// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
						bUseModeChanger : true,
					}
				});

				// 전송버튼 클릭이벤트
				$("#savebutton").click(
					function() {
						if (confirm("등록하시겠습니까?")) {
							// id가 smarteditor인 textarea에 에디터에서 대입
							oEditors.getById["content"].exec(
									"UPDATE_CONTENTS_FIELD", []);

							// 이부분에 에디터 validation 검증
							if (validation()) {
								
								//답글요청일경우 [RE:] +  추가
								if("${ reply }" != ""){
									$("#title").val( "${ reply }" + $("#title").val());
									$("#reply").val("y");
								}
								
								
								$("#frm").submit();
							}
						}
					})
	
			}); //document ready 종료

	// 필수값 Check
	function validation() {
		var contents = $.trim(oEditors[0].getContents());
		if (contents === '<p>&nbsp;</p>' || contents === '') { // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
			alert("내용을 입력하세요.");
			oEditors.getById['smarteditor'].exec('FOCUS');
			return false;
		}

		return true;
	}
	
	
</script>

		<h1 class="page-header" style="font-weight: 800;">게시글 등록 - ${ board_name }</h1> 
		
		<form id="frm" action="${ pageContext.servletContext.contextPath }/notice/insertNotice" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<input type="text" class="form-control" id="title" name="title" placeholder="제목 입력">
			</div>
			
			
			
			
		 	<div class="box1">
				<div id="file_add_div"><button type="button" id="file_add">+</button> 파일첨부 추가하기 </div>
				<div id ="file_container">
				
				
				</div>
			</div>
				
				

			<!-- 에디터가 그려질 textarea -->
			<textarea name="content" id="content" rows="10" cols="100" style="width:1300px; height:400px;"></textarea> 
			
			<!-- 게시판 이름과 코드를 숨김 같이 전송 -->
			<input type="hidden" id="board_name" name="board_name" value="${ board_name }" />
           	<input type="hidden" id="board_code" name="board_code" value="${ board_code }" />
           	<input type="hidden" id="reply" name="reply" />
           	
   			<button type="button" id="savebutton" class="btn btn-default">작성완료</button>
    	</form>
	
