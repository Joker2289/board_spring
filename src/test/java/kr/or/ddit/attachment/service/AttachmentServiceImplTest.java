package kr.or.ddit.attachment.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.attachment.model.AttachmentVO;
import kr.or.ddit.test.LogicTestConfig;

public class AttachmentServiceImplTest extends LogicTestConfig{
	
	@Resource(name="attachmentService")
	private IAttachmentService service;
	
	@Before
	public void setup() {
		
	}
	
	/**
	 * 
	 * Method : testInsertAttachment
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 첨부파일 등록
	 */
	@Test
	public void testInsertAttachment() {
		
		/***Given***/
		AttachmentVO vo = new AttachmentVO();
		vo.setNotice_num("85");
		vo.setFile_name("테스트");
		vo.setFile_path("테스트");
		
		/***When***/
		int insertCnt = service.insertAttachment(vo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	 * 
	 * Method : testSelectAttachmentList
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 첨부파일 리스트 조회
	 */
	@Test
	public void testSelectAttachmentList() {
		/***Given***/
		
		/***When***/
		List<AttachmentVO> attachmentList = service.selectAttachmentList("85");
		System.out.println(attachmentList.get(0).getFile_path());
		
		/***Then***/
		assertEquals("테스트", attachmentList.get(0).getFile_path());
		
		
	}

}
