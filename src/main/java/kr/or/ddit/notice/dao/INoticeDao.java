package kr.or.ddit.notice.dao;

import java.util.List;


import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.util.model.PageVO;

public interface INoticeDao {
	
	/**
	 * 
	 * Method : selectNoticeList
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_code
	 * @return
	 * Method 설명 : 게시글 리스트 조회(페이징 처리)
	 */
	List<NoticeVO> selectNoticeList(PageVO vo);
	
	/**
	 * 
	 * Method : getNoticeCnt
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @return
	 * Method 설명 : 게시글 수 조회
	 */
	int getNoticeCnt(PageVO vo);
	
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
	int insertNotice(NoticeVO vo);
	
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
	NoticeVO selectNotice(String notice_num);
	
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
	int deleteNotice(String notice_num);
	
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
	int updateNotice(NoticeVO vo);
	
	
}
