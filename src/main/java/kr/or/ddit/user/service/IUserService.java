package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

public interface IUserService {
	/**
	 * 
	 * Method : getAllUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 조회
	 */
	List<UserVO> getAllUser();
	
	
	/**
	 * 
	 * Method : getAllUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 특정 사용자 조회
	 */
	UserVO searchUser(String userId);
	
	/**
	 * 
	 * Method : getAllUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 페이징 리스트 조회
	 */
	Map<String, Object>selectUserPagingList(PageVO pageVO);
	
	/**
	 * 
	 * Method : insertUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 등록
	 */
	int insertUser(UserVO vo);
	
	/**
	 * 
	 * Method : deleteUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 삭제
	 */
	int deleteUser(String userId);
	
	/**
	 * 
	 * Method : updateUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 사용자 수정
	 */
	int updateUser(UserVO vo);
	
	/**
	 * 
	 * Method : updateUserPass
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 사용자 PW 암호화 업데이트
	 */
	int encryptPass();
	
}
