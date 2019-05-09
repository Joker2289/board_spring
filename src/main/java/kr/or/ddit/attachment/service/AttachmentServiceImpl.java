package kr.or.ddit.attachment.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.attachment.dao.AttachmentDaoImpl;
import kr.or.ddit.attachment.dao.IAttachmentDao;
import kr.or.ddit.attachment.model.AttachmentVO;

@Service("attachmentService")
public class AttachmentServiceImpl implements IAttachmentService{
	
	@Resource(name="attachmentDao")
	private IAttachmentDao attachmentDao;
	
	public AttachmentServiceImpl() {
		attachmentDao = new AttachmentDaoImpl();
	}
	
	/**
	 * 
	 * Method : insertAttachment
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 	첨부파일 등록
	 */
	@Override
	public int insertAttachment(AttachmentVO vo) {
		int insertCnt = attachmentDao.insertAttachment(vo);
		return insertCnt;
	}
	
	/**
	 * 
	 * Method : selectAttachmentList
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param notice_num
	 * @return
	 * Method 설명 : 첨부파일 리스트 조회
	 */
	@Override
	public List<AttachmentVO> selectAttachmentList(String notice_num) {
		List<AttachmentVO> attachmentList = attachmentDao.selectAttachmentList(notice_num);
		return attachmentList;
	}

	/**
	 * 
	 * Method : selectAttachment
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param file_name
	 * @return
	 * Method 설명 : 첨부파일 조회
	 */
	@Override
	public AttachmentVO selectAttachment(String file_num) {
		AttachmentVO attachmentVO = attachmentDao.selectAttachment(file_num);
		return attachmentVO;
	}
	
	/**
	 * 
	 * Method : deleteAttachment
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param file_name
	 * @return
	 * Method 설명 : 첨부파일 삭제 
	 */
	@Override
	public int deleteAttachment(String file_num) {
		int deleteCnt = attachmentDao.deleteAttachment(file_num);
		return deleteCnt;
	}
	
	

}
