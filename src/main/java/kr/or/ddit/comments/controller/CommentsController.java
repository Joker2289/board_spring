package kr.or.ddit.comments.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.comments.model.CommentsVO;
import kr.or.ddit.comments.service.ICommentsService;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/comments")
@Controller
public class CommentsController {
	
	@Resource(name="commentsService")
	private ICommentsService commentsService;
	
	//댓글 작성 요청
	@RequestMapping(path= {"/addComment"}, method=RequestMethod.POST)
	public String addComment(HttpServletRequest req, Model model, CommentsVO commentsVO, BoardVO boardVO) {
		
		UserVO userVO_log = (UserVO) req.getSession().getAttribute("userVO_log");
		commentsVO.setUserId(userVO_log.getUserId());
		commentsVO.setDel_state("n");
		
		int insertComments = commentsService.insertComments(commentsVO);
		
		model.addAttribute("board_name", boardVO.getBoard_name());
		model.addAttribute("board_code", boardVO.getBoard_code());
		model.addAttribute("notice_num", commentsVO.getNotice_num());
		
		return req.getContextPath() + "redirect:/notice/noticeView";
	}
	
	//댓글 수정 요청
	@RequestMapping(path= {"/updComment"}, method=RequestMethod.GET)
	public String updComment(HttpServletRequest req, Model model, CommentsVO commentsVO, BoardVO boardVO) {
		
		commentsVO.setDel_state("y");
		
		int updateCnt = commentsService.updateCommnets(commentsVO);
		
		model.addAttribute("board_name", boardVO.getBoard_name());
		model.addAttribute("board_code", boardVO.getBoard_code());
		model.addAttribute("notice_num", commentsVO.getNotice_num());
		
		return req.getContextPath() + "redirect:/notice/noticeView";
	}
	
	
}
