package com.bighit.on.channelcommmand;

import com.bighit.on.cmn.DTO;

public class ChannelCommandVO extends DTO {
	/**
	 * 	CH_LINK 의 doInsert, doDelete 만들기

		만들때
		com_id 는 int
		ch_link는 string로 주면된다.
	 */
	//
	private int comId;
	
	private String chLink;

	public ChannelCommandVO() {
	
	}

	public ChannelCommandVO(int comId, String chLink) {
		super();
		this.comId = comId;
		this.chLink = chLink;
	}

	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getChLink() {
		return chLink;
	}

	public void setChLink(String chLink) {
		this.chLink = chLink;
	}

	@Override
	public String toString() {
		return "ChannelLinkVO [comId=" + comId + ", chLink=" + chLink + ", toString()=" + super.toString() + "]";
	}
	
	
}
