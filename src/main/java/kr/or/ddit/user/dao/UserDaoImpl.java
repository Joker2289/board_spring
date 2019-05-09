package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.model.PageVO;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {
	
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 
	 * Method : getAllUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 조회
	 */
	public List<UserVO> getAllUser(){
		List<UserVO> userList = sqlSessionTemplate.selectList("user.getAllUser"); //Ibatis의 queryForList 와 같음 쓰는방식도 똑같다 ("테이블별칭.쿼리이름" , 파라미터값);
		
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
		
		UserVO vo = sqlSessionTemplate.selectOne("user.searchUser", userId);
		
		return vo;
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
	public List<UserVO> selectUserPagingList(PageVO pageVO) {
		
		List<UserVO> userList = sqlSessionTemplate.selectList("user.selectUserPagingList", pageVO);
		
		return userList;
	}
	
	/**
	 * 
	 * Method : getUserCnt
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 수 조회
	 */
	@Override
	public int getUserCnt() {
		
		int userCnt = sqlSessionTemplate.selectOne("user.getUserCnt");
		
		return userCnt;
	}

	
	/**
	 * 
	 * Method : insertUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 등록
	 */
	@Override
	public int insertUser(UserVO vo) {
		
		int insertCnt = sqlSessionTemplate.insert("user.insertUser", vo);
		
		return insertCnt;
	}
	
	
	/**
	 * 
	 * Method : deleteUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 삭제
	 */
	@Override
	public int deleteUser(String userId) {
		
		int deleteCnt = sqlSessionTemplate.delete("user.deleteUser", userId);
		
		return deleteCnt;
	}
	
	/**
	 * 
	 * Method : updateUser
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param vo
	 * @return
	 * Method 설명 : 사용자 수정
	 */
	@Override
	public int updateUser(UserVO vo) {
		
		int updateCnt = sqlSessionTemplate.update("user.updateUser", vo);
		
		return updateCnt;
	}
	
	/**
	 * 
	 * Method : updateUserPass
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 사용자 PW 암호화 업데이트
	 */
	@Override
	public int updateUserPass(UserVO vo) {
		
		int updateCnt = sqlSessionTemplate.update("user.updateUserPass", vo);
		
		return updateCnt;
	}
}
