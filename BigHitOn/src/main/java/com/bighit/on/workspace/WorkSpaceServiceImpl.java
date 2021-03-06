package com.bighit.on.workspace;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.bighit.on.channel.ChannelDaoImpl;
import com.bighit.on.channel.ChannelVO;
import com.bighit.on.channelusers.ChannelUsersDao;
import com.bighit.on.cmn.Message;
import com.bighit.on.email.EmailVO;
import com.bighit.on.user.dao.UsersVO;

@Service("WorkSpaceServiceImpl")
public class WorkSpaceServiceImpl implements WorkSpaceService {
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("mailSenderImpl")
	private MailSender  mailSender;
	
	@Autowired
	private WorkSpaceDaoImpl workSpaceDao;
	@Autowired
	private ChannelDaoImpl channelDaoImpl; 
	@Autowired
	private ChannelUsersDao chUserDao;
	
	/**
	 * 워크스페이스링크 중복 체크
	 */
	@Override
	public int workSpaceLinkCK(WorkSpaceVO workSpaceVO) {
		WorkSpaceVO workSpaceCheck = workSpaceDao.doSelectOne(workSpaceVO);
		LOG.debug("workSpaceCheck.getWsLink():"+workSpaceCheck.getWsLink());
		if(null != workSpaceCheck.getWsLink()) {
			LOG.debug("중복되는 workspace입니다");
			return 1;
		}
		else {
			LOG.debug("중복되지않는 workspace입니다");
			return 0;
		}
	}
	
	@Override
	public int workSpaceNameCK(WorkSpaceVO workSpaceVO) {
		if(workSpaceVO.getWsName().length()<=8) {
			LOG.debug("workSpaceVO.getWsName().length():"+workSpaceVO.getWsName().length());
			LOG.debug("워크스페이스 이름이 짧습니다. 다시 지어주세요");
			
			return 1;
		}
		LOG.debug("workSpaceVO.getWsName().length():"+workSpaceVO.getWsName().length());
		LOG.debug("이름 생성 완료");
		return 0;
	}
	
	@Override
	public int workSpacePCK(WorkSpaceVO workSpaceVO) {
		
		if(workSpaceVO.getProject().length()<=10) {
			LOG.debug("workSpaceVO.getProject().length():"+workSpaceVO.getProject().length());
			LOG.debug("프로젝트 이름이 짧습니다. 다시 지어주세요");
			
			return 1;
		}
		LOG.debug("workSpaceVO.getWsName().length():"+workSpaceVO.getWsName().length());
		LOG.debug("프로젝트 생성 완료");
		return 0;
	}
	
	@Override
	public Message doInsert(WorkSpaceVO workSpaceVO) {
		Message res = new Message();
		int flag = 1 ;
		
		String genChLink = channelDaoImpl.doGetKey();
		flag &= workSpaceDao.doInsert(workSpaceVO);
		
		res.setMsgContents(genChLink);
		
		res.setRegId(flag+"");
		
		return res;
		
	}
	
	@Override
	public int doDelete(WorkSpaceVO workSpaceVO) {
		return workSpaceDao.doDelete(workSpaceVO);
	}

	@Override
	public WorkSpaceVO doSelectOne(WorkSpaceVO wsLink) {
		return workSpaceDao.doSelectOne(wsLink);
	}

	@Override
	public List<WorkSpaceVO> doSelectList(UsersVO usersVO) {
		return workSpaceDao.doSelectList(usersVO);
	}

	/**
	 * 초대 이메일로 보내기
	 */
	@Override
	public void sendEmail(EmailVO emailVO) {
		String from = "gustn4880@naver.com";
		String title = emailVO.getUserId()+"님께서"+emailVO.getWsName()+"에 초대했습니다.";//제목
		String contents = emailVO.getUserId()+"님께서"+emailVO.getWsName()+"에 초대했습니다.";//내용
		String recAddr = emailVO.getEmail();//받는사람
		String link = "http://localhost:8080/on/users/signUpView.do?ws_link="+emailVO.getWsLink() +"&email="+emailVO.getEmail();//워크스페이스링크
		
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
		LOG.debug("mimeMessage:"+mimeMessage);
		//전송
		this.mailSender.send(mimeMessage);
	}
}
