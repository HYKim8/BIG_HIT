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

import com.bighit.on.channel.ChannelVO;
import com.bighit.on.channelusers.ChannelUsersDao;
import com.bighit.on.channelusers.ChannelUsersVO;
import com.bighit.on.user.dao.UsersDaoImpl;
import com.bighit.on.user.dao.UsersVO;
import com.bighit.on.workspace.WorkSpaceVO;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class JUnitTestUsers {
	Logger LOG = LoggerFactory.getLogger(JUnitTestUsers.class);
	
	@Autowired // 테스트 컨텍스트 프레임워크는 일치하는 컨텍스트를 찾아 DI해 준다.
	WebApplicationContext context;

	@Autowired
	UsersDaoImpl usersDaoImpl;
	@Autowired
	ChannelUsersDao channelUserDao;
	
	UsersVO users01;
	UsersVO users02;
	UsersVO users03;
	
	ChannelVO channelVO;
	ChannelUsersVO cu1;
	ChannelUsersVO cu2;
	ChannelUsersVO cu3;
	
	WorkSpaceVO workSpaceVO;
	
	/** workspace에 포함된 users list */
	@Test
//	@Ignore
	public void doSelectList2() {
		usersDaoImpl.doSelectList2(workSpaceVO);
	}
	
	/** channel에 소속된 users list */
	@Test
	@Ignore
	public void doSelectList() {
		usersDaoImpl.doSelectList(channelVO);
	}
	
	
	@Test
	@Ignore
	public void doUpdate() throws Exception{
		usersDaoImpl.doDelete(users01);
//		usersDaoImpl.doDelete(users02);
//		usersDaoImpl.doDelete(users03);
				
		int flag = usersDaoImpl.doInsert(users01);
		assertThat(1, is(1));
		
		users01.setName(users01.getName()+"_U");
		users01.setNickname(users01.getNickname()+"바보");
		LOG.debug("================");
		LOG.debug("=users01="+users01);
		LOG.debug("================");
		
		usersDaoImpl.doUpdate(users01);

	}
	

	@Before
	public void setUp() throws Exception {
		users01=new UsersVO("1111", "1", "bighit@naver.com", "1234", "김영은", "JOY", "", "", "01012345678", 12, 1, 1, "", "", "");
    	users02=new UsersVO("2222", "1", "bighit@naver.com", "1234", "김영은", "JOY", "", "", "01012345678", 12, 1, 1, "", "", "");
    	users03=new UsersVO("3333", "1", "bighit@naver.com", "1234", "김영은", "JOY", "", "", "01012345678", 12, 1, 1, "", "", "");
    	channelVO = new ChannelVO();
    	channelVO.setChLink("1");
    	workSpaceVO = new WorkSpaceVO();
    	workSpaceVO.setWsLink("1");
    	
    	cu1 = new ChannelUsersVO(channelVO.getChLink(),users01.getUser_serial(),0);
    	cu2 = new ChannelUsersVO(channelVO.getChLink(),users02.getUser_serial(),0);
    	cu3 = new ChannelUsersVO(channelVO.getChLink(),users03.getUser_serial(),0);
    	LOG.debug("** setup() **");
    	LOG.debug("***************************************");
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("** tearDown() **");
		LOG.debug("***************************************");
	}

	@Test
//	@Ignore
	public void test() {
//		channelUserDao.doDeleteAll(channelVO);		
		
//		usersDaoImpl.doDelete(users01);
//		usersDaoImpl.doDelete(users02);
//		usersDaoImpl.doDelete(users03);
		
		usersDaoImpl.doInsert(users01);
		usersDaoImpl.doInsert(users02);
		usersDaoImpl.doInsert(users03);
		
//		channelUserDao.doInsert(cu1);
//		channelUserDao.doInsert(cu2);
//		channelUserDao.doInsert(cu3);	
//		
//		List<UsersVO> list = usersDaoImpl.doSelectList(channelVO);
//		assertThat(list.size(), is(3));
		
		LOG.debug("============================");
		LOG.debug("test doInsert 성공 ");
		
		
	}

}
