package kr.or.ddit.notice.model;

import java.util.Date;

public class NoticeVO {
	
	private String notice_num;		//게시글 번호
	private String board_code;		//게시판 코드
	private String userId;			//작성자 ID
	private String parent_num;		//부모글 번호
	private String title;			//제목
	private String content;			//내용
	private Date rep_dt;			//작성일
	
	
	public String getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(String notice_num) {
		this.notice_num = notice_num;
	}
	public String getBoard_code() {
		return board_code;
	}
	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getParent_num() {
		return parent_num;
	}
	public void setParent_num(String parent_num) {
		this.parent_num = parent_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRep_dt() {
		return rep_dt;
	}
	public void setRep_dt(Date rep_dt) {
		this.rep_dt = rep_dt;
	}
	
	@Override
	public String toString() {
		return "NoticeVO [notice_num=" + notice_num + ", board_code=" + board_code + ", userId=" + userId
				+ ", parent_num=" + parent_num + ", title=" + title + ", content=" + content + ", rep_dt=" + rep_dt
				+ "]";
	}
}
