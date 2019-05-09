package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.util.model.PageVO;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	@Resource(name="noticeService")
	private INoticeService noticeService;
	
	
	/**
	 * 
	 * Method : boardManagementView
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param req
	 * @param model
	 * @return
	 * Method 설명 : 게시판 관리 페이지 이동
	 */
	@RequestMapping(path= {"/boardManagementView"}, method=RequestMethod.GET)
	public String boardManagementView(HttpServletRequest req, Model model) {
		
		List<BoardVO> boardList = boardService.getAllBoard();
		model.addAttribute("boardList", boardList);
		
		return req.getContextPath() + "boardManagementTiles";
		
	}
	
	/**
	 * 
	 * Method : insertBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param req
	 * @param boardVO
	 * @return
	 * Method 설명 : 게시판 생성 
	 */
	@RequestMapping(path= {"/insertBoard"}, method=RequestMethod.POST)
	public String insertBoard(HttpServletRequest req, BoardVO boardVO) {
		int cnt = 0;
		//게시판 생성 요청
		if(boardVO.getBoard_name() != null) {
			cnt = boardService.insertBorad(boardVO);
			req.getSession().setAttribute("msg", "게시판 생성 완료");
		}
		//실패시
		if(cnt != 1) {
			req.getSession().setAttribute("msg", "Error");
		} 
		return req.getContextPath() + "redirect:/board/boardManagementView";
	}
	
	/**
	 * 
	 * Method : updateBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param req
	 * @param boardVO
	 * @return
	 * Method 설명 : 게시판 수정
	 */
	@RequestMapping(path= {"/updateBoard"}, method=RequestMethod.POST)
	public String updateBoard(HttpServletRequest req, BoardVO boardVO) {
		int cnt = 0;
		//게시판 수정 요청
		if(boardVO.getBoard_code() != null) {
			cnt = boardService.updateBoard(boardVO);
			req.getSession().setAttribute("msg", "게시판 수정 성공");
		}
		//수정 성공시
		if(cnt != 1) {
			req.getSession().setAttribute("msg", "Error");
		} 
		return req.getContextPath() + "redirect:/board/boardManagementView";
	}
	
	/**
	 * 
	 * Method : boardSelectView
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param req
	 * @param model
	 * @param board_name
	 * @param pageVO
	 * @return
	 * Method 설명 : 선택 게시판 게시글 조회
	 */
	@RequestMapping(path= {"/boardSelect"}, method=RequestMethod.GET)
	public String boardSelectView(HttpServletRequest req, Model model, @RequestParam("board_name")String board_name, PageVO pageVO) {

		Map<String, Object> resultMap = noticeService.selectNoticeList(pageVO);
		
		model.addAllAttributes(resultMap);
		model.addAttribute("board_name", board_name);
		model.addAttribute("board_code", pageVO.getBoard_code());
		model.addAttribute("pageSize", pageVO.getPageSize());
		model.addAttribute("page", pageVO.getPage());
		
		return req.getContextPath() + "noticeAllListTiles";
	}
	
	
}
