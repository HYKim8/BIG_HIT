package com.bighit.on.channelusers;

import com.bighit.on.cmn.DTO;

public class ChannelUsersVO extends DTO {
	private String chLink;
	private String userSerial;
	private int notification;
	
	public ChannelUsersVO() {}

	public ChannelUsersVO(String chLink, String userSerial, int notification) {
		super();
		this.chLink = chLink;
		this.userSerial = userSerial;
		this.notification = notification;
	}
	
	public ChannelUsersVO(String chLink, String userSerial) {
		super();
		this.chLink = chLink;
		this.userSerial = userSerial;
	}

	public String getChLink() {
		return chLink;
	}

	public void setChLink(String chLink) {
		this.chLink = chLink;
	}

	public String getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(String userSerial) {
		this.userSerial = userSerial;
	}

	public int getNotification() {
		return notification;
	}

	public void setNotification(int notification) {
		this.notification = notification;
	}

	@Override
	public String toString() {
		return "ChannelUsersVO [chLink=" + chLink + ", userSerial=" + userSerial + ", notification=" + notification
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
