package com.bighit.on.reminder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReminderService {

	@Autowired
	ReminderDaoImpl reminderDao;
	
	public int doInsert(ReminderVO reminderVO) {
		return reminderDao.doInsert(reminderVO);
	}
	
	public int doDelete(ReminderVO reminderVO) {
		return reminderDao.doDelete(reminderVO);
	}
	
	public int doUpdate(ReminderVO reminderVO) {
		return reminderDao.doUpdateTest(reminderVO);
	}
	
	public ReminderVO doSelectOne(ReminderVO reminderVO) {
		return (ReminderVO) reminderDao.doSelectOne(reminderVO);
	}
	
	public List<ReminderVO> doSelectList(ReminderVO reminderVO){
		return reminderDao.doSelectList(reminderVO);
	}
	
}
