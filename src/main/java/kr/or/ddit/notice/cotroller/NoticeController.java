package kr.or.ddit.notice.cotroller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import kr.or.ddit.attachment.model.AttachmentVO;
import kr.or.ddit.attachment.service.IAttachmentService;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.comments.model.CommentsVO;
import kr.or.ddit.comments.service.ICommentsService;
import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

@RequestMapping("/notice")
@Controller
public class NoticeController {
	
	private Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Resource(name="noticeService")
	private INoticeService noticeService;
	
	@Resource(name="attachmentService")
	private IAttachmentService attachmentService;
	
	@Resource(name="commentsService")
	private ICommentsService commentsService;
	
	//상세조회 이동
	@RequestMapping(path= {"/noticeView"}, method=RequestMethod.GET)
	public String noticeView(HttpServletRequest req, Model model, BoardVO boardVO, @RequestParam("notice_num")String notice_num) {
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("userVO_log");
		NoticeVO noticeVO = noticeService.selectNotice(notice_num);
		
		//첨부파일 조회
		List<AttachmentVO> attachmentList = attachmentService.selectAttachmentList(notice_num);
		
		//댓글 리스트 조회
		List<CommentsVO> commentsList = commentsService.selectCommentsList(notice_num);
		
		model.addAttribute("loginId", userVO.getUserId());
		model.addAttribute("noticeVO", noticeVO);
		model.addAttribute("board_name", boardVO.getBoard_name());
		model.addAttribute("board_code", boardVO.getBoard_code());
		model.addAttribute("commentsList", commentsList);
		model.addAttribute("attachmentList", attachmentList);

		return "noticeViewTiles";
	}
	
	private String tmp_parent_num; 	//부모글
	private List<AttachmentVO> attachmentList;
	
	//글작성 페이지 이동
	@RequestMapping(path= {"noticeForm"}, method=RequestMethod.GET)
	public String noticeForm(HttpServletRequest req, Model model, BoardVO boardVO, 
			@RequestParam(name="reply", defaultValue="n") String reply, NoticeVO noticeVO) {
		
		if(reply.equals("y")) {
			tmp_parent_num = noticeVO.getParent_num();
			model.addAttribute("reply", "ㄴ[RE]: ");
		}
		
		model.addAttribute("board_name", boardVO.getBoard_name());
		model.addAttribute("board_code", boardVO.getBoard_code());
		
		return "noticeFormTiles";
	}
	
	//글작성 요청
	@RequestMapping(path= {"insertNotice"}, method=RequestMethod.POST)
	public String insertNotice(HttpServletRequest req, Model model, BoardVO boardVO, NoticeVO noticeVO,
								MultipartRequest multiparts, @RequestParam(name="reply") String reply) throws IOException, ServletException {
		
		List<MultipartFile> parts = multiparts.getFiles("uploadFile");
		
		
		attachmentList = new ArrayList<AttachmentVO>();
		
		String file_name = "";
		String file_path = "";
		
		for(MultipartFile part : parts) {
			logger.debug("contentDisposition: {}", part.getOriginalFilename());
			
			//file크기가 0보다 클때만 저장
			if(part.getSize() > 0) {
				file_name = part.getOriginalFilename();
				file_path = "/Users/pjk/Documents/board_attachment/" + UUID.randomUUID().toString();
				
				part.transferTo(new File(file_path));
				
				AttachmentVO attachmentVO = new AttachmentVO();
				attachmentVO.setFile_name(file_name);
				attachmentVO.setFile_path(file_path);
				
				attachmentList.add(attachmentVO);
				
			}
		}
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("userVO_log");
		String userId = userVO.getUserId();
		
		noticeVO.setUserId(userId);
		
		//답글 요청시 부모글 저장
		if(reply.equals("y")) {
			noticeVO.setParent_num(tmp_parent_num);
		}
		
		int insertCnt = noticeService.insertNotice(noticeVO, attachmentList);
		
		//등록 성공
		if(insertCnt == 1) {
			req.getSession().setAttribute("msg", "등록 성공");
			model.addAttribute("board_name", boardVO.getBoard_name());
			model.addAttribute("board_code", boardVO.getBoard_code());
			model.addAttribute("notice_num", noticeVO.getNotice_num());
			
			return "redirect:" + req.getContextPath() + "/notice/noticeView";
		}
		
		//등록 실패
		else {
			req.getSession().setAttribute("msg", "등록 실패");
			return "/notice/noticeForm";
		}
	}
	
