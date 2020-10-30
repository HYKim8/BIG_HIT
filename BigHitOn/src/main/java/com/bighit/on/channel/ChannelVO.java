package com.bighit.on.channel;

import com.bighit.on.cmn.DTO;

public class ChannelVO extends DTO {

	/**
	 * 채널링크
	 */
	private String chLink;
	
	/**
	 * 워크스페이스 링크
	 */
	private String wsLink;
	
	/**
	 * 채널명
	 */
	private String chName;
	
	/**
	 * 주제
	 */
	private String topic;
	
	/**
	 * 설명
	 */
	private String chDescription;
	
	/**
	 * 공개상태
	 */
	private String chAccess;
	
	/**
	 * 작성자
	 */
	private String regId;
	
	/**
	 * 작성시간
	 */
	private String regDt;
	
	public ChannelVO() {
		
	}

	public ChannelVO(String chLink, String wsLink, String chName, String topic, String chDescription, String chAccess,
			String regId, String regDt) {
		super();
		this.chLink = chLink;
		this.wsLink = wsLink;
		this.chName = chName;
		this.topic = topic;
		this.chDescription = chDescription;
		this.chAccess = chAccess;
		this.regId = regId;
		this.regDt = regDt;
	}

	public String getChLink() {
		return chLink;
	}

	public void setChLink(String chLink) {
		this.chLink = chLink;
	}

	public String getWsLink() {
		return wsLink;
	}

	public void setWsLink(String wsLink) {
		this.wsLink = wsLink;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getChDescription() {
		return chDescription;
	}

	public void setChDescription(String chDescription) {
		this.chDescription = chDescription;
	}

	public String getChAccess() {
		return chAccess;
	}

	public void setChAccess(String chAccess) {
		this.chAccess = chAccess;
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
		return "ChannelVO [chLink=" + chLink + ", wsLink=" + wsLink + ", chName=" + chName + ", topic=" + topic
				+ ", chDescription=" + chDescription + ", chAccess=" + chAccess + ", regId=" + regId + ", regDt="
				+ regDt + "]";
	}
	
	
	
	
}
