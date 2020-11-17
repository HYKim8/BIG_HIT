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
import org.springframework.web.servlet.ModelAndView;

import com.bighit.on.channel.ChannelServiceImpl;
import com.bighit.on.channel.ChannelVO;
import com.bighit.on.user.dao.UsersServiceImpl;
import com.bighit.on.user.dao.UsersVO;
import com.bighit.on.workspace.WorkSpaceServiceImpl;
import com.bighit.on.workspace.WorkSpaceVO;

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
		
		// for the Test
		session.setAttribute("wsLink", "1");
		// for the Test
		
		String wsLink = (String) session.getAttribute("wsLink");
		
		ModelAndView mav = new ModelAndView();
		
		ChannelVO channelVO = new ChannelVO();
		channelVO.setWsLink(wsLink);
		
		WorkSpaceVO workSpaceVO = new WorkSpaceVO();
		workSpaceVO.setWsLink(wsLink);
		
		List<UsersVO> usersList = usersService.doSelectList(workSpaceVO);
		List<ChannelVO> channelList = channelService.doSelectList(channelVO);
		
		mav.setViewName("main/main2");
		mav.addObject("channelList", channelList);
		mav.addObject("usersList", usersList);
		
		return mav;
	}
}
