package kr.or.ddit.board.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.protocol.x.Notice;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.test.WebTestConfig;

public class BoardControllerTest extends WebTestConfig{

	@Resource(name="boardService")
	private IBoardService boardService;
	
	/**
	 * 
	 * Method : testManagementView
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 게시판 관리 페이지 이동 테스트
	 * @throws Exception 
	 */
	@Test
	public void testManagementView() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/board/boardManagementView")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<BoardVO> boardList = (List<BoardVO>) mav.getModelMap().get("boardList");
		
		/***Then***/
		assertEquals("boardManagementTiles", viewName);
		assertNotNull(boardList);
		assertEquals("테스트코드 게시판2", boardList.get(3).getBoard_name());
	}
	
	/**
	 * 
	 * Method : testInsertBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 게시판 생성 테스트
	 * @throws Exception 
	 */
	@Test
	public void testInsertBoard() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/board/insertBoard")
				.param("board_name", "테스트 게시판999")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		HttpSession session = mvcResult.getRequest().getSession();
		
		/***Then***/
		assertEquals("redirect:/board/boardManagementView", viewName);
		assertEquals("게시판 생성 완료", session.getAttribute("msg"));
	}
	
	/**
	 * 
	 * Method : testUpdateBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 게시판 수정 테스트
	 * @throws Exception 
	 */
	@Test
	public void testUpdateBoard() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/board/updateBoard")
				.param("board_code", "37")
				.param("board_name", "테스트 999")
				.param("act_state", "n")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		HttpSession session = mvcResult.getRequest().getSession();
		
		/***Then***/
		assertEquals("redirect:/board/boardManagementView", viewName);
		assertEquals("게시판 수정 성공", session.getAttribute("msg"));
	}
	
	/**
	 * 
	 * Method : testSelectView
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 선택 게시판 게시글 조회
	 * @throws Exception 
	 */
	@Test
	public void testBoardSelectView() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/board/boardSelect")
				.param("board_name", "테스트코드 게시판2")
				.param("board_code", "5")
				.param("page", "1")
				.param("pageSize", "10")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<NoticeVO> noticeList = (List<NoticeVO>) mav.getModelMap().get("noticeList");
		
		/***Then***/
		assertEquals("noticeAllListTiles", viewName);
		assertEquals("joker", noticeList.get(0).getUserId());
	}

}
