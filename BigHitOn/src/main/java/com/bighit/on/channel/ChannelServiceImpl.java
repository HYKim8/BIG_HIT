package com.bighit.on.channel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.bighit.on.email.EmailVO;
import com.bighit.on.user.dao.UsersVO;

@Service("ChannelServiceImpl")
public class ChannelServiceImpl implements ChannelService {
	final Logger LOG = LoggerFactory.getLogger(ChannelServiceImpl.class);
	
	@Autowired
	@Qualifier("mailSenderImpl")
	private MailSender  mailSender;
	
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
