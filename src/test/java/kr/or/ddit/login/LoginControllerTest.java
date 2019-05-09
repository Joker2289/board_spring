package kr.or.ddit.login;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVO;

public class LoginControllerTest extends WebTestConfig{
	
	/**
	 * 
	 * Method : testLoginView
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 로그인 화면 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void testLoginView() throws Exception {
		
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("login/login", viewName);
	}
	
	/**
	 * 
	 * Method : testLoginProcess_sucess
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 사용자 로그인 요청 성공 테스트
	 * @throws Exception 
	 * 
	 * 
	 * 
	 */
	@Test
	public void testLoginProcess_sucess() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login").param("userId", "joker")
				.param("pass", "1234")).andReturn();
	
		//session userVO
		//main
		MockHttpServletRequest request = mvcResult.getRequest();
		UserVO userVO = (UserVO) request.getSession().getAttribute("userVO_log");
		
		//view name
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("module/main_D", viewName);
		assertEquals("조커", userVO.getUserNm());
	}
	
	@Test
	public void testLoginProcess_faild() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login").param("userId", "joker")
				.param("pass", "124")).andReturn();
	
		//session userVO
		//main
		MockHttpServletRequest request = mvcResult.getRequest();
		
		//view name
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("login/login", viewName);
	}

}
