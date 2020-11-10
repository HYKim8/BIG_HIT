package com.bighit.on.test;

import java.text.ParseException;
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

import com.bighit.on.reminder.ReminderDaoImpl;
import com.bighit.on.reminder.ReminderService;
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
	
	@Autowired
	ReminderService reminderService;
	
	ReminderVO reminder01;
	ReminderVO reminder02;
	ReminderVO reminder03;

	@Before
	public void setUp() throws Exception {
		reminder01 = new ReminderVO();
		reminder02 = new ReminderVO();
		reminder03 = new ReminderVO();
				
		String time01 = "2020/11/10 21:49";
		String time02 = "1994/04/20 19:12";
		String time03 = "1995/04/20 19:12";
				
		reminder01.setRegId("KIM");
		reminder01.setRemindTime(time01);
		reminder01.setThrKey("1");
		reminder02.setRegId("KIM");
		reminder02.setRemindTime(time02);
		reminder02.setThrKey("1");
		reminder03.setRegId("KIM");
		reminder03.setRemindTime(time03);
		reminder03.setThrKey("1");
				
		LOG.debug("---------------------------");
		LOG.debug("-In setUp Param-" + reminder01);
		LOG.debug("-In setUp Param-" + reminder02);
		LOG.debug("-In setUp Param-" + reminder03);
		LOG.debug("---------------------------");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void inputZone() {
		reminderService.doInsert(reminder01);
	}
	
	@Test
	public void serviceTest() {
		reminderService.doInsert(reminder01);
		reminderService.doInsert(reminder01);
		reminderService.doInsert(reminder01);
		
		List<ReminderVO> list = reminderService.doSelectList(reminder01);
		
		String remindId01 = list.get(0).getRemindId();
		String remindId02 = list.get(1).getRemindId();
		String remindId03 = list.get(2).getRemindId();
		
		reminder01.setRemindId(remindId01);
		reminder02.setRemindId(remindId02);
		reminder03.setRemindId(remindId03);
		
		reminderService.doSelectOne(reminder01);
		reminderService.doSelectOne(reminder02);
		reminderService.doSelectOne(reminder03);
		
		String time01 = "2002/04/20 19:12";
		String time02 = "2004/04/20 19:12";
		String time03 = "2005/04/20 19:12";
		
		reminder01.setRemindTime(time01);
		reminder02.setRemindTime(time02);
		reminder03.setRemindTime(time03);
		
		reminderService.doUpdate(reminder01);
		reminderService.doUpdate(reminder02);
		reminderService.doUpdate(reminder03);
		
		reminderService.doDelete(reminder01);
		reminderService.doDelete(reminder02);
		reminderService.doDelete(reminder03);
	}
	
	@Test
	@Ignore
	public void doSelectList() {
		reminderDao.doSelectList(reminder01);
	}
	
	@Test
	public void daoTest() {
		reminderDao.doInsert(reminder01);
		reminderDao.doInsert(reminder02);
		reminderDao.doInsert(reminder03);
		
		List<ReminderVO> list = reminderDao.doSelectList(reminder01);
		
		String remindId01 = list.get(0).getRemindId();
		String remindId02 = list.get(1).getRemindId();
		String remindId03 = list.get(2).getRemindId();
		
		reminder01.setRemindId(remindId01);
		reminder02.setRemindId(remindId02);
		reminder03.setRemindId(remindId03);
		
		reminderDao.doSelectOne(reminder01);
		reminderDao.doSelectOne(reminder02);
		reminderDao.doSelectOne(reminder03);
		
		String time01 = "2002/04/20 19:12";
		String time02 = "2004/04/20 19:12";
		String time03 = "2005/04/20 19:12";
		
		reminder01.setRemindTime(time01);
		reminder02.setRemindTime(time02);
		reminder03.setRemindTime(time03);
		
		reminderDao.doUpdateTest(reminder01);
		reminderDao.doUpdateTest(reminder02);
		reminderDao.doUpdateTest(reminder03);
		
		reminderDao.doDelete(reminder01);
		reminderDao.doDelete(reminder02);
		reminderDao.doDelete(reminder03);
	}
	
	
	
	@Test
	@Ignore
	public void doUpdate() {
		
		
		
		String time = "2020/04/20 19:12";
		reminder01.setRemindTime(time);
		reminder01.setRemindId("27");
		
		LOG.debug("---------------------------");
		LOG.debug("-In doUpdate test Param-" + reminder01);
		LOG.debug("---------------------------");
		reminderDao.doUpdateTest(reminder01);
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
