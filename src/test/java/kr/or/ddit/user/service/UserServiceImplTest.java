package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

public class UserServiceImplTest extends LogicTestConfig{
	
	@Resource(name="userService")
	private IUserService service;
	
	@Before
	public void setup() {
	}
	
	//getAllUser 메소드를 테스트 하는 메소드 작성
	@Test
	public void testGetAllUSer() {
		/***Given***/
		
		/***When***/
		List<UserVO> userList = service.getAllUser();
		for(UserVO vo : userList) {
			System.out.println(vo);
		}
		/***Then***/
		assertNotNull(userList);
		//assertEquals(5, userList.size());
	}
	
	@Test
	public void testSearchUser() {
		/***Given***/
		
		/***When***/
		UserVO rvo = service.searchUser("cony");
		System.out.println("==========================");
		System.out.println("PW : " + rvo.getPass());
		System.out.println("==========================");
		
		/***Then***/
		assertNotNull(rvo);
	}
	
	@Test
	public void testSelectUserPagingList() {
		/***Given***/
		PageVO pageVO = new PageVO(1, 10);
		/***When***/
		Map<String, Object> resultMap = service.selectUserPagingList(pageVO);
		
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		int userCnt = (Integer) resultMap.get("userCnt");
		
		for(UserVO user : userList) {
			System.out.println(user);
		}
		System.out.println("userCnt : " + userCnt);
		/***Then***/
		assertNotNull(userList);
		
	}
	
	@Test
	public void testInsertUser() {
		/***Given***/
		UserVO vo = new UserVO();
		vo.setUserId("test7890");
		vo.setPass("1234");
		vo.setAlias("testNick");
		vo.setZipcode("34942");
		vo.setUserNm("name");
		vo.setAddr1("대전 서구 관저동");
		vo.setAddr2("addr2");
		/***When***/
		int cnt = service.insertUser(vo);
		
		service.deleteUser("test7890");
		/***Then***/
		assertEquals(1, cnt);
	}
	
	//@Test
	public void testEncryptPass() {
		/***Given***/
		/***When***/
		int cnt = service.encryptPass();
		/***Then***/
		assertEquals(112, cnt);
	}

}
