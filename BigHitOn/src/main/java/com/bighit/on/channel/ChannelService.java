package com.bighit.on.channel;

import java.util.List;

import com.bighit.on.channelusers.ChannelUsersVO;
import com.bighit.on.email.EmailVO;

public interface ChannelService {
	
	public int doInsert(ChannelVO channelVO);
	
	public int doDelete(ChannelVO channelVO);
	
	public ChannelVO doSelectOne(ChannelVO channel);
	
	public List<ChannelVO> doSelectList(ChannelVO channelVO);
	
	public int getOut(ChannelUsersVO inVO);
	
	public int notifiUpdate(ChannelUsersVO inVO);
	
}
