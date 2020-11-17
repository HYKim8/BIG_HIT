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

@Controller
public class MainController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired ChannelServiceImpl channelService;
	
	@RequestMapping(value = "main/index.do", method = RequestMethod.GET)
	public ModelAndView main_view(HttpServletRequest req) {
		LOG.debug("-------------------------");
		LOG.debug("-main/index.do-");
		LOG.debug("-------------------------");
		
		HttpSession session = req.getSession();
		String wsLink = (String) session.getAttribute("wsLink");
		
		ChannelVO channelVO = new ChannelVO();
		channelVO.setWsLink(wsLink);
		
		List<ChannelVO> list = channelService.doSelectList(channelVO);
		
		return null;
	}
}
