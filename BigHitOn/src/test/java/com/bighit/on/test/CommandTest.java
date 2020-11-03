package com.bighit.on.test;

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
	
	
	CommandVO command01;
	CommandVO command02;
	
	ComChLinkVO comChLink01;
	ComChLinkVO comChLink02;
	
	
	@Before
	public void setUp() throws Exception {
		command01 = new CommandVO();
		command02 = new CommandVO();
		
		comChLink01 = new ComChLinkVO();
		comChLink02 = new ComChLinkVO();
		
		command01.setComId(4);
		command01.setCmdName("test");
		command01.setAppName("test");
		command01.setParamCnt(1);
		
		command02.setComId(2);
		command02.setCmdName("test02");
		command02.setAppName("test02");
		command02.setParamCnt(1);
		
		//comChLink01.setComId(1);
		//comChLink01.setCmdName("test");
		//comChLink01.setAppName("test");
		comChLink01.setChLink(1);
		//comChLink01.setParamCnt(1);
		
		//comChLink02.setComId(1);
		//comChLink02.setCmdName("test");
		//comChLink02.setAppName("test");
		comChLink02.setChLink(1);
		//comChLink02.setParamCnt(1);
		
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
	@Ignore
	public void doSelectOne() {
		commandDao.doSelectOne(1);
	}
	
	@Test
	@Ignore
	public void doSelectListChLink() {
		commandDao.doSelectListChLink(comChLink02);
	}
	@Test
	@Ignore
	public void doSelectList() {
		commandDao.doSelectList(command01);
	}
	
	@Test
	public void all() {
		commandDao.doDelete(command01);
		commandDao.doDelete(command02);
		
		commandDao.doInsert(command01);
		commandDao.doInsert(command02);
		
		List<CommandVO> list = commandDao.doSelectList(command01);
		
		
		commandDao.doSelectOne(4);
		
		
		command01.setCmdName("changeTest02");
		commandDao.doUpdate(command01);
	}

}
