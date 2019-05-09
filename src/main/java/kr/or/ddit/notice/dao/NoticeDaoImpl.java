package kr.or.ddit.notice.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.util.model.PageVO;

@Repository("noticeDao")
public class NoticeDaoImpl implements INoticeDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 
	 * Method : selectNoticeList
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_code
	 * @return
	 * Method 설명 : 게시글 리스트 조회
	 */
	@Override
	public List<NoticeVO> selectNoticeList(PageVO vo) {
		List<NoticeVO> noticeList = sqlSessionTemplate.selectList("notice.selectNoticeList", vo);
		return noticeList;
	}
	
	/**
	 * 
	 * Method : insertNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param vo
	 * @return
	 * Method 설명 : 게시글 등록
	 */
	@Override
	public int insertNotice(NoticeVO vo) {
		
		int insertCnt = sqlSessionTemplate.insert("notice.insertNotice", vo);
		
		return insertCnt;
	}
	
	/**
	 * 
	 * Method : selectNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param notice_num
	 * @return
	 * Method 설명 : 게시글 조회
	 */
	@Override
	public NoticeVO selectNotice(String notice_num) {
		
		NoticeVO noticeVO = sqlSessionTemplate.selectOne("notice.selectNotice", notice_num);
		
		return noticeVO;
	}
	
	/**
	 * 
	 * Method : deleteNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param notice_num
	 * @return
	 * Method 설명 : 게시글 삭제
	 */
	@Override
	public int deleteNotice(String notice_num) {
		
		int deleteCnt = sqlSessionTemplate.delete("notice.deleteNotice", notice_num);
		
		return deleteCnt;
	}
	
	/**
	 * 
	 * Method : updateNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param vo
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	@Override
	public int updateNotice(NoticeVO vo) {
		
		int updateCnt = sqlSessionTemplate.update("notice.updateNotice", vo);
		
		return updateCnt;
	}
	
	/**
	 * 
	 * Method : getNoticeCnt
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_code
	 * @return
	 * Method 설명 : 게시글 수 조회
	 */
	@Override
	public int getNoticeCnt(PageVO vo) {
		
		int noticeCnt = sqlSessionTemplate.selectOne("notice.getNoticeCnt", vo);
		
		return noticeCnt;
	}
	
	
}
