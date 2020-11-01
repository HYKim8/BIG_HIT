package com.bighit.on.reaction;

import com.bighit.on.cmn.DTO;

public class ReactionVO extends DTO {
	private int emoji;
	/** 언급 수신자 */
	private String resId;
	/** 쓰레드키 */
	private String thrKey;
	/** 작성자 */
	private String regId;
	/** 작성일 */
	private String regDt;
	
	public ReactionVO() {}

	public ReactionVO(int emoji, String resId, String thrKey, String regId, String regDt) {
		super();
		this.emoji = emoji;
		this.resId = resId;
		this.thrKey = thrKey;
		this.regId = regId;
		this.regDt = regDt;
	}

	public int getEmoji() {
		return emoji;
	}

	public void setEmoji(int emoji) {
		this.emoji = emoji;
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
		return "ReactionVO [emoji=" + emoji + ", resId=" + resId + ", thrKey=" + thrKey + ", regId=" + regId
				+ ", regDt=" + regDt + ", toString()=" + super.toString() + "]";
	}

	
	
}
