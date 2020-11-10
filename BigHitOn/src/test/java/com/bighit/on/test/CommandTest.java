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

import com.bighit.on.channelcommand.ChannelCommandDao;
import com.bighit.on.channelcommand.ChannelCommandVO;
import com.bighit.on.command.ComChLinkVO;
import com.bighit.on.command.CommandDao;
import com.bighit.on.command.CommandVO;
import com.bighit.on.thread.ThreadDao;
import com.bighit.on.thread.ThreadVO;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class CommandTest {
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	CommandDao commandDao;
	
	@Autowired
	ChannelCommandDao channelCommandDao; 
	
	CommandVO command01;
	CommandVO command02;
	

	
	ChannelCommandVO ccv1;
	ChannelCommandVO ccv2;
	
	@Before
	public void setUp() throws Exception {
		command01 = new CommandVO();
		command02 = new CommandVO();		
		
		command01 = new CommandVO(12,"tests","testt",11);
		command02 = new CommandVO(13,"t1ests","tes1tt",10);
	
		ccv1 = new ChannelCommandVO(command01.getComId(),"1");
		ccv2 = new ChannelCommandVO(command02.getComId(),"1");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	@Ignore
	public void doDelete() {
		commandDao.doDelete(command01);
	}
	
	@Test
	@Ignore
	public void doInsert() {
		commandDao.doInsert(command01);
	}
	
	@Test
	@Ignore
	public void doUpdate() {
		command01.setCmdName("changeTest");
		
		LOG.debug("=============");
		LOG.debug("=doUpdate="+command01);
		LOG.debug("=============");
		
		commandDao.doUpdate(command01);
	}
	
	@Test
	
	public void doSelectOne() {
		commandDao.doSelectOne(command01);
	}
	
//	@Test
//	@Ignore
//	public void doSelectListChLink() {
//		commandDao.doSelectListChLink(comChLink02);
//	}
	@Test
	@Ignore
	public void doSelectList() {
		commandDao.doSelectList(command01);
	}
	
	@Test
	@Ignore
	public void all() {
		channelCommandDao.doDeleteAll(command01);
		channelCommandDao.doDeleteAll(command02);
		
		commandDao.doDelete(command01);
		commandDao.doDelete(command02);
		
		commandDao.doInsert(command01);
		commandDao.doInsert(command02);		
		
		
		channelCommandDao.doInsert(ccv1);
		channelCommandDao.doInsert(ccv2);
		ComChLinkVO link = new ComChLinkVO();
		link.setChLink("1");
		
		List<ComChLinkVO> listFromChannel = commandDao.doSelectListChLink(link);
		assertThat(listFromChannel.size(), is(2));
		
		List<CommandVO> list = commandDao.doSelectList(command01);
		
		
		commandDao.doSelectOne(command01);
		
		
		command01.setCmdName("changeTest02");
		commandDao.doUpdate(command01);
	}

}
