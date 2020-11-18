package com.bighit.on.cmn;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bighit.on.channel.ChannelDaoImpl;
import com.bighit.on.channel.ChannelServiceImpl;
import com.bighit.on.channel.ChannelVO;
import com.bighit.on.channelusers.ChannelUsersDao;
import com.bighit.on.channelusers.ChannelUsersVO;
import com.bighit.on.file.FileService;
import com.bighit.on.file.FileVO;
import com.bighit.on.reminder.ReminderService;
import com.bighit.on.reminder.ReminderVO;
import com.bighit.on.user.dao.UsersServiceImpl;
import com.bighit.on.user.dao.UsersVO;
import com.bighit.on.workspace.WorkSpaceServiceImpl;
import com.bighit.on.workspace.WorkSpaceVO;
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
	@Autowired FileService fileService;
	
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
		
		LOG.debug("workspaceList");
		List<WorkSpaceVO> workspaceList = workSpaceService.doSelectList(usersVO);
		
		mav.setViewName("main/main2");
		mav.addObject("workspaceList", workspaceList);
		mav.addObject("channelList", channelList);
		mav.addObject("channelListDM", channelListDM);
		mav.addObject("reminderList", reminderList);
		
		return mav;
	}
	
	@RequestMapping(value = "main/wsChanger.do", method = RequestMethod.GET)
	@ResponseBody
	public String wsChanger(HttpServletRequest req, String toWsLink) {
		
		LOG.debug("wsChanger");
		LOG.debug("toWsLink" + toWsLink);
		HttpSession session = req.getSession();
		UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
		String workLink= toWsLink;
		String email = usersVO.getEmail();
		usersVO=usersService.doSelectOne(workLink, email);
		session.setAttribute("usersVO", usersVO);
		LOG.debug("==userVO=="+usersVO);
		return "main/main2";
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
	
	@RequestMapping(value = "file/doUpload.do", method = RequestMethod.POST)
	@ResponseBody
	public String doUpload(HttpServletRequest req, MultipartFile file, String fileType, String thrKey, String chLink) throws IllegalStateException, IOException {
		LOG.debug("-------------------------");
		LOG.debug("-file/doUpload.do-");
		LOG.debug("-------------------------");
		
		LOG.debug("file Type : " + fileType);
		HttpSession session = req.getSession();
		
		UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
		String userId = usersVO.getUser_serial();
		// thrKey(ajax)
		// chLink(ajax)
		
		String keyName = fileService.doMakeKeyName(fileType, file.getOriginalFilename());
		fileService.doFileUpload(keyName, file);
		
		FileVO fileVO = new FileVO();
		fileVO.setChLink(chLink);
		fileVO.setFileName(file.getOriginalFilename());
		fileVO.setFileUrl(keyName);
		fileVO.setThrKey(thrKey);
		fileVO.setRegId(userId);
		
		fileService.doInsert(fileVO);
		
		try {
			LOG.debug("file is :" + file.toString());
		} catch(Exception e) {
			return "error occured" + e.getMessage();
		}
		
		return "file/file";
	}
	
	@RequestMapping(value = "reminder/doSelectList.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectListReminder(HttpServletRequest req) {
		LOG.debug("-------------------------");
		LOG.debug("-reminder/doSelectList.do-");
		LOG.debug("-------------------------");
		
		HttpSession session = req.getSession();
		UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
		
		ReminderVO reminderVO = new ReminderVO();
		reminderVO.setWsLink(usersVO.getWs_link());
		
		Gson gson = new Gson();
		String json = gson.toJson(reminderVO);
		
		return json;
	}
	
	@RequestMapping(value = "reminder/doInsert.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsertReminder(HttpServletRequest req, ReminderVO reminderVO) {
		LOG.debug("-------------------------");
		LOG.debug("-reminder/doInsert.do-");
		LOG.debug("-------------------------");
		
		HttpSession session = req.getSession();
		UsersVO usersVO = new UsersVO();
		usersVO = (UsersVO) session.getAttribute("usersVO");
		LOG.debug("usersVO : " + usersVO);
		
		reminderVO.setRegId(usersVO.getUser_serial());
		reminderVO.setWsLink(usersVO.getWs_link());
		
		LOG.debug("reminderVO : " + reminderVO);
		
		reminderService.doInsert(reminderVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(reminderVO);
		
		return json;
	}
}
