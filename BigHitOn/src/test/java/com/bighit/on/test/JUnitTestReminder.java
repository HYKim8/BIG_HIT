package com.bighit.on.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

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

import com.bighit.on.reminder.ReminderDaoImpl;
import com.bighit.on.reminder.ReminderVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class JUnitTestReminder {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired // 테스트 컨텍스트 프레임워크는 일치하는 컨텍스트를 찾아 DI해 준다.
	WebApplicationContext context;

	@Autowired
	ReminderDaoImpl reminderDao;
	
	ReminderVO reminder01;

	@Before
	public void setUp() throws Exception {
		reminder01 = new ReminderVO();
		reminder01.setRegId("KIM");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String sdftime = sdf.format(System.currentTimeMillis());
		String time = "1992/04/20 19:12";
		reminder01.setRemindTime(time);
		
		
		reminder01.setThrKey("1");
		LOG.debug("---------------------------");
		LOG.debug("-In setUp Param-" + reminder01);
		LOG.debug("---------------------------");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
//	@Ignore
	public void doUpdate() {
		
		
		
		String time = "2020/04/20 19:12";
		reminder01.setRemindTime(time);
		reminder01.setRemindId("30");
		
		LOG.debug("---------------------------");
		LOG.debug("-In doUpdate test Param-" + reminder01);
		LOG.debug("---------------------------");
		reminderDao.doUpdate(reminder01);
	}
	
	
	@Test
	@Ignore
	public void doInsert() {
		LOG.debug("---------------------------");
		LOG.debug("-In doInsert test Param-" + reminder01);
		LOG.debug("---------------------------");
		reminderDao.doInsert(reminder01);
	}

}
