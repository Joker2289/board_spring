package kr.or.ddit.comments.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.comments.model.CommentsVO;

@Repository("commentsDao")
public class CommentsDaoImpl implements ICommentsDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 
	 * Method : insertComments
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param vo
	 * @return
	 * Method 설명 : 댓글 등록
	 */
	@Override
	public int insertComments(CommentsVO vo) {
		int insertCnt = sqlSessionTemplate.insert("comments.insertComments", vo);
		return insertCnt;
	}
	
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
	@Override
	public List<CommentsVO> selectCommentsList(String notice_num) {
		List<CommentsVO> commentsList = sqlSessionTemplate.selectList("comments.selectCommentsList", notice_num);
		return commentsList;
	}
	
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
	@Override
	public int updateComments(CommentsVO vo) {
		
		int updateCnt = sqlSessionTemplate.update("comments.updateComments", vo);
		
		return updateCnt;
	}
	
	
}
