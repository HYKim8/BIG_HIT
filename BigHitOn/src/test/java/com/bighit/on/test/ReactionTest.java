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

import com.bighit.on.reaction.ReactionDaoImpl;
import com.bighit.on.reaction.ReactionVO;
import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class ReactionTest {
	final Logger LOG = LoggerFactory.getLogger(ReactionTest.class);
		
	@Autowired
	ReactionDaoImpl reactionDaoImpl;
	
	List<ReactionVO> list;
	ReactionVO in1;
	ReactionVO in2;
	ReactionVO in3;
	
	@Before
	public void setUp() throws Exception {
		list = new ArrayList<ReactionVO>();
		
		in1= new ReactionVO(1, "yeo", "1", "yeo", "");
		in1= new ReactionVO(2, "yeo", "1", "yeo", "");
		in1= new ReactionVO(3, "yeo", "1", "yeo", "");
		list.add(in1);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		for(ReactionVO vo : list) reactionDaoImpl.doDelete(vo);
		for(ReactionVO vo : list) reactionDaoImpl.doInsert(vo);
		List<ReactionVO> tmplist = reactionDaoImpl.doSelectList(in1);
		//해당 쓰레드의 반응 갯수
		
		assertThat(tmplist.size(), is(3));
		
		assertThat(reactionDaoImpl.doCheck(in1),is(1));
//		UsersVO user = new UsersVO();
//		user.setUser_serial("yeo");
//		//해당 유저가 받은 반응 갯수
//		List<ThreadVO> threadlist = reactionDaoImpl.doSelectList(user);
//		assertThat(threadlist.size(), is(1));
	}
	@Test
	public void bean()
	{
		LOG.debug("reactionDaoImpl"+reactionDaoImpl.toString());
	}

}
