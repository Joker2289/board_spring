package kr.or.ddit.comments.model;

import java.util.Date;

public class CommentsVO {
	
	private String 	comment_num;		//댓글번호
	private String 	notice_num;			//게시글 번호
	private String 	userId;				//작성자 ID
	private String 	comment_content;		//댓글 내용
	private Date	rep_dt;				//작성일
	private String 	del_state;			//삭제여부
	
	public String getComment_num() {
		return comment_num;
	}
	public void setComment_num(String comment_num) {
		this.comment_num = comment_num;
	}
	public String getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(String notice_num) {
		this.notice_num = notice_num;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Date getRep_dt() {
		return rep_dt;
	}
	public void setRep_dt(Date rep_dt) {
		this.rep_dt = rep_dt;
	}
	public String getDel_state() {
		return del_state;
	}
	public void setDel_state(String del_state) {
		this.del_state = del_state;
	}
	
	@Override
	public String toString() {
		return "CommentsVO [comment_num=" + comment_num + ", notice_num=" + notice_num + ", userId=" + userId
				+ ", comment_content=" + comment_content + ", rep_dt=" + rep_dt + ", del_state=" + del_state + "]";
	}
	
	
}
