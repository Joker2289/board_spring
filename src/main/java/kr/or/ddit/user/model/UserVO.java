package kr.or.ddit.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserVO implements HttpSessionBindingListener{
	
	private Logger logger = LoggerFactory.getLogger(UserVO.class);
	
	// hibernate validator 사용
	// @NotBlank -> white space입력 가능

	// white space도 거절 -> 문자열이 반드시 입력되어야 함
	// 에러코드 : 어노테이션 명 -> 메세지 소스에 '어노테이션 명.필드명'로 에러메세지를 등록할 수 있다.
	@NotEmpty
	private String userId; 			// 사용자 id
	
	@Size(min=8)
	private String pass; 			// 사용자 pw
	
	private String userNm; 			// 사용자 이름
	private String alias; 			// 사용자 닉네임
	private String zipcode; 		// 우편번호
	private String addr1; 			// 주소1
	private String addr2; 			// 주소2
	private Date reg_dt; 			// 등록일시
	private String fileName;		// 파일이름
	private String realFileName; 	// 파일이름 + 경로
	
	public UserVO() {
		super();
	}

	public UserVO(String userId, String pass, String userNm, String alias, String zipcode, String addr1, String addr2) {
		super();
		this.userId = userId;
		this.pass = pass;
		this.userNm = userNm;
		this.alias = alias;
		this.zipcode = zipcode;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String password) {
		this.pass = password;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public Date getReg_dt() {
		return reg_dt;
	}

	// reg_dt 값을 yyyy-MM-dd 포맷팅
	public String getReg_dt_fmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(reg_dt);
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRealFileName() {
		return realFileName;
	}

	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", pass=" + pass + ", userNm=" + userNm + ", alias=" + alias + ", zipcode="
				+ zipcode + ", addr1=" + addr1 + ", addr2=" + addr2 + ", reg_dt=" + reg_dt + "]";
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		
		logger.debug("userVO valueBound : {} ", session.getId());
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		
		logger.debug("userVO valueUnBound : {} ", session.getId());
		
	}

}
