package kr.or.ddit.attachment.dao;

import java.util.List;


import kr.or.ddit.attachment.model.AttachmentVO;

public interface IAttachmentDao {
	
	/**
	 * 
	 * Method : insertAttachment
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param vo
	 * @return
	 * Method 설명 : 	첨부파일 등록
	 */
	int insertAttachment(AttachmentVO vo);
	
	/**
	 * 
	 * Method : selectAttachmentList
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param notice_num
	 * @return
	 * Method 설명 : 첨부파일 리스트 조회
	 */
	List<AttachmentVO> selectAttachmentList(String notice_num);
	
	
	/**
	 * 
	 * Method : selectAttachment
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param file_name
	 * @return
	 * Method 설명 : 첨부파일 조회
	 */
	AttachmentVO selectAttachment(String file_name);
	
	/**
	 * 
	 * Method : deleteAttachment
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param file_name
	 * @return
	 * Method 설명 : 첨부파일 삭제
	 */
	int deleteAttachment(String file_name);
	
}
