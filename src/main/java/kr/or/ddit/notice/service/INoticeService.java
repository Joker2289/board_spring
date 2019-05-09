package kr.or.ddit.notice.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.attachment.model.AttachmentVO;
import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.util.model.PageVO;

public interface INoticeService {
	
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
	Map<String, Object> selectNoticeList(PageVO vo);
	
	/**
	 * 
	 * Method : insertNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 게시글 등록
	 */
	int insertNotice(NoticeVO vo, List<AttachmentVO> attachmentList);
	
	/**
	 * 
	 * Method : selectNotice
	 * 작성자 : pjk
	 * 변경이력 :
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
	 * @param vo
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	int updateNotice(NoticeVO vo, String[] removeList, List<AttachmentVO> attachmentList);
}
