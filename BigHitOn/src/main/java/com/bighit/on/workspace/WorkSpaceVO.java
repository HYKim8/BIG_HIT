package com.bighit.on.workspace;

import com.bighit.on.cmn.DTO;

public class WorkSpaceVO extends DTO {
	
	/**
	 * 워크스페이스 링크
	 */
	private String wsLink;
	
	/**
	 * 워크스페이스 명칭
	 */
	private String wsName;
	
	/**
	 * 프로젝트 소개
	 */
	private String project;
	
	/**
	 * 작성자
	 */
	private String regId;
	
	/**
	 * 작성시간
	 */
	private String regDt;
	
	public WorkSpaceVO() {
		
	}

	public WorkSpaceVO(String wsLink, String wsName, String project, String regId, String regDt) {
		super();
		this.wsLink = wsLink;
		this.wsName = wsName;
		this.project = project;
		this.regId = regId;
		this.regDt = regDt;
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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "WorkSpaceVO [wsLink=" + wsLink + ", wsName=" + wsName + ", project=" + project + ", regId=" + regId
				+ ", regDt=" + regDt + "]";
	}
	
}
