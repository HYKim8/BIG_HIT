package com.bighit.on.email;

import com.bighit.on.cmn.DTO;

public class EmailVO extends DTO{
	
	/**
	 * 받는사람email
	 */
	private String email;
	
	/**
	 * 워크스페이스링크
	 */
	private String wsLink;
	
	/**
	 * 워크스페이스이름
	 */
	private String wsName;
	
	/**
	 * 보내는사람 아이디
	 */
	private String userId;
	
	
	public EmailVO() {
		
	}


	public EmailVO(String email, String wsLink, String wsName, String userId) {
		super();
		this.email = email;
		this.wsLink = wsLink;
		this.wsName = wsName;
		this.userId = userId;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getWsLink() {
		return wsLink;
	}


	public void setWsLink(String wsLink) {
		this.wsLink = wsLink;
	}


	public String getWsName() {
		return wsName;
	}


	public void setWsName(String wsName) {
		this.wsName = wsName;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "EmailVO [email=" + email + ", wsLink=" + wsLink + ", wsName=" + wsName + ", userId=" + userId
				+ ", toString()=" + super.toString() + "]";
	}

	

}
