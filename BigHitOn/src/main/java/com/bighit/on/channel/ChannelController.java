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

import com.bighit.on.channelusers.ChannelUsersVO;
import com.bighit.on.cmn.Message;
import com.bighit.on.cmn.Search;
import com.bighit.on.user.dao.UsersServiceImpl;
import com.bighit.on.user.dao.UsersVO;
import com.bighit.on.workspace.WorkSpaceService;
import com.bighit.on.workspace.WorkSpaceVO;
import com.google.gson.Gson;

@Controller
public class ChannelController {
	
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ChannelService channelService;
	
	@Autowired
	WorkSpaceService workSpaceService; 
	@Autowired
	UsersServiceImpl usersServiceImpl;
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
	
	@RequestMapping(value = "channel/doDelete2.do", method = RequestMethod.POST)
	@ResponseBody
	public String doDelete2(ChannelUsersVO channelUsersVO) {
		LOG.debug("===================================");
		LOG.debug("=doDelete=");
		
		int flag = channelService.doDelete2(channelUsersVO);
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
	public String doSelectList(Search search, Model model) {
		//세션으로 변경 
		UsersVO user = usersServiceImpl.doSelectOne(search.getSearchWord());
		
		WorkSpaceVO ws = new WorkSpaceVO();
		ws.setWsLink(user.getWs_link());
		List<UsersVO> userList = usersServiceImpl.doSelectList(ws);
		
		
		LOG.debug("-------------------------");
		LOG.debug("-doSelectList-");
		LOG.debug("-------------------------");
		search.setSearchDiv("10");
		List<ChannelVO> list = this.channelService.doSelectList(search);
		//채널 리스트 
		model.addAttribute("chlist", list);
		model.addAttribute("vo", search);
		
		search.setSearchDiv("20");
		list =  this.channelService.doSelectList(search);
		
		//쓰레드를 받을때는 채널 링크만 중요하므로 dm 리스트의 조회 편의성을 위하여 
		//dm 채널 이름을 임의로 상대방 이름으로 변경한다.
		for(ChannelVO vo : list) {
			//채널이름이 상대방이름 / 내이름 으로 되어있음 이걸 split  
			String names[] = vo.getChName().split("/");
			// 잘못들어가있을 경우 아예 break, 첫번째가 내이름과 같으면 두번째이름을 채널 네임, 아닐시 반대  
			if(names.length != 2) break;
			
			String chName = names[0].equals(user.getName())? names[1] : names[2];
			vo.setChName(chName);
		}
		model.addAttribute("dmlist", list);
		
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		LOG.debug("-------------------------");
		LOG.debug("-json-"+json);
		LOG.debug("-------------------------");
		
		return "main/main";
	}
}
