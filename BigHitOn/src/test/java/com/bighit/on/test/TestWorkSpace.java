package com.bighit.on.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

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

import com.bighit.on.workspace.WorkSpaceDaoImpl;
import com.bighit.on.workspace.WorkSpaceVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class TestWorkSpace {
	Logger  LOG = LoggerFactory.getLogger(TestWorkSpace.class);
	
	@Autowired // 테스트 컨텍스트 프레임워크는 일치하는 컨텍스트를 찾아 DI해 준다.
	WebApplicationContext context;
	
	@Autowired
	WorkSpaceDaoImpl workSpaceDao;
	
	WorkSpaceVO workSpace01;
	WorkSpaceVO workSpace02;
	
	@Before
	public void setUp() throws Exception {
		workSpace01 = new WorkSpaceVO("2","jhs_ws","bighit","jhs","");
		workSpace02 = new WorkSpaceVO("3","jhs_ws","bighit","jhs","");
	
		LOG.debug("** setup() **");
		LOG.debug("** workSpace01 param **:"+workSpace01);
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("** tearDown() **");
	}

	@Test
	@Ignore
	public void all() {
		LOG.debug("** test **");
		//워크스페이스삭제T
		workSpaceDao.doDelete(workSpace01);
		workSpaceDao.doDelete(workSpace02);
		
		//워크스페이스 생성T
		workSpaceDao.doInsert(workSpace01);
		workSpaceDao.doInsert(workSpace02);
		
		//워크스페이스 단건조회
		workSpaceDao.doSelectOne(workSpace01);
		workSpaceDao.doSelectOne(workSpace02);
	}
	
	//성공
	@Test
	@Ignore
	public void doInsert() {
		int flag = workSpaceDao.doInsert(workSpace01);
		assertThat(flag, is(1));
	}
	
	//성공
	@Test
	@Ignore
	public void doDelete() {
		int flag = workSpaceDao.doDelete(workSpace01);
		assertThat(flag, is(1));
	}
	
	//성공
	@Test
	public void doSelectOne() {
		workSpaceDao.doSelectOne(workSpace01);
		
	}
	
}
