package com.bighit.on.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bighit.on.mention.MentionDaoImpl;
import com.bighit.on.mention.MentionVO;
import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class MentionTest {
	final Logger LOG = LoggerFactory.getLogger(MentionTest.class);
		
	@Autowired
	MentionDaoImpl mentionDaoImpl;
	
	List<MentionVO> list;
	MentionVO in1;
	MentionVO in2;
	MentionVO in3;
	
	@Before
	public void setUp() throws Exception {
		list = new ArrayList<MentionVO>();
		
		in1= new MentionVO("yeo", "2", "yeo", "");
		in2= new MentionVO("yeo", "1", "kim", "");
		in3= new MentionVO("kim", "2", "yeo", "");
		
		list.add(in1);
		list.add(in2);
		list.add(in3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		for(MentionVO vo : list) mentionDaoImpl.doDelete(vo);
		for(MentionVO vo : list) mentionDaoImpl.doInsert(vo);
		List<MentionVO> tmplist = mentionDaoImpl.doSelectList(in1);
		//해당 쓰레드의 반응 갯수
		assertThat(tmplist.size(), is(2));
		
		
//		UsersVO user = new UsersVO();
//		user.setUser_serial("yeo");
		//해당 유저가 받은 반응 갯수
//		List<ThreadVO> threadlist = mentionDaoImpl.doSelectList(user);
//		assertThat(threadlist.size(), is(2));
	}
	@Test
	public void bean()
	{
		LOG.debug("mentionDaoImpl"+mentionDaoImpl.toString());
	}

}
