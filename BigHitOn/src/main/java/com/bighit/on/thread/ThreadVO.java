package com.bighit.on.thread;

import com.bighit.on.cmn.DTO;

public class ThreadVO extends DTO {
	/**
	 * 쓰레드 키
	 */
	private String thrKey;
	
	/**
	 * 채널링크
	 */
	private String chLink;
	
	/**
	 * 내용
	 */
	private String contents;
	
	/**
	 * 고정 유무
	 */
	private int isPin;
	
	/**
	 * 고정자
	 */
	private String pinId;
	
	/**
	 * 작성자
	 */
	private String regId;
	
	/**
	 * 작성일
	 */
	private String regDt;
	
	/**
	 * 수정일
	 */
	private String modDt;
	
	/**
	 * 부모 쓰레드키
	 */
	private String parentKey;
	
	private int childCnt;
	
	public ThreadVO() {
	}

	public ThreadVO(String thrKey, String chLink, String contents, int isPin, String pinId, String regId, String regDt,
			String modDt, String parentKey, int childCnt) {
		super();
		this.thrKey = thrKey;
		this.chLink = chLink;
		this.contents = contents;
		this.isPin = isPin;
		this.pinId = pinId;
		this.regId = regId;
		this.regDt = regDt;
		this.modDt = modDt;
		this.parentKey = parentKey;
		this.childCnt = childCnt;
	}

	public String getThrKey() {
		return thrKey;
	}

	public void setThrKey(String thrKey) {
		this.thrKey = thrKey;
	}

	public String getChLink() {
		return chLink;
	}

	public void setChLink(String chLink) {
		this.chLink = chLink;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getIsPin() {
		return isPin;
	}

	public void setIsPin(int isPin) {
		this.isPin = isPin;
	}

	public String getPinId() {
		return pinId;
	}

	public void setPinId(String pinId) {
		this.pinId = pinId;
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

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public int getChildCnt() {
		return childCnt;
	}

	public void setChildCnt(int childCnt) {
		this.childCnt = childCnt;
	}

	@Override
	public String toString() {
		return "ThreadVO [thrKey=" + thrKey + ", chLink=" + chLink + ", contents=" + contents + ", isPin=" + isPin
				+ ", pinId=" + pinId + ", regId=" + regId + ", regDt=" + regDt + ", modDt=" + modDt + ", parentKey="
				+ parentKey + ", childCnt=" + childCnt + ", toString()=" + super.toString() + "]";
	}

	
	
}
