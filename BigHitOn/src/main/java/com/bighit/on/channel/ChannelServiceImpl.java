package com.bighit.on.channel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ChannelServiceImpl")
public class ChannelServiceImpl implements ChannelService {
	
	@Autowired
	private ChannelDaoImpl channelDao;
	
	@Override
	public int doInsert(ChannelVO channelVO) {
		return channelDao.doInsert(channelVO);
	}

	@Override
	public int doDelete(ChannelVO channelVO) {
		return channelDao.doDelete(channelVO);
	}

	@Override
	public ChannelVO doSelectOne(ChannelVO channel) {
		return channelDao.doSelectOne(channel);
	}

	@Override
	public List<ChannelVO> doSelectList(ChannelVO channelVO) {
		return channelDao.doSelectList(channelVO);
	}

}
