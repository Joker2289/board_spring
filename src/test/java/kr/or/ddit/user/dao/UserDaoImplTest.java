package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

public class UserDaoImplTest extends LogicTestConfig{
	
	@Resource(name="userDao")
	private IUserDao dao;
	

	//@Before - @Test - @After
	
	
	@Before
	public void setup() {

	}
	
	@After
	public void tearDown() {
		
	}
	
	
	//getAllUser 메소드를 테스트 하는 메소드 작성
	@Test
	public void testGetAllUser() {
		/***Given***/
		
		/***When***/
		List<UserVO> userList = dao.getAllUser();
		for(UserVO vo : userList) {
			System.out.println(vo);
		}
		/***Then***/
		assertNotNull(userList);
		//assertEquals(5, userList.size());		// 총 userList가 변경되서 수시로 바까줘야함
	}
	
	@Test
	public void testSearchUser() {
		/***Given***/
		
		/***When***/
		UserVO rvo = dao.searchUser("cony");
		System.out.println("==========================");
		System.out.println("PW : " + rvo.getPass());
		System.out.println("==========================");
		
		/***Then***/
		assertNotNull(rvo);
	}
	
	@Test
	public void testSelectUserPagingList() {
		/***Given***/
		PageVO pageVO = new PageVO(1,10);
		/***When***/
		List<UserVO> userList = dao.selectUserPagingList(pageVO);
		userList.get(0).getReg_dt();
		/***Then***/
		assertNotNull(userList);
		assertEquals(10, userList.size());
	}
	
	@Test
	public void testGetUserCnt() {
		/***Given***/
		
		/***When***/
		int userCnt = dao.getUserCnt();
		/***Then***/
		assertTrue(100<userCnt);
		
	}
	
	
	//페이지 수 구하는 메서드
	@Test
	public void testPagination() {
		/***Given***/
		int userCnt = 105;
		int pageSize = 10;
		/***When***/
		int lastPage = userCnt/pageSize + (userCnt%pageSize > 0 ? 1:0);
		/***Then***/
		assertEquals(11, lastPage);
		
	}
	
	//페이지 수 구하는 메서드
	@Test
	public void testPagination2() {
		/***Given***/
		int userCnt = 111;
		int pageSize = 10;
		/***When***/
		int lastPage = userCnt/pageSize + (userCnt%pageSize > 0 ? 1:0);
		/***Then***/
		assertEquals(12, lastPage);
		
	}
	
	@Test
	public void testInsertUser() {
		/***Given***/
		UserVO vo = new UserVO();
		vo.setUserId("test");
		vo.setPass("1234");
		vo.setAlias("testNick");
		vo.setZipcode("34942");
		vo.setUserNm("name");
		vo.setAddr1("대전 서구 관저동");
		vo.setAddr2("addr2");
		
		/***When***/
		int cnt = dao.insertUser(vo);
		/***Then***/
		assertEquals(1, cnt);
	}
	
	
	

}
