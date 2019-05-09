package kr.or.ddit.board.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.test.LogicTestConfig;


public class BoardServiceImplTest extends LogicTestConfig{
	
	@Resource(name="boardService")
	private IBoardService service;
	
	@Before
	public void setup() {
	}
	
	/**
	 * 
	 * Method : testInsertBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 게시판 등록 테스트
	 */
	@Test
	public void testInsertBoard() {
		/***Given***/
		BoardVO vo = new BoardVO();
		vo.setBoard_name("테스트");
		
		/***When***/
		int cnt = service.insertBorad(vo);
		/***Then***/
		assertEquals(1, cnt);
	}
	
	/**
	 * 
	 * Method : testGetAllBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 전체 게시판 조회
	 */
	@Test
	public void testGetAllBoard() {
		/***Given***/
		
		/***When***/
		List<BoardVO> boardList = service.getAllBoard();
		for(BoardVO vo : boardList) {
			System.out.println(vo.getBoard_code() + " : " + vo.getBoard_name() + " : " + vo.getAct_state());
		}
		/***Then***/
		assertNotNull(boardList);
	}
	
	/**
	 * 
	 * Method : testSearchBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 게시판 검색
	 */
	@Test
	public void testSearchBoard() {
		/***Given***/
		
		/***When***/
		BoardVO boardVO = service.searchBoard("5");
		/***Then***/
		assertNotNull(boardVO);
		assertEquals("테스트코드 게시판", boardVO.getBoard_name());
	}
	
	
	/**
	 * 
	 * Method : testUpdateBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 게시판 수정 테스트
	 */
	@Test
	public void testUpdateBoard() {
		/***Given***/
		BoardVO vo = new BoardVO();
		vo.setBoard_code("5");
		vo.setBoard_name("테스트코드 게시판2");
		vo.setAct_state("y");
		/***When***/
		int updateCnt = service.updateBoard(vo);
		/***Then***/
		assertEquals(1, updateCnt);
		
	}
	
	

}
