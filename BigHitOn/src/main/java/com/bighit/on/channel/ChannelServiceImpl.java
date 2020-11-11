package com.bighit.on.channel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

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
	
	@Override
	public void sendEmail(EmailVO emailVO) {
		String from = "gustn4880@naver.com";
		String title = emailVO.getUserId()+"님께서"+emailVO.getWsName()+"에 초대했습니다.";//제목
		String contents = emailVO.getUserId()+"님께서"+emailVO.getWsName()+"에 초대했습니다.";//내용
		String recAddr = emailVO.getEmail();//받는사람
		String link = emailVO.getWsLink();//워크스페이스링크
		
		SimpleMailMessage  mimeMessage=new SimpleMailMessage();
		
		//from
		mimeMessage.setFrom(from);
		//to
		mimeMessage.setTo(recAddr);
		//title
		mimeMessage.setSubject(title);
		//내용
		mimeMessage.setText(contents);
		//링크
		mimeMessage.setText(link);
		
		LOG.debug("mailSender:"+mailSender);
		//전송
		this.mailSender.send(mimeMessage);
	}
}
