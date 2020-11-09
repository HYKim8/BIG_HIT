package com.bighit.on.cmn;

public class Message extends DTO {

	/**
	 * 메시지 ID : 10
	 */
	private String regId;
	
	private String thrKey;
	
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

	
	public String getThrKey() {
		return thrKey;
	}

	public void setThrKey(String thrKey) {
		this.thrKey = thrKey;
	}

	@Override
	public String toString() {
		return "Message [regId=" + regId + ", thrKey=" + thrKey + ", msgContents=" + msgContents + ", toString()="
				+ super.toString() + "]";
	}

	
	
	
}
