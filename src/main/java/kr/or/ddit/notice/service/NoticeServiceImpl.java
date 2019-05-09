package kr.or.ddit.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.attachment.dao.IAttachmentDao;
import kr.or.ddit.attachment.model.AttachmentVO;
import kr.or.ddit.notice.dao.INoticeDao;
import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.util.model.PageVO;

@Service("noticeService")
public class NoticeServiceImpl implements INoticeService {
	
	@Resource(name="noticeDao")
	private INoticeDao noticeDao;
	
	@Resource(name="attachmentDao")
	private IAttachmentDao attachmentDao;
	
	
	
	/**
	 * 
	 * Method : selectNoticeList
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param board_code
	 * @return
	 * Method 설명 : 게시글 리스트 조회 (페이징 처리)
	 */
	@Override
	public Map<String, Object> selectNoticeList(PageVO vo) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("noticeList", noticeDao.selectNoticeList(vo));
		resultMap.put("noticeCnt", noticeDao.getNoticeCnt(vo));
		
		return resultMap;
	}
	
	/**
	 * 
	 * Method : insertNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 게시글 등록
	 */
	@Override
	public int insertNotice(NoticeVO vo, List<AttachmentVO> attachmentList) {
		
		int insertCnt = noticeDao.insertNotice(vo);
		
		//첨부파일이 있을때 notice_num을 insertNotice를 실행 후 현재 시퀀스로 가져옴 (같은 세션에 있을때 가능하다)
		if(attachmentList != null && insertCnt != 0) {
			
			for(AttachmentVO attachmentVO : attachmentList) {
				attachmentVO.setNotice_num(vo.getNotice_num());
				int insertAttachment = attachmentDao.insertAttachment(attachmentVO);
			}
			
		}
		
		
		return insertCnt;
	}
	
	/**
	 * 
	 * Method : selectNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param notice_num
	 * @return
	 * Method 설명 : 게시글 조회
	 */
	@Override
	public NoticeVO selectNotice(String notice_num) {
		NoticeVO noticeVO = noticeDao.selectNotice(notice_num);
		return noticeVO;
	}
	
	/**
	 * 
	 * Method : deleteNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param notice_num
	 * @return
	 * Method 설명 : 게시글 삭제
	 */
	@Override
	public int deleteNotice(String notice_num) {
		int deleteCnt = noticeDao.deleteNotice(notice_num);
		return deleteCnt;
	}
	
	/**
	 * 
	 * Method : updateNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	@Override
	public int updateNotice(NoticeVO vo, String[] removeList, List<AttachmentVO> attachmentList) {
		
		int updateCnt = noticeDao.updateNotice(vo);
		
		//첨부파일 삭제
		if(removeList != null && updateCnt != 0) {
			for(String fileNum : removeList) {
				int deleteCnt = attachmentDao.deleteAttachment(fileNum);
			}
		}
		
		//첨부파일 등록
		if(attachmentList != null && updateCnt != 0) {	
			
			for(AttachmentVO attachmentVO : attachmentList) {
				attachmentVO.setNotice_num(vo.getNotice_num());
				int insertAttachment = attachmentDao.insertAttachment(attachmentVO);
			}
		}
		
		return updateCnt;
	}

}
