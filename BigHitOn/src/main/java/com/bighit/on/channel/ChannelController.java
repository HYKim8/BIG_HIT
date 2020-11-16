package com.bighit.on.channel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bighit.on.cmn.Message;
import com.bighit.on.cmn.Search;
import com.bighit.on.user.dao.UsersVO;
import com.google.gson.Gson;

@Controller
public class ChannelController {
	
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ChannelService channelService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value = "channel/doInsert.do", method = RequestMethod.POST)
	@ResponseBody
	public String doInsert(ChannelVO channelVO) {
		LOG.debug("===================================");
		LOG.debug("=doInsert=");
		LOG.debug("=param=" + channelVO);
		
		int flag = channelService.doInsert(channelVO);
		Message message = new Message();
		message.setRegId(String.valueOf(flag));

		if (flag > 0) {
			message.setMsgContents("등록");
		} else {
			message.setMsgContents("등록실패");
		}
		LOG.debug("message" + message);
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");
		return json;
		
		
	}
	
	@RequestMapping(value = "channel/doDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public String doDelete(ChannelVO channelVO) {
		LOG.debug("===================================");
		LOG.debug("=doDelete=");
		LOG.debug("=param=" + channelVO);
		
		int flag = channelService.doDelete(channelVO);
		Message message = new Message();
		message.setRegId(String.valueOf(flag));
		
		if (flag > 0) {
			message.setMsgContents("삭제");
		} else {
			message.setMsgContents("삭제 실패");
		}
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}
	
	@RequestMapping(value = "channel/doSelectOne.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"	)
	@ResponseBody
	public String doSelectOne(ChannelVO channelVO) {
		LOG.debug("===================================");
		LOG.debug("=doSelectOne=");
		LOG.debug("=param="+channelVO);
		
		ChannelVO outVO = channelService.doSelectOne(channelVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(outVO);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}
	
	@RequestMapping(value = "channel/doSelectList.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"	)
	@ResponseBody
	public String doSelectList(ChannelVO channelVO, Model model) {
		LOG.debug("-------------------------");
		LOG.debug("-doSelectList-");
		LOG.debug("-------------------------");
		
		List<ChannelVO> list = this.channelService.doSelectList(channelVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		LOG.debug("-------------------------");
		LOG.debug("-json-"+json);
		LOG.debug("-------------------------");
		
		return json;
	}
	
	@RequestMapping(value = "main/channelList.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"	)
	@ResponseBody
	public String doSelectList(UsersVO user, Model model) {
		LOG.debug("-------------------------");
		LOG.debug("-doSelectList-");
		LOG.debug("-------------------------");
		Search search = new Search("10",user.getUser_serial());
		List<ChannelVO> list = this.channelService.doSelectList(search);
		//채널 리스트 
		model.addAttribute("chlist", list);
		
		search.setSearchDiv("20");
		list =  this.channelService.doSelectList(search); 
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		LOG.debug("-------------------------");
		LOG.debug("-json-"+json);
		LOG.debug("-------------------------");
		
		return "main/main";
	}
}
