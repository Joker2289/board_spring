package kr.or.ddit.comments.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.comments.dao.CommentsDaoImpl;
import kr.or.ddit.comments.model.CommentsVO;

@Service("commentsService")
public class CommentsServiceImpl implements ICommentsService{

	@Resource(name="commentsDao")
	private CommentsDaoImpl commentsDao;
	
	/**
	 * 
	 * Method : insertComments
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 댓글 작성
	 */
	@Override
	public int insertComments(CommentsVO vo) {
		int insertCnt = commentsDao.insertComments(vo);
		return insertCnt;
	}
	
	/**
	 * 
	 * Method : selectCommentsList
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param notice_num
	 * @return
	 * Method 설명 : 댓글 리스트 조회
	 */
	@Override
	public List<CommentsVO> selectCommentsList(String notice_num) {
		List<CommentsVO> commentsList = commentsDao.selectCommentsList(notice_num);
		return commentsList;
	}

	/**
	 * 
	 * Method : updateCommnets
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 댓글 수정
	 */
	@Override
	public int updateCommnets(CommentsVO vo) {
		int updateCnt = commentsDao.updateComments(vo);
		return updateCnt;
	}

}
