package com.bighit.on.reminder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bighit.on.user.dao.UsersVO;
import com.google.gson.Gson;

@Controller
public class ReminderController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ReminderService reminderService;
	@RequestMapping(value = "reminder/doInsertFinal.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsertReminder(HttpServletRequest req, ReminderVO reminderVO, String date, String time) {
		LOG.debug("-------------------------");
		LOG.debug("-reminder/doInsertFinal.do-");
		LOG.debug("-------------------------");
		
		String remindTime = date + " " + time;
		
		HttpSession session = req.getSession();
		UsersVO usersVO = new UsersVO();
		usersVO = (UsersVO) session.getAttribute("usersVO");
		LOG.debug("usersVO : " + usersVO);
		
		// thrKey ajax로 받아야함.
		
		reminderVO.setRegId(usersVO.getNickname());
		reminderVO.setWsLink(usersVO.getWs_link());
		reminderVO.setRemindTime(remindTime);
		
		LOG.debug("reminderVO : " + reminderVO);
		
		reminderService.doInsert(reminderVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(reminderVO);
		
		return json;
	}
	
	@RequestMapping(value = "reminder/reminder_view.do", method = RequestMethod.GET)
	public ModelAndView reminder_view(HttpServletRequest req) {
		LOG.debug("-------------------------");
		LOG.debug("-reminder_view.do-");
		LOG.debug("-------------------------");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reminder/reminder");
		
		return mav;
	}
	
	@RequestMapping(value = "reminder/doDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public String doDelete(ReminderVO reminderVO) {
		LOG.debug("-------------------------");
		LOG.debug("-reminder/doDelete.do-");
		LOG.debug("-------------------------");
		
		int flag = reminderService.doDelete(reminderVO);
		
		LOG.debug("-------------------------");
		LOG.debug("-doDelete-Controller-");
		LOG.debug("-flag-" + flag);
		LOG.debug("-------------------------");
		
		
		return "reminder/reminder";
	}
	
	
	
	
	
}
