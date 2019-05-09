package kr.or.ddit.attachment.model;

public class AttachmentVO {
	
	private String file_num;		//첨부파일 번호
	private String notice_num;		//게시글 번호
	private String file_name;		//파일이름
	private String file_path;		//파일경로
	
	public String getFile_num() {
		return file_num;
	}
	public void setFile_num(String file_num) {
		this.file_num = file_num;
	}
	public String getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(String notice_num) {
		this.notice_num = notice_num;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	@Override
	public String toString() {
		return "AttachmentVO [file_num=" + file_num + ", notice_num=" + notice_num + ", file_name=" + file_name
				+ ", file_path=" + file_path + "]";
	}
	
	
	
}
