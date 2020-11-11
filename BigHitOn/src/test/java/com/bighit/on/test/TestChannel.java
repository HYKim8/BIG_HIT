package com.bighit.on.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.bighit.on.channel.ChannelDaoImpl;
import com.bighit.on.channel.ChannelService;
import com.bighit.on.channel.ChannelVO;
import com.bighit.on.channel.EmailVO;
import com.bighit.on.channelcommand.ChannelCommandDao;
import com.bighit.on.channelusers.ChannelUsersDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class TestChannel {
	Logger LOG = LoggerFactory.getLogger(TestChannel.class);

	@Autowired // 테스트 컨텍스트 프레임워크는 일치하는 컨텍스트를 찾아 DI해 준다.
	WebApplicationContext context;

	@Autowired
	ChannelDaoImpl channelDao;
	@Autowired
	ChannelUsersDao channelUsersDao;
	@Autowired
	ChannelCommandDao channelCommandDao;
	
	@Autowired
	ChannelService channelService;
	
	ChannelVO channel01;
	ChannelVO channel02;
	
	EmailVO email01;
	
	@Before
	public void setUp() throws Exception {
		channel01 = new ChannelVO();
		channel02 = new ChannelVO();
		
		LOG.debug("** setup() **");

		channel01.setChLink("12");
		channel01.setWsLink("1");
		channel01.setChName("testCh1");
		channel01.setTopic("=testch1test=");
		channel01.setChDescription("testspace");
		channel01.setChAccess("1");
		channel01.setRegId("jhs");
		channel01.setRegDt("");

		channel02.setChLink("123");
		channel02.setWsLink("1");
		channel02.setChName("testCh1_U");
		channel02.setTopic("=testch1test_U=");
		channel02.setChDescription("testspace_U");
		channel02.setChAccess("1");
		channel02.setRegId("jhs_U");
		channel02.setRegDt("");

		LOG.debug("channel01 param:" + channel01);
		
		email01 = new EmailVO("isc8481@hanmail.net", channel01.getWsLink(), channel01.getChName(), "slack");
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("** tearDown() **");
	}

	// 성공
	@Test
	@Ignore
	public void doSelectOne() {
		channelDao.doSelectOne(channel01);
		channelDao.doSelectOne(channel02);
	}

	// 성공
	@Test
	@Ignore
	public void doDelete() {
		channelDao.doDelete(channel01);
		channelDao.doDelete(channel02);
	}

	// 성공
	@Test
	@Ignore
	public void doInsert() {
		LOG.debug("** test **");
		channelDao.doInsert(channel01);
		channelDao.doInsert(channel02);
	}

	// 성공
	@Test
	@Ignore
	public void doSelectList() {
//		ChannelVO cntChnnel = new ChannelVO();
//		cntChnnel.setWsLink("1");
//		List<ChannelVO> list = channelDao.doSelectList(cntChnnel);
//		assertThat(list.size(), is(0));
		channelDao.doSelectList(channel01);
	}

	@Test
	@Ignore
	public void all() {
//		channelCommandDao.doDeleteAll(channel01);
//		channelCommandDao.doDeleteAll(channel02);
//		
//		channelUsersDao.doDeleteAll(channel01);
//		channelUsersDao.doDeleteAll(channel02);
		// 채널삭제T
		channelDao.doDelete(channel01);
		channelDao.doDelete(channel02);

//		//채널 생성T
//		channelDao.doInsert(channel01);
//		channelDao.doInsert(channel02);
//		
//		
//		//List
//		List<ChannelVO> list = channelDao.doSelectList(channel01);
//
//		//단건조회
//		channelDao.doSelectOne(channel01);
//		channelDao.doSelectOne(channel02);

	}
	
	//성공
	@Test
	public void sendEmail() {
		channelService.sendEmail(email01);
	}

}
