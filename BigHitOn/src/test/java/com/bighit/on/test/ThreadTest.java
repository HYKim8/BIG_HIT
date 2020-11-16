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

import com.bighit.on.cmn.Search;
import com.bighit.on.thread.ThreadDao;
import com.bighit.on.thread.ThreadService;
import com.bighit.on.thread.ThreadVO;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class ThreadTest {
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
	ThreadDao threadDao;
	
	@Autowired
	ThreadService threadService;
	
	ThreadVO thread01;
	ThreadVO thread02;
	
	
	@Before
	public void setUp() throws Exception {
		thread01 = new ThreadVO();
		thread02 = new ThreadVO();

		thread01.setThrKey("test1");
		thread01.setChLink("12");
		thread01.setContents("test");
		thread01.setIsPin(1);
		thread01.setParentKey("");
		thread01.setPinId("abc");
		thread01.setRegId("abc");
		
		thread02.setThrKey("2");
		thread02.setChLink("123");
		
		
		//thread02.setContents("test");
		//thread02.setIsPin(2);
		//thread02.setParentKey("2");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Ignore
	public void doSelectAll() {
		threadDao.doSelectAll(thread01);
	}
	//@Test
	//@Ignore
	//public void doSelectOne() {
	//	threadDao.doSelectOne("test");
	//}
	@Test
	@Ignore
	public void doInsert() {
		//threadDao.doInsert(thread02);
		threadService.doInsert(thread02);
	}
	@Test
	@Ignore
	public void doUpdate() {
		thread01.setContents("abw");
		LOG.debug("=============");
		LOG.debug("=doUpdate="+thread01);
		LOG.debug("=============");
		threadDao.doUpdate(thread01);
	}
	@Test
	@Ignore
	public void doDelete() {
		//threadDao.doDelete(thread02);
		threadService.doDelete(thread02);
	}
	
	@Test
//	@Ignore
	public void all() {
//		threadDao.doDelete(thread01);
		for(int i=0;i<1000;i++)
		threadDao.doInsert(thread01);
		
//		List<ThreadVO> list = threadDao.doSelectAll(thread01);
		
//		threadDao.doSelectOne(thread01);
		
//		thread01.setContents("changeTest");
//		threadDao.doUpdate(thread01);
	}
	@Test
//	@Ignore
	public void chList() {
		Search search = new Search("","12");
		threadDao.doSelectList(search);
		
	}

}
