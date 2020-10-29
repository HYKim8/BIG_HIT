package com.bighit.on.mention;

import com.bighit.on.cmn.DTO;

public class MentionVO extends DTO {
	/** 언급 수신자 */
	private String resId;
	/** 쓰레드키 */
	private String thrKey;
	/** 작성자 */
	private String regId;
	/** 작성일 */
	private String regDt;
	
	public MentionVO() {}

	public MentionVO(String resId, String thrKey, String regId, String regDt) {
		super();
		this.resId = resId;
		this.thrKey = thrKey;
		this.regId = regId;
		this.regDt = regDt;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
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
		return "MentionVO [resId=" + resId + ", thrKey=" + thrKey + ", regId=" + regId + ", regDt=" + regDt
				+ ", toString()=" + super.toString() + "]";
	}
	
}
