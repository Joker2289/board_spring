package kr.or.ddit.user.conroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.PageVO;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Resource(name="userService")
	private IUserService userService;
	
	
	/**
	 * 
	 * Method : userAllList
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 전체 리스트 조회
	 */
	@RequestMapping("/userAllList")
	public String userAllList(Model model, HttpServletRequest req) {
		
		List<UserVO> userList = userService.getAllUser();
		
		model.addAttribute("userList", userList);
		
		//return req.getContextPath() + "user/userAllList";
		return req.getContextPath() + "userAllListTiles";
	}
	
	/**
	 * 
	 * Method : userPagingList
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param page
	 * @param pageSize
	 * @return
	 * Method 설명 : 사용자 페이징 리스트 조회
	 */
	@RequestMapping("/userPagingList")
	public String userPagingList(PageVO pageVO, Model model, HttpServletRequest req) {
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVO);
		
		//addAllAttrivyte : Map 의 모든 키와 값을 넣어준다
		model.addAllAttributes(resultMap); // resultMap : userList 와 userCnt 담겨있다
		model.addAttribute("pageSize", pageVO.getPageSize());
		model.addAttribute("page", pageVO.getPage());
		
		//return req.getContextPath() + "user/userPagingList";
		return req.getContextPath() + "userPagingListTiles";
	}
	
	/**
	 * 
	 * Method : userPagingListAjaxView
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param page
	 * @param pageSize
	 * @return
	 * Method 설명 : 사용자 페이징 리스트 뷰
	 */
	@RequestMapping("/userPagingListAjaxView")
	public String userPagingListAjaxView(HttpServletRequest req) {
		
		return req.getContextPath() + "userPagingListAjaxTiles";
	}
	
	/**
	 * 
	 * Method : userPagingListAjax
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param pageVO
	 * @param model
	 * @param req
	 * @return
	 * Method 설명 : 사용자 페이지 리스트 ajax 처리
	 */
	@RequestMapping("/userPagingListAjax")
	public String userPagingListAjax(PageVO pageVO, Model model, HttpServletRequest req) {
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVO);
		
		//addAllAttrivyte : Map 의 모든 키와 값을 넣어준다
		model.addAllAttributes(resultMap); // resultMap : userList 와 userCnt 담겨있다
		model.addAttribute("pageSize", pageVO.getPageSize());
		model.addAttribute("page", pageVO.getPage());
		
		//userList, userCnt, pageSize, page
		// { userList : [ {userId : 'brown', userNm : '브라운'} .... {userId : 'sally', userNm : '샐리'}
		// userCont = "110",
		// pageSize = "10",;
		// page = "2";
		
		
		//return req.getContextPath() + "user/userPagingList";
		return req.getContextPath() + "jsonView";
	}
	
	@RequestMapping("/userPagingListAjaxHtml")
	public String userPagingListAjaxHtml(PageVO pageVO, Model model, HttpServletRequest req) {
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVO);
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize", pageVO.getPageSize());
		model.addAttribute("page", pageVO.getPage());
		
		return req.getContextPath() + "user/userPagingListAjaxHtml";
	}
	
	/**
	 * 
	 * Method : user
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param userId
	 * @param model
	 * @return
	 * Method 설명 : 사용자 상세조회
	 */
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public String user(@RequestParam("userId")String userId, Model model, HttpServletRequest req) {
		
		UserVO userVO = userService.searchUser(userId);
		model.addAttribute("userVO", userVO);
		
		return req.getContextPath() + "user/user";
	}
	
	/**
	 * 
	 * Method : profileImg
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param resp
	 * @param req
	 * @param userId
	 * @throws IOException
	 * Method 설명 :
	 */
	@RequestMapping("/profileImg")
	public void profileImg(HttpServletResponse resp, 
							HttpServletRequest req,
							@RequestParam("userId") String userId) throws IOException {
		//파일로 다운로드
		resp.setHeader("Content-Disposition", "attachment; filename=profile.png"); 
		
		//파일로 다운로드 and 이미지 출력도됨
		resp.setContentType("application/octet-stream");
		
		//OS나 브라우저에 따라 화면이 이상하게 출력 될수 있으므로 
		resp.setContentType("image");
		
		
		//1.사용자 아이디 파라미터 확인
		//String userId = req.getParameter("userId");
		
		//2.해당 사용자 아이디로 사용자 정보 조회(realFileName)
		UserVO userVO = userService.searchUser(userId);
		
		//3-1.realFileName이 존재할 경우
		//3-1-1.해당 경로의 파일을 FileInputStream으로 읽는다
		FileInputStream fis;
		if(userVO != null && userVO.getRealFileName() != null) {
			fis = new FileInputStream(new File(userVO.getRealFileName()));
		}
		
		//3-2.realFileName이 존재하지 않을 경우
		//3-2-1. /upload/noimg.png (application.getRealPath())
		else {
			//application
			//ServletContext application = getServletContext();
			
			// servlet 기반으로 만든게 아니라 getServletContext() = appliction의 정보를 가져올수 없음
			
			ServletContext application = req.getServletContext();
			
			//String noimgPath = application.getRealPath("/upload/noimp2.png");
			fis = new FileInputStream(new File("/Users/pjk/Documents/picture/noimg2.png"));
		}
		
		//4.FileInputStream을 response 객체의 outputStrea 객체에 write
		ServletOutputStream sos = resp.getOutputStream();
		
		byte[] buff = new byte[512];
		int len = 0; 
		while((len = fis.read(buff)) > -1) {
			sos.write(buff);
		}
		sos.close();
		fis.close();
	}
	
	/**
	 * 
	 * Method : userForm
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 등록폼 (화면 요청)
	 */
	@RequestMapping(path="/userForm", method=RequestMethod.GET)
	public String userForm(HttpServletRequest req) {
		return req.getContextPath() + "user/userForm";
	}
	
	/**
	 * 
	 * Method : userForm
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param userVO
	 * @param profile
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 * Method 설명 : 사용자 등록폼 (사용자 등록 요청)
	 */
	@RequestMapping(path="/userForm", method=RequestMethod.POST)
	public String userForm(UserVO userVO, @RequestPart("profile")MultipartFile profile, 
						HttpSession session, Model model, HttpServletRequest req) throws Exception {
		
		UserVO duplicateUserVO = userService.searchUser(userVO.getUserId());
		
		//중복체크 통과(신규등록)
		if(duplicateUserVO == null) {
			
			//사용자 사진을 업로드 한경우
			if(profile.getSize() > 0) {
				String fileName = profile.getOriginalFilename();
				String realFileName =  "/Users/pjk/Documents/picture/" + UUID.randomUUID().toString();
				
				profile.transferTo(new File(realFileName));
				
				userVO.setFileName(fileName);
				userVO.setRealFileName(realFileName);
			}
			
			//PW 암호화
			userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
			
			int insertUser = 0;
			
			try {
				insertUser = userService.insertUser(userVO);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			//입력 성공
			if(insertUser == 1) {
				session.setAttribute("msg", "정상 등록 되었습니다");
				return "redirect:" + req.getContextPath() + "/user/userPagingList";
			} 
			
			//입력 실패
			else {
				session.setAttribute("msg", "등록 실패 했습니다");
				return req.getContextPath() + "user/userForm";
			}
		}
		
		//중복체크 통과 실패
		else {
			model.addAttribute("msg", "중복된 아이디 입니다");
			return req.getContextPath() + "user/userForm"; 
		}
	}
	
	//파일 이름, 경로 저장할 임시 변수
	String tmp_fileName = null;
	String tmp_realFileName = null;
	
	@RequestMapping(path="/userModifyForm", method=RequestMethod.GET)
	public String userModifyForm(@RequestParam("userId")String userId, Model model, HttpServletRequest req) {
		
		UserVO userVO = userService.searchUser(userId);
		model.addAttribute("userVO", userVO);
		
		tmp_fileName = userVO.getFileName();
		tmp_realFileName = userVO.getRealFileName();
		
		return req.getContextPath() + "user/userModify";
	}
	
	@RequestMapping(path="/userModifyForm", method=RequestMethod.POST)
	public String userModifyForm(UserVO userVO, Model model, RedirectAttributes ra,
			@RequestPart("profile")MultipartFile profile, HttpSession session, HttpServletRequest req) throws Exception {
		
		//사용자 사진을 업로드 한경우
		if(profile.getSize() > 0) {
			String fileName = profile.getOriginalFilename();
			String realFileName =  "/Users/pjk/Documents/picture/" + UUID.randomUUID().toString();
			
			profile.transferTo(new File(realFileName));
			
			userVO.setFileName(fileName);
			userVO.setRealFileName(realFileName);
		}
		
		//사용자 사진 업로드 안 한 경우
		else {
			
			userVO.setFileName(tmp_fileName);
			userVO.setRealFileName(tmp_realFileName);
			
		}
		
		//비밀번호 수정 요청 여부 
		//사용자가 값을 입력하지 않은 경우 => 기존 비밀번호 유지
		if(userVO.getPass().equals("")) {
			UserVO userVOForPass = userService.searchUser(userVO.getUserId());
			userVO.setPass(userVOForPass.getPass());
		}
		//사용자가 비밀번호를 신규 등록한 경우
		else {
			//PW 암호화
			userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
		}

		int updateUser = 0;
		
		try {
			updateUser = userService.updateUser(userVO);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//수정 성공
		if(updateUser == 1) {
			//redirect 파라미터를 보내는 방법
			//1.url로 작성
			//  - return "redirect:/user/user?userId=" + userVO.getUserId();
			//2.model객체의 속성 : 저장된 속성을 자동으로 파라미터 변혼
			//  - model.addAttribute("userId", userVO.getUserId());
			//3.RedirectAttributes(ra) 객체를 이용
			//  - ra.addAttribute("userId", userVO.getUserId());
			
			ra.addFlashAttribute("msg", "수정 성공");
			ra.addAttribute("userId", userVO.getUserId());
			
			//session.setAttribute("msg", "수정 성공");
			//model.addAttribute("userId", userVO.getUserId()); 
			return "redirect:" + req.getContextPath() + "/user/user";
		}
		//수정 실패
		else {
			session.setAttribute("msg", "수정 실패");
			return req.getContextPath() + "user/userModify";
		}
	}
	
	
	
}
