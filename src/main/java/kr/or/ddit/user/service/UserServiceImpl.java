package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

@Service("userService")
public class UserServiceImpl implements IUserService{
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	public UserServiceImpl() {
		//userDao = new UserDaoImpl();
	}
	
	/**
	 * 
	 * Method : getAllUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 조회
	 */
	
	@Override
	public List<UserVO> getAllUser() {
		List<UserVO> userList = userDao.getAllUser();
		
		return userList;
	}
	
	
	/**
	 * 
	 * Method : searchUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 특정 사용자 조회
	 */

	@Override
	public UserVO searchUser(String userId) {
		UserVO userVO = userDao.searchUser(userId);
		
		return userVO;
	}

	/**
	 * 
	 * Method : selectUserPagingList
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 페이징 리스트 조회
	 */
	@Override
	public Map<String, Object> selectUserPagingList(PageVO pageVO) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("userList", userDao.selectUserPagingList(pageVO));
		resultMap.put("userCnt", userDao.getUserCnt());
		
		return resultMap;
	}
	
	/**
	 * 
	 * Method : insertUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 사용자 등록
	 */
	@Override
	public int insertUser(UserVO vo) {
		
		int insertCnt = userDao.insertUser(vo);
		
		return insertCnt;
	}
	
	/**
	 * 
	 * Method : deleteUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 사용자 등록
	 */
	@Override
	public int deleteUser(String userId) {
		
		int deleteCnt = userDao.deleteUser(userId);
		
		return deleteCnt;
	}
	
	/**
	 * 
	 * Method : updateUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 사용자 수정
	 */
	@Override
	public int updateUser(UserVO vo) {
		int updateCnt = userDao.updateUser(vo);
		
		return updateCnt;
	}
	
	/**
	 * 
	 * Method : encryptPass
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 비밀번호 암호화
	 */
	@Override
	public int encryptPass() {
		
		List<UserVO> userList = userDao.getAllUser();
		
		int updateCnt = 0;
		
		for(UserVO userVO : userList ) {
			String encryptPw = KISA_SHA256.encrypt(userVO.getPass());
			userVO.setPass(encryptPw);
			
			updateCnt += userDao.updateUserPass(userVO);
		}
		
		return updateCnt;
	}

}
