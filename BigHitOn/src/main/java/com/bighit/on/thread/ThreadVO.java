package com.bighit.on.thread;

public class ThreadVO {
	/**
	 * 쓰레드 키
	 */
	private String ThrKey;
	
	/**
	 * 채널링크
	 */
	private String ChLink;
	
	/**
	 * 내용
	 */
	private String Contents;
	
	/**
	 * 고정 유무
	 */
	private int IsPin;
	
	/**
	 * 고정자
	 */
	private String PinId;
	
	/**
	 * 작성자
	 */
	private String RegId;
	
	/**
	 * 작성일
	 */
	private String RegDt;
	
	/**
	 * 수정일
	 */
	private String ModDt;
	
	/**
	 * 부모 쓰레드키
	 */
	private String ParentKey;
	
	public ThreadVO() {
	}

	public ThreadVO(String thrKey, String chLink, String contents, int isPin, String pinId, String regId, String regDt,
			String modDt, String parentKey) {
		super();
		ThrKey = thrKey;
		ChLink = chLink;
		Contents = contents;
		IsPin = isPin;
		PinId = pinId;
		RegId = regId;
		RegDt = regDt;
		ModDt = modDt;
		ParentKey = parentKey;
	}

	public String getThrKey() {
		return ThrKey;
	}

	public void setThrKey(String thrKey) {
		ThrKey = thrKey;
	}

	public String getChLink() {
		return ChLink;
	}

	public void setChLink(String chLink) {
		ChLink = chLink;
	}

	public String getContents() {
		return Contents;
	}

	public void setContents(String contents) {
		Contents = contents;
	}

	public int getIsPin() {
		return IsPin;
	}

	public void setIsPin(int isPin) {
		IsPin = isPin;
	}

	public String getPinId() {
		return PinId;
	}

	public void setPinId(String pinId) {
		PinId = pinId;
	}

	public String getRegId() {
		return RegId;
	}

	public void setRegId(String regId) {
		RegId = regId;
	}

	public String getRegDt() {
		return RegDt;
	}

	public void setRegDt(String regDt) {
		RegDt = regDt;
	}

	public String getModDt() {
		return ModDt;
	}

	public void setModDt(String modDt) {
		ModDt = modDt;
	}

	public String getParentKey() {
		return ParentKey;
	}

	public void setParentKey(String parentKey) {
		ParentKey = parentKey;
	}

	@Override
	public String toString() {
		return "ThreadVO [ThrKey=" + ThrKey + ", ChLink=" + ChLink + ", Contents=" + Contents + ", IsPin=" + IsPin
				+ ", PinId=" + PinId + ", RegId=" + RegId + ", RegDt=" + RegDt + ", ModDt=" + ModDt + ", ParentKey="
				+ ParentKey + ", toString()=" + super.toString() + "]";
	}
	
	
}
