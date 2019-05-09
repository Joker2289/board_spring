
package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements IBoardService{
	
	@Resource(name="boardDao")
	private IBoardDao boardDao;
	
	/**
	 * 
	 * Method : insertBorad
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 게시판 등록
	 */
	@Override
	public int insertBorad(BoardVO vo) {
		int insertCnt = boardDao.insertBoard(vo);
		return insertCnt;
	}
	
	/**
	 * 
	 * Method : getAllBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @return
	 * Method 설명 : 전체 게시판 출력
	 */
	@Override
	public List<BoardVO> getAllBoard() {
		List<BoardVO> boardList = boardDao.getAllBoard();
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
		
		BoardVO boardVO = boardDao.searchBoard(board_code);
		
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
		
		int updateCnt = boardDao.updateBoard(vo);
		
		return updateCnt;
	}

}
