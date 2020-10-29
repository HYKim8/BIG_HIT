package com.bighit.on.save;

import com.bighit.on.cmn.DTO;

public class SaveThrVO extends DTO {
	/** 쓰레드키 */
	private String thrKey;
	/** 작성자 */
	private String regId;
	/** 작성일 */
	private String regDt;
	
	public SaveThrVO() {}

	
	public SaveThrVO(String thrKey, String regId, String regDt) {
		super();
		this.thrKey = thrKey;
		this.regId = regId;
		this.regDt = regDt;
	}


	public String getThrKey() {
		return thrKey;
	}

	public void setThrKey(String thrKey) {
		this.thrKey = thrKey;
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
		return "SaveThrVO [thrKey=" + thrKey + ", regId=" + regId + ", regDt=" + regDt + ", toString()="
				+ super.toString() + "]";
	}
	
}
