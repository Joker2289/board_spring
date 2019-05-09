package kr.or.ddit.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVO;

@Repository("boardDao")
public class BoardDaoImpl implements IBoardDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 
	 * Method : insertBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 게시판 등록
	 */
	@Override
	public int insertBoard(BoardVO vo) {
		int insertCnt = sqlSessionTemplate.insert("board.insertBoard", vo);
		return insertCnt;
	}
	
	/**
	 * 
	 * Method : getAllBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @return
	 * Method 설명 : 전체 게시판 조회
	 */
	@Override
	public List<BoardVO> getAllBoard() {
		List<BoardVO> boardList = sqlSessionTemplate.selectList("board.getAllBoard");
		return boardList;
	}
	
	/**
	 * 
	 * Method : searchBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_name
	 * @return
	 * Method 설명 : 게시판 검색
	 */
	@Override
	public BoardVO searchBoard(String board_code) {
		BoardVO boardVO = sqlSessionTemplate.selectOne("board.searchBoard", board_code);
		return boardVO;
	}
	
	/**
	 * 
	 * Method : updateBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param vo
	 * @return
	 * Method 설명 : 게시판 수정
	 */
	@Override
	public int updateBoard(BoardVO vo) {
		int updateCnt = sqlSessionTemplate.update("board.updateBoard", vo);
		return updateCnt;
	}
	
	
}
