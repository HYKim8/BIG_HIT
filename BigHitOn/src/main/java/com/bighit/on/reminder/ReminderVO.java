package com.bighit.on.reminder;

import java.sql.Date;

import com.bighit.on.cmn.DTO;

public class ReminderVO extends DTO {

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

	private String wsLink;

	public ReminderVO() {
	}

	public ReminderVO(String remindId, String thrKey, String remindTime, String regId, String regDt) {
		super();
		this.remindId = remindId;
		this.thrKey = thrKey;
		this.remindTime = remindTime;
		this.regId = regId;
		this.regDt = regDt;
	}

	public String getWsLink() {
		return wsLink;
	}

	public void setWsLink(String wsLink) {
		this.wsLink = wsLink;
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
