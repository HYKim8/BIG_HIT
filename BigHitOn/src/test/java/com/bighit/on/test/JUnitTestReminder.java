package com.bighit.on.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sdftime = sdf.format(System.currentTimeMillis());
		reminder01.setRemindTime(sdftime);
		reminder01.setThrKey("1");
		
		LOG.debug("In setUp Param" + reminder01);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		LOG.debug("In test Param" + reminder01);
		reminderDao.doInsert(reminder01);
		
	}

}
