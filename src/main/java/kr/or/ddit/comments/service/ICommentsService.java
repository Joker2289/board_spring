package kr.or.ddit.comments.service;


import java.util.List;

import kr.or.ddit.comments.model.CommentsVO;

public interface ICommentsService {
	
	/**
	 * 
	 * Method : insertComments
	 * 작성자 : pjk
	 * 변경이력 :
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
	 * @param notice_num
	 * @return
	 * Method 설명 : 댓글 리스트 조회
	 */
	List<CommentsVO> selectCommentsList(String notice_num);
	
	/**
	 * 
	 * Method : updateCommnets
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 댓글 수정
	 */
	int updateCommnets(CommentsVO vo);
}
