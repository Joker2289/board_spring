package kr.or.ddit.comments.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.comments.service.ICommentsService;
import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVO;

public class CommentsControllerTest extends WebTestConfig{
	
	@Resource(name="commentsService")
	private ICommentsService commentsService;
	
	/**
	 * 
	 * Method : testAddComment
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 댓글 작성 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void testAddComment() throws Exception {
		/*** Given ***/
		UserVO userVO = new UserVO();
		userVO.setUserId("joker");
		
		/*** When ***/
		MvcResult mvcResult = mockMvc.perform(post("/comments/addComment")
					.sessionAttr("userVO_log", userVO)
					.param("comment_content", "test_comment")
					.param("notice_num", "86")
					.param("del_state", "n")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/*** Then ***/
		assertEquals("redirect:/notice/noticeView", viewName);
	}
	
	@Test
	public void testUpdComment() throws Exception {
		/*** Given ***/
		UserVO userVO = new UserVO();
		userVO.setUserId("joker");
		
		/*** When ***/
		MvcResult mvcResult = mockMvc.perform(get("/comments/updComment")
					.sessionAttr("userVO_log", userVO)
					.param("comment_num", "50")
					.param("notice_num", "86")
					.param("del_state", "y")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/*** Then ***/
		assertEquals("redirect:/notice/noticeView", viewName);
	}
	

}
