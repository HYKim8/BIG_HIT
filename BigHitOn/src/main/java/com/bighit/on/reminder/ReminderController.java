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

import com.google.gson.Gson;

@Controller
public class ReminderController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ReminderService reminderService;
	
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
	
	
	
	@RequestMapping(value = "reminder/doSelectList.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String doSelectList(ReminderVO reminderVO, Model model) {
		LOG.debug("-------------------------");
		LOG.debug("-reminder/doSelectList.do-");
		LOG.debug("-------------------------");
		
		List<ReminderVO> list = this.reminderService.doSelectList(reminderVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		LOG.debug("-------------------------");
		LOG.debug("-json-"+json);
		LOG.debug("-------------------------");
		
		return json;
	}
	
	
	
	
}
