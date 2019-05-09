package kr.or.ddit.notice.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.attachment.model.AttachmentVO;
import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.PageVO;


public class NoticeServiceImplTest extends LogicTestConfig{
	
	@Resource(name="noticeService")
	private INoticeService service;
	
	@Before
	public void setup() {
	}
	
	/**
	 * 
	 * Method : test
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 게시글 리스트 조회 테스트 (페이징 처리)
	 */
	@Test
	public void testSelectNoitceList() {
		/***Given***/
		PageVO pageVO = new PageVO();
		pageVO.setBoard_code("1"); //자유게시판, 게시판 분류 코드
		//pageVO 기본값 page=1, pageSize=10
		
		/***When***/
		Map<String, Object> resultMap = service.selectNoticeList(pageVO);
		List<NoticeVO> noticeList = (List<NoticeVO>) resultMap.get("noticeList");
		/***Then***/
		assertEquals(10, noticeList.size());
	}
	
	/**
	 * 
	 * Method : testInsertNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 게시글 등록 테스트
	 */
	@Test
	public void testInsertNotice() {
		/***Given***/
		NoticeVO vo = new NoticeVO();
		vo.setBoard_code("5"); // 5번 테스트 코드 게시판
		vo.setContent("안녕하세요");
		vo.setTitle("안녕하세요 로제입니다");
		vo.setUserId("rose");
		List<AttachmentVO> attachmentList = new ArrayList<AttachmentVO>();
		/***When***/
		int insertCnt = service.insertNotice(vo, attachmentList);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	 * 
	 * Method : testSelectNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 	게시글 조회 테스트
	 */
	@Test
	public void testSelectNotice() {
		/***Given***/
		/***When***/
		NoticeVO vo = service.selectNotice("77");
		/***Then***/
		assertEquals("joker", vo.getUserId());
	}
	
	/**
	 * 
	 * Method : testDeleteNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 게시글 삭제
	 */
	@Test
	public void testDeleteNotice() {
		/***Given***/
		/***When***/
		int deleteCnt = service.deleteNotice("35");
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	/**
	 * 
	 * Method : testUpdateNotice
	 * 작성자 : pjk
	 * 변경이력 :
	 * Method 설명 : 게시글 수정
	 */
	@Test
	public void testUpdateNotice() {
		/***Given***/
		NoticeVO vo = new NoticeVO();
		vo.setNotice_num("85");
		vo.setContent("<p><span style=\"font-size: 36pt;\">안녕하세요 로제에요</span></p><p><span style=\"font-size: 36pt;\">﻿<img src=\"/upload/04826461-11e7-426e-8c3f-a0919fcb88d4.png&#10;\" title=\"%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A62.png\">&nbsp;</span></p>");
		vo.setTitle("가입인사 드립니다 수정");
		List<AttachmentVO> attachmentList = new ArrayList<>();
		String[] removeList = null;
		/***When***/
		int updateCnt = service.updateNotice(vo, removeList, attachmentList);
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	
	
	

}
