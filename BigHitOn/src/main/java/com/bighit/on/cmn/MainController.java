package com.bighit.on.cmn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bighit.on.channel.ChannelDaoImpl;
import com.bighit.on.channel.ChannelServiceImpl;
import com.bighit.on.channel.ChannelVO;
import com.bighit.on.channelusers.ChannelUsersDao;
import com.bighit.on.channelusers.ChannelUsersVO;
import com.bighit.on.reminder.ReminderService;
import com.bighit.on.reminder.ReminderVO;
import com.bighit.on.user.dao.UsersServiceImpl;
import com.bighit.on.user.dao.UsersVO;
import com.bighit.on.workspace.WorkSpaceServiceImpl;
import com.google.gson.Gson;

@Controller
public class MainController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired ChannelServiceImpl channelService;
	@Autowired WorkSpaceServiceImpl workSpaceService;
	@Autowired UsersServiceImpl usersService;
	@Autowired ChannelUsersDao channelUsersService;
	@Autowired ChannelDaoImpl channelDao;
	@Autowired ReminderService reminderService;
	
	@RequestMapping(value = "main/index.do", method = RequestMethod.GET)
	public ModelAndView main_view(HttpServletRequest req) {
		LOG.debug("-------------------------");
		LOG.debug("-main/index.do-");
		LOG.debug("-------------------------");
		
		HttpSession session = req.getSession();
		
		UsersVO usersVO = new UsersVO();
		usersVO = (UsersVO) session.getAttribute("usersVO");
		ReminderVO reminderVO = new ReminderVO();
		
		ModelAndView mav = new ModelAndView();
		
		Search search = new Search();
		search.setSearchWord(usersVO.getUser_serial());
		String wsLink = usersVO.getWs_link();
		reminderVO.setWsLink(wsLink);
		
		LOG.debug("chList");
		search.setSearchDiv("10");
		List<ChannelVO> channelList = channelService.doSelectList(search);
		
		LOG.debug("DMList");
		search.setSearchDiv("20");
		List<ChannelVO> channelListDM = channelService.doSelectList(search);
		
		LOG.debug("ReminderList");
		List<ReminderVO> reminderList = reminderService.doSelectList(reminderVO);
		
		mav.setViewName("main/main2");
		mav.addObject("channelList", channelList);
		mav.addObject("channelListDM", channelListDM);
		mav.addObject("reminderList", reminderList);
		
		return mav;
	}
	
	@RequestMapping(value = "main/addchannel.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
    @ResponseBody
	public String addChannel(HttpServletRequest req, ChannelVO channelVO) {
		LOG.debug("-------------------------");
		LOG.debug("-main/addchannel.do-");
		LOG.debug("-------------------------"); 
	    
		HttpSession session = req.getSession();
		
		UsersVO usersVO = new UsersVO();
		usersVO = (UsersVO) session.getAttribute("usersVO");
		
		String wsLink = usersVO.getWs_link();
		String User_serial = usersVO.getUser_serial();
		
	    channelVO.setWsLink(wsLink);
	    channelVO.setChAccess("1");
	    channelVO.setRegId(User_serial);
	    
	    ChannelUsersVO channelUsersVO = new ChannelUsersVO();
	    // 클라이언트 2명이 동시에 만들면 유령 채널이 생기지 않을까?
	    String tmpKey = channelDao.doGetKey();
	    channelUsersVO.setChLink(tmpKey);
	    channelUsersVO.setUserSerial(User_serial);
	    channelUsersVO.setNotification(1);
	    
	    channelService.doInsert(channelVO);
	    channelUsersService.doInsert(channelUsersVO);
	    
	    Gson gson = new Gson();
    	String json = gson.toJson(channelVO);
		
		return json;
	}
	
	@RequestMapping(value = "reminder/doChkAlarm.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doChkAlarm(HttpServletRequest req) {
		LOG.debug("-------------------------");
		LOG.debug("-reminder/doChkAlarm.do-");
		LOG.debug("-------------------------");
		
		HttpSession session = req.getSession();
		UsersVO usersVO = new UsersVO();
		usersVO = (UsersVO) session.getAttribute("usersVO");
		
		String wsLink = usersVO.getWs_link();
		//ajax
		ReminderVO reminderVO = new ReminderVO();
		reminderVO.setWsLink(wsLink);
		
		List<ReminderVO> outList = reminderService.doSelectList(reminderVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(outList);
		
		LOG.debug(json);
		
		
		
		return json;
	}
}
