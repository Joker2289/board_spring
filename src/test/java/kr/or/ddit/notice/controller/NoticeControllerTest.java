package kr.or.ddit.notice.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.attachment.model.AttachmentVO;
import kr.or.ddit.comments.model.CommentsVO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVO;

public class NoticeControllerTest extends WebTestConfig {

	@Resource(name = "noticeService")
	private INoticeService noticeService;

	/**
	 * 
	 * Method : testNoticeView 작성자 : pjk 변경이력 :
	 * 
	 * @throws Exception Method 설명 : 상세조회 이동
	 */
	@Test
	public void testNoticeView() throws Exception {
		
		/*** Given ***/
		UserVO userVO = new UserVO();
		userVO.setUserId("joker");
		
		/*** When ***/
		MvcResult mvcResult = mockMvc.perform(get("/notice/noticeView")
				.param("notice_num", "85")
				.sessionAttr("userVO_log", userVO)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String loginId = (String) mav.getModelMap().get("loginId");
		List<CommentsVO> commentsList = (List<CommentsVO>) mav.getModelMap().get("commentsList");
		/*** Then ***/
		assertEquals("noticeViewTiles", viewName);
		assertEquals("joker", loginId);
		assertEquals("댓글 테스트", commentsList.get(1).getComment_content());
	}

	/**
	 * 
	 * Method : testNoticeForm 
	 * 
	 * 작성자 : pjk 
	 * 
	 * 변경이력 : @throws Exception
	 * 
	 * Method 설명 : 글작성 페이지로 이동 테스트
	 */
	@Test
	public void testNoticeForm() throws Exception {
		/*** Given ***/

		/*** When ***/
		MvcResult mvcResult = mockMvc.perform(get("/notice/noticeForm")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/*** Then ***/
		assertEquals("noticeFormTiles", viewName);
	}
	
	/**
	 * 
	 * Method : testInsertNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 글작성 요청 테스트
	 */
	@Test
	public void testInsertNotice() throws Exception {
		/*** Given ***/
		File profileFile = new File("/Users/pjk/Desktop/이미지/아이린4.gif");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = 
				new MockMultipartFile("uploadFile", "아이린.gif", "image/gif", fis);
		
		UserVO userVO = new UserVO();
		userVO.setUserId("joker");
		
		MvcResult mvcResult = 
				mockMvc.perform(fileUpload("/notice/insertNotice").file(file)
				.sessionAttr("userVO_log", userVO)
				.param("content", "test88")
				.param("title", "test88")
				.param("board_code", "5")
				.param("reply", "n")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***When***/
		HttpSession session = mvcResult.getRequest().getSession();
		
		/***Then***/
		assertEquals("등록 성공", session.getAttribute("msg"));
		assertEquals("redirect:/notice/noticeView", viewName);
	}
	
	/**
	 * 
	 * Method : testNoticeUpdate
	 * 작성자 : pjk
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 수정 페이지 이동 테스트
	 */
	@Test
	public void testNoticeUpdate() throws Exception {
		/*** Given ***/

		/*** When ***/
		MvcResult mvcResult = mockMvc.perform(get("/notice/noticeUpdate")
				.param("notice_num", "88")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<AttachmentVO> attachmentList = (List<AttachmentVO>) mav.getModelMap().get("attachmentList");

		/*** Then ***/
		assertEquals("noticeUpdateTiles", viewName);
		assertEquals("아이린.gif", attachmentList.get(0).getFile_name());
	}
	
	/**
	 * 
	 * Method : testUpdateNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 게시글 수정 요청 테스트
	 */
	@Test
	public void testUpdateNotice() throws Exception {
		/*** Given ***/
		File profileFile = new File("/Users/pjk/Desktop/이미지/아이린.gif");
		File profileFile2 = new File("/Users/pjk/Desktop/이미지/아이린2.gif");
		FileInputStream fis = new FileInputStream(profileFile);
		FileInputStream fis2 = new FileInputStream(profileFile2);
		
		MockMultipartFile file = 
				new MockMultipartFile("uploadFile", "테스트.gif", "image/gif", fis);
		
		MockMultipartFile file2 = 
				new MockMultipartFile("uploadFile", "테스트2.gif", "image/gif", fis2);
		
		
		
		MvcResult mvcResult = 
				mockMvc.perform(fileUpload("/notice/updateNotice")
				.file(file)
				.file(file2)
				.param("notice_num", "86")
				.param("removeList", "59")
				.param("removeList", "58")
				.param("content", "test88")
				.param("title", "test88")
				.param("board_code", "5")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***When***/
		HttpSession session = mvcResult.getRequest().getSession();

		/***Then***/
		assertEquals("수정 성공", session.getAttribute("msg"));
		assertEquals("redirect:/notice/noticeView", viewName);
	}
	
	

}
