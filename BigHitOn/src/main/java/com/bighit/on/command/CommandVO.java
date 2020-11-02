package com.bighit.on.command;

import com.bighit.on.cmn.DTO;

public class CommandVO extends DTO {
	
	/**
	 * 커맨드 아이디
	 */
	private int comId;  
	 
	/**
	 * 어플명
	 */
	private String appName;
	
	/**
	 * 명령어 명
	 */
	private String cmdName;
	
	/**
	 * 인자개수
	 */
	private int paramCnt;
	
	public CommandVO() {		
	}

	public CommandVO(int comId, String appName, String cmdName, int paramCnt) {
		super();
		this.comId = comId;
		this.appName = appName;
		this.cmdName = cmdName;
		this.paramCnt = paramCnt;
	}

	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getCmdName() {
		return cmdName;
	}

	public void setCmdName(String cmdName) {
		this.cmdName = cmdName;
	}

	public int getParamCnt() {
		return paramCnt;
	}

	public void setParamCnt(int paramCnt) {
		this.paramCnt = paramCnt;
	}

	@Override
	public String toString() {
		return "CommendVO [comId=" + comId + ", appName=" + appName + ", cmdName=" + cmdName + ", paramCnt=" + paramCnt
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
