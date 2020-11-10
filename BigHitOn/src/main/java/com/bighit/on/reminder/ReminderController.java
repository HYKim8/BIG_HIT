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
		
		ReminderVO reminderVO = new ReminderVO();
		reminderVO.setThrKey("1");
		List<ReminderVO> reminderList = reminderService.doSelectList(reminderVO);
		HttpSession session = req.getSession();
			
		session.setAttribute("reminderList", reminderList);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reminder/reminder");
		mav.addObject(reminderList);
		
		return mav;
	}
	
	@RequestMapping(value = "reminder/doInsert.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView doInsert() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reminder/reminder");
		
		return mav;
	}
	
	@RequestMapping(value = "reminder/doChkAlarm.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ModelAndView doChkAlarm(HttpServletRequest req) {
		LOG.debug("-------------------------");
		LOG.debug("-reminder/doChkAlarm.do-");
		LOG.debug("-------------------------");
		
		HttpSession session = req.getSession();
		List<ReminderVO> list = (List<ReminderVO>) session.getAttribute("reminderList");
		
		for(int i = 0; i < list.size(); i++) {
			LOG.debug("outVO"+list.get(i));
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reminder/reminder");
		
		
		return mav;
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
	
	
	@RequestMapping(value = "reminder/doInsert.do", method = RequestMethod.POST)
	@ResponseBody
	public String doInsert(ReminderVO reminderVO) {
		
		LOG.debug("-------------------------");
		LOG.debug("-doInsert-");
		LOG.debug("-reminderVO-" + reminderVO);
		LOG.debug("-------------------------");
		
		int flag = reminderService.doInsert(reminderVO);
		
		LOG.debug("-------------------------");
		LOG.debug("-doInsert-Controller-");
		LOG.debug("-flag-" + flag);
		LOG.debug("-------------------------");
		
		Gson gson = new Gson();
		String json = gson.toJson(flag);
		
		return json;
	}
	
}
