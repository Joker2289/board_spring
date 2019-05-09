package kr.or.ddit.board.service;

import java.util.List;


import kr.or.ddit.board.model.BoardVO;

public interface IBoardService {
	
	/**
	 * 
	 * Method : insertBorad
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 : 게시판 등록
	 */
	int insertBorad(BoardVO vo);
	
	/**
	 * 
	 * Method : getAllBoard
	 * 작성자 : pjk
	 * 변경이력 :
	 * @param sqlSession
	 * @param vo
	 * @return
	 * Method 설명 : 전체 게시판 출력
	 */
	List<BoardVO> getAllBoard();
	
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
	BoardVO searchBoard(String board_code);
	
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
	int updateBoard(BoardVO vo);
	
}
