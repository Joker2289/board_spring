package kr.or.ddit.attachment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.attachment.model.AttachmentVO;
import kr.or.ddit.attachment.service.IAttachmentService;

@RequestMapping("/attachment")
@Controller
public class fileDownload {
	
	@Resource(name="attachmentService")
	private IAttachmentService attachmentService;
	
	
	/**
	 * 
	 * Method : fileDownload
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param resp
	 * @param file_num
	 * @throws IOException
	 * Method 설명 : 첨부파일 다운로드
	 */
	@RequestMapping(path="/fileDownload", method=RequestMethod.GET)
	public void fileDownload(HttpServletResponse resp, @RequestParam("file_num")String file_num) throws IOException {
		
		//첨부파일 번호로 조회(파일네임으로 조회하는 방법은 호구짓 중복된 파일이 있을 가능성 큼)
		AttachmentVO attachmentVO = attachmentService.selectAttachment(file_num);
		
		//파일로 다운로드
		resp.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(attachmentVO.getFile_name(), "utf-8")); 
		
		//파일로 다운로드 and 이미지 출력도됨
		resp.setContentType("application/octet-stream");
		
		//OS나 브라우저에 따라 화면이 이상하게 출력 될수 있으므로 
		resp.setContentType("image");

		FileInputStream fis = new FileInputStream(new File(attachmentVO.getFile_path()));
		
		ServletOutputStream sos = resp.getOutputStream();
		
		byte[] buff = new byte[512];
		int len = 0; 
		while((len = fis.read(buff)) > -1) {
			sos.write(buff);
		}
		sos.close();
		fis.close();
	}
}	
