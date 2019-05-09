package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

public class UserControllerTest extends WebTestConfig{
	
	private static final String USER_INSERT_TEST_ID = "test99";
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Before
	public void initDate() {
		userService.deleteUser(USER_INSERT_TEST_ID);
	}
	
	/**
	 * 
	 * Method : testUserAllList
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 사용자 전체 조회 테스트
	 * @throws Exception 
	 */
	@Test
	public void testUserAllList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userAllList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVO> userList = (List<UserVO>) mav.getModel().get("userList");
		
		/***Then***/
		assertEquals("userAllListTiles", viewName);
		assertNotNull(userList);
		assertTrue(userList.size() > 100);
	
	}
	
	/**
	 * 
	 * Method : testUserPagingList
	 * 작성자 : pjk
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 페이징 리스트 조회
	 */
	@Test
	public void testUserPagingList() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userPagingList?page=3")).andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		ModelMap modelMap = mav.getModelMap();
		List<UserVO> userList = (List<UserVO>) modelMap.get("userList");
		int userCnt = (Integer) modelMap.get("userCnt");
		int page = (Integer) modelMap.get("page");
		int pageSize = (Integer) modelMap.get("pageSize");
		
		
		/***Then***/
		assertEquals("userPagingListTiles", viewName);
		assertEquals(3, page);
		assertEquals(10, pageSize);
		assertNotNull(userList);
		assertTrue(userList.size() <= 10);
	}
	
	/**
	 * 
	 * Method : testUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 상세조회
	 */
	@Test
	public void testUser() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", "joker")).andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		ModelMap modelMap = mav.getModelMap();
		UserVO userVO = (UserVO) modelMap.get("userVO");
		
		/***Then***/
		assertEquals("조커", userVO.getUserNm());
	}
	
	/**
	 * 
	 * Method : testUserForm
	 * 작성자 : pjk
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록 폼
	 */
	@Test
	public void testUserForm() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userForm")).andReturn();
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("user/userForm", viewName);
	}
	
	/**
	 * 
	 * Method : testUserForm_post
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 사용자 등록 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void testUserForm_post_success() throws Exception {
		/***Given***/
		File profileFile = new File("/Users/pjk/Desktop/이미지/아이린4.gif");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = 
				new MockMultipartFile("profile", "아이린.gif", "image/gif", fis);
		
		MvcResult mvcResult = 
				mockMvc.perform(fileUpload("/user/userForm").file(file)
				.param("userId", USER_INSERT_TEST_ID)
				.param("userNm", "test99")
				.param("alias", "테스트")
				.param("addr1", "대전 중구 대흥로 76")
				.param("addr2", "2층 DDIT")
				.param("zipcode", "34842")
				.param("pass", "1234"))
			.andExpect(view().name("redirect:/user/userPagingList")).andReturn();
		
		/***When***/
		HttpSession session = mvcResult.getRequest().getSession();
		
		/***Then***/
		assertEquals("정상 등록 되었습니다", session.getAttribute("msg"));
	}
	
	/**
	 * 
	 * Method : testUserForm_post_fail_duplicateUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록요청 (중복 사용자로 인한 등록 실패 케이스) 테스트
	 */
	@Test
	public void testUserForm_post_fail_duplicateUser() throws Exception {
		/***Given***/
		File profileFile = new File("/Users/pjk/Desktop/이미지/아이린4.gif");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = 
				new MockMultipartFile("profile", "아이린.gif", "image/gif", fis);
		
		MvcResult mvcResult = 
				mockMvc.perform(fileUpload("/user/userForm").file(file)
				.param("userId", "joker")
				.param("userNm", "test99")
				.param("alias", "테스트")
				.param("addr1", "대전 중구 대흥로 76")
				.param("addr2", "2층 DDIT")
				.param("zipcode", "34842")
				.param("pass", "1234"))
			.andExpect(view().name("user/userForm")).andReturn();
		
		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		String msg = (String) mav.getModel().get("msg");
		
		/***Then***/
		assertEquals("중복된 아이디 입니다", msg);
	}
	
	/**
	 * 
	 * Method : testUserForm_post_fail_insertError
	 * 작성자 : pjk
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록 (zipcode 사이즈  sql 에러) 테스트
	 */
	@Test
	public void testUserForm_post_fail_insertError() throws Exception {
		/***Given***/
		File profileFile = new File("/Users/pjk/Desktop/이미지/아이린4.gif");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = 
				new MockMultipartFile("profile", "아이린.gif", "image/gif", fis);
		
		MvcResult mvcResult = 
				mockMvc.perform(fileUpload("/user/userForm").file(file)
				.param("userId", USER_INSERT_TEST_ID)
				.param("userNm", "test99")
				.param("alias", "테스트")
				.param("addr1", "대전 중구 대흥로 76")
				.param("addr2", "2층 DDIT")
				.param("zipcode", "348423484234842")
				.param("pass", "1234"))
			.andExpect(view().name("redirect:/user/userPagingList")).andReturn();
		
		/***When***/
		HttpSession session = mvcResult.getRequest().getSession();
		
		/***Then***/
		assertEquals("정상 등록 되었습니다", session.getAttribute("msg"));
	}
	
	
}
