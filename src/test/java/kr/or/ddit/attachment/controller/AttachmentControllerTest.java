package kr.or.ddit.attachment.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import kr.or.ddit.attachment.service.IAttachmentService;
import kr.or.ddit.test.WebTestConfig;

public class AttachmentControllerTest extends WebTestConfig{

	@Resource(name="attachmentService")
	private IAttachmentService attachmentService;
	
	/**
	 * 
	 * Method : testFileDownload
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 첨부파일 다운로드 테스트
	 * @throws Exception 
	 */
	@Test
	public void testFileDownload() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/attachment/fileDownload")
				.param("file_num", "60")).andReturn();
		/***Then***/
		
	}

}
