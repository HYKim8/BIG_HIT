package com.bighit.on.reminder;

public class ReminderVO  {

	/**
	 * 리마인드 아이디
	 */
	private String remindId;

	/**
	 * 쓰레드키
	 */
	private String thrKey;

	/**
	 * 리마인드 시간
	 */
	private String remindTime;

	/**
	 * 작성자
	 */
	private String regId;

	/**
	 * 작성 시간
	 */
	private String regDt;

	public ReminderVO() {
		super();
	}

	public ReminderVO(String remindId, String thrKey, String remindTime, String regId, String regDt) {
		super();
		this.remindId = remindId;
		this.thrKey = thrKey;
		this.remindTime = remindTime;
		this.regId = regId;
		this.regDt = regDt;
	}

	public String getRemindId() {
		return remindId;
	}

	public void setRemindId(String remindId) {
		this.remindId = remindId;
	}

	public String getThrKey() {
		return thrKey;
	}

	public void setThrKey(String thrKey) {
		this.thrKey = thrKey;
	}

	public String getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(String remindTime) {
		this.remindTime = remindTime;
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
		return "ReminderVO [remindId=" + remindId + ", thrKey=" + thrKey + ", remindTime=" + remindTime + ", regId="
				+ regId + ", regDt=" + regDt + "]";
	}

	
}
