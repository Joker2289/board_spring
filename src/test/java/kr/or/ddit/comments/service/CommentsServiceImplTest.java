package kr.or.ddit.comments.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.comments.model.CommentsVO;
import kr.or.ddit.test.LogicTestConfig;

public class CommentsServiceImplTest extends LogicTestConfig{

	@Resource(name="commentsService")
	private ICommentsService service;
	
	@Before
	public void setup() {
		
	}
	
	/**
	 * 
	 * Method : testInsertComments
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 댓글 등록 테스트
	 */
	@Test
	public void testInsertComments() {
		/***Given***/
		CommentsVO vo = new CommentsVO();
		vo.setNotice_num("85");
		vo.setComment_content("댓글 테스트코드");
		vo.setUserId("joker");
		vo.setDel_state("n");
		/***When***/
		int insertCnt = service.insertComments(vo);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	 * 
	 * Method : testSelectCommentsList
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 댓글 리스트 조회 테스트
	 */
	@Test
	public void testSelectCommentsList() {
		/***Given***/
		
		/***When***/
		List<CommentsVO> commentsList = service.selectCommentsList("85");
		/***Then***/
		assertNotNull(commentsList);
	}
	
	/**
	 * 
	 * Method : testUpdateCommnets
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 댓글 삭제여부 상태 변경
	 */
	@Test
	public void testUpdateCommnets() {
		/***Given***/
		CommentsVO vo = new CommentsVO();
		vo.setDel_state("y");
		vo.setComment_num("49");
		/***When***/
		int updateCnt = service.updateCommnets(vo);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	

}
