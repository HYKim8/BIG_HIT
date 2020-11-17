package com.bighit.on.cmn;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.bighit.on.channel.ChannelServiceImpl;
import com.bighit.on.channel.ChannelVO;
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
	
	@RequestMapping(value = "main/index.do", method = RequestMethod.GET)
	public ModelAndView main_view(HttpServletRequest req) {
		LOG.debug("-------------------------");
		LOG.debug("-main/index.do-");
		LOG.debug("-------------------------");
		
		HttpSession session = req.getSession();
		
		UsersVO usersVO = new UsersVO();
		usersVO = (UsersVO) session.getAttribute("usersVO");
		
		ModelAndView mav = new ModelAndView();
		
		Search search = new Search();
		search.setSearchWord(usersVO.getUser_serial());
		
		LOG.debug("chList");
		search.setSearchDiv("10");
		List<ChannelVO> channelList = channelService.doSelectList(search);
		
		LOG.debug("DMList");
		search.setSearchDiv("20");
		List<ChannelVO> channelListDM = channelService.doSelectList(search);
		
		mav.setViewName("main/main2");
		mav.addObject("channelList", channelList);
		mav.addObject("channelListDM", channelListDM);
		
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
		String regId = usersVO.getUser_serial();
		
	    channelVO.setWsLink(wsLink);
	    channelVO.setChAccess("1");
	    channelVO.setRegId(regId);
	    
	    channelService.doInsert(channelVO);
	    
	    Gson gson = new Gson();
    	String json = gson.toJson(channelVO);
		
		return json;
	}
	
}
