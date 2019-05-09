package kr.or.ddit.comments.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comments.model.CommentsVO;

public interface ICommentsDao {
	
	/**
	 * 
	 * Method : insertComments
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param vo
	 * @return
	 * Method 설명 : 댓글 작성
	 */
	int insertComments(CommentsVO vo);
	
	/**
	 * 
	 * Method : selectCommentsList
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param vo
	 * @return
	 * Method 설명 : 댓글 리스트 조회
	 */
	List<CommentsVO> selectCommentsList(String notice_num);
	
	/**
	 * 
	 * Method : updateComments
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param vo
	 * @return
	 * Method 설명 : 댓글 수정
	 */
	int updateComments(CommentsVO vo);
}
