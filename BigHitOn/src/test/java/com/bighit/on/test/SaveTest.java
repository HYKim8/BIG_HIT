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

import com.bighit.on.save.SaveThrDaoImpl;
import com.bighit.on.save.SaveThrVO;
import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class SaveTest {
	final Logger LOG = LoggerFactory.getLogger(SaveTest.class);
		
	@Autowired
	SaveThrDaoImpl saveThDaoImpl;
	
	List<SaveThrVO> list;
	SaveThrVO in1;
	SaveThrVO in2;
	
	@Before
	public void setUp() throws Exception {
		list = new ArrayList<SaveThrVO>();
		
		in1= new SaveThrVO("2", "yeo", "");
		in2= new SaveThrVO("1", "yeo", "");
		
		list.add(in1);
		list.add(in2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		for(SaveThrVO vo : list) saveThDaoImpl.doDelete(vo);
		for(SaveThrVO vo : list) saveThDaoImpl.doInsert(vo);
		List<SaveThrVO> tmplist = saveThDaoImpl.doSelectList(in1);
		//해당 쓰레드의 반응 갯수
		assertThat(tmplist.size(), is(2));
		assertThat(saveThDaoImpl.doCheck(in1),is(true));
		UsersVO user = new UsersVO();
		user.setUser_serial("yeo");
		user.setReg_id("yeo");
		List<ThreadVO> li = saveThDaoImpl.doSelectList(user);
		
		for(ThreadVO vo : li) LOG.debug(vo.toString());
	}
	@Test
	public void bean()
	{
		LOG.debug("saveThDaoImpl"+saveThDaoImpl.toString());
	}

}
