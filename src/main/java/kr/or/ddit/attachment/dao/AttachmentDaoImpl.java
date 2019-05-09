package kr.or.ddit.attachment.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.attachment.model.AttachmentVO;

@Repository("attachmentDao")
public class AttachmentDaoImpl implements IAttachmentDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 
	 * Method : insertAttachment
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param vo
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	@Override
	public int insertAttachment(AttachmentVO vo) {
		int insertCnt = sqlSessionTemplate.insert("attachment.insertAttachment", vo);
		return insertCnt;
	}
	
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
	@Override
	public List<AttachmentVO> selectAttachmentList(String notice_num) {
		List<AttachmentVO> attachmentList = sqlSessionTemplate.selectList("attachment.selectAttachmentList", notice_num);
		return attachmentList;
	}
	
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
	@Override
	public AttachmentVO selectAttachment(String file_num) {
		AttachmentVO attachmentVO = sqlSessionTemplate.selectOne("attachment.selectAttachment", file_num);
		return attachmentVO;
	}
	
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
	@Override
	public int deleteAttachment(String file_num) {
		int deleteCnt = sqlSessionTemplate.delete("attachment.deleteAttachment", file_num);
		return deleteCnt;
	}

}
