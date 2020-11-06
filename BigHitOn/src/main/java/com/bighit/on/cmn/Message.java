package com.bighit.on.cmn;

public class Message extends DTO {

	/**
	 * 메시지 ID : 10
	 */
	private String regId;
	
	/**
	 * 메시지 내용: 등록되었습니다.
	 */
	private String msgContents;
	
	public Message() {
		
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getMsgContents() {
		return msgContents;
	}

	public void setMsgContents(String msgContents) {
		this.msgContents = msgContents;
	}

	@Override
	public String toString() {
		return "Message [regId=" + regId + ", msgContents=" + msgContents + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