	//수정버튼 클릭
	@RequestMapping(path= {"noticeUpdate"}, method=RequestMethod.GET)
	public String noticeUpdate(HttpServletRequest req, Model model, BoardVO boardVO, @RequestParam("notice_num")String notice_num) {
		
		NoticeVO noticeVO = noticeService.selectNotice(notice_num);
		
		//첨부파일 조회
		List<AttachmentVO> attachmentList = attachmentService.selectAttachmentList(notice_num);
		
		model.addAttribute("noticeVO", noticeVO);
		model.addAttribute("board_name", boardVO.getBoard_name());
		model.addAttribute("board_code", boardVO.getBoard_code());
		model.addAttribute("attachmentList", attachmentList);
		
		return "noticeUpdateTiles";
	}
	
	//수정요청
	@RequestMapping(path= {"updateNotice"}, method=RequestMethod.POST)
	public String updateNotice(HttpServletRequest req, Model model, BoardVO boardVO, NoticeVO noticeVO,
					MultipartRequest multiparts) throws IllegalStateException, IOException {
		
		//첨부파일 삭제 리스트 가져오기
		String[] removeList = req.getParameterValues("removeList");
		
		List<MultipartFile> parts = multiparts.getFiles("uploadFile");
		
		
		attachmentList = new ArrayList<AttachmentVO>();
		
		String file_name = "";
		String file_path = "";
		
		for(MultipartFile part : parts) {
			logger.debug("contentDisposition: {}", part.getOriginalFilename());
			
			//file크기가 0보다 클때만 저장
			if(part.getSize() > 0) {
				file_name = part.getOriginalFilename();
				file_path = "/Users/pjk/Documents/board_attachment/" + UUID.randomUUID().toString();
				
				part.transferTo(new File(file_path));
				
				AttachmentVO attachmentVO = new AttachmentVO();
				attachmentVO.setFile_name(file_name);
				attachmentVO.setFile_path(file_path);
				
				attachmentList.add(attachmentVO);
				
			}
		}
		
		
		int updateCnt = noticeService.updateNotice(noticeVO, removeList, attachmentList);
		
		//등록 성공
		if(updateCnt == 1) {
			req.getSession().setAttribute("msg", "수정 성공");
			model.addAttribute("board_name", boardVO.getBoard_name());
			model.addAttribute("board_code", boardVO.getBoard_code());
			model.addAttribute("notice_num", noticeVO.getNotice_num());
			
			return "redirect:" + req.getContextPath() + "/notice/noticeView";
		}
		
		return "noticeUpdateTiles";
	}
	
	//게시글 삭제
	@RequestMapping(path= {"deleteNotice"}, method=RequestMethod.GET)
	public String deleteNotice(HttpServletRequest req, Model model, BoardVO boardVO, @RequestParam("notice_num")String notice_num) {
		
		
		int deleteCnt = noticeService.deleteNotice(notice_num);
		model.addAttribute("board_name", boardVO.getBoard_name());
		model.addAttribute("board_code", boardVO.getBoard_code());
		model.addAttribute("notice_num", notice_num);
		
		//삭제 성공
		if(deleteCnt == 1) {
			req.getSession().setAttribute("msg", "삭제 성공");
			return "redirect:" + req.getContextPath() + "/board/boardSelect";
		}
		
		//등록 실패
		else {
			req.getSession().setAttribute("msg", "삭제 실패");
			return "/notice/noticeView";
		}
	}
	
	
}
