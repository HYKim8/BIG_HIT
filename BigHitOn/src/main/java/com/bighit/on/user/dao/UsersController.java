package com.bighit.on.user.dao;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bighit.on.channel.ChannelVO;
import com.bighit.on.cmn.Message;
import com.bighit.on.workspace.WorkSpaceVO;
import com.google.gson.Gson;


public class UsersController {
	
	final Logger LOG = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	MessageSource messageSource;
	

	@RequestMapping(value="users/doSelectList2.do", method = RequestMethod.GET)
	@ResponseBody
	public String doSelectList2(WorkSpaceVO workSpaceVO) {
		LOG.debug("=====================");
		LOG.debug("=doSelectList2=");
		LOG.debug("=param="+workSpaceVO);
		
		List<UsersVO> list = this.usersService.doSelectList2(workSpaceVO);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(list);
		LOG.debug("=====================");
		LOG.debug("=json="+json);
		
		 return json;
	}
	
	@RequestMapping(value="users/doSelectList.do", method = RequestMethod.GET)
	@ResponseBody
	public String doSelectList(ChannelVO channelVO) {
		LOG.debug("=====================");
		LOG.debug("=doSelectList=");
		LOG.debug("=param="+channelVO);
		
		List<UsersVO> list = this.usersService.doSelectList(channelVO);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(list);
		LOG.debug("=====================");
		LOG.debug("=json="+json);
		
		 return json;
	}
	
	@RequestMapping(value="users/doSelectOne.do", method = RequestMethod.POST)
	@ResponseBody
	public String doSelectOne(String userSerial) {
		LOG.debug("=====================");
		LOG.debug("=doSelectOne=");
		LOG.debug("=param="+userSerial);
		
		UsersVO outVO = usersService.doSelectOne(userSerial);
		LOG.debug("===========================");
	    LOG.debug("=outVO="+outVO);
	   
	    Gson gson = new Gson();
	    String json = gson.toJson(outVO);
	    LOG.debug("=json="+json);
	   
	   return json;
	}
	
	@RequestMapping(value="users/doUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public String doUpdate(UsersVO usersVO) {
		LOG.debug("=====================");
		LOG.debug("=doUpdate=");
		LOG.debug("=param="+usersVO);
		
		int flag = usersService.doUpdate(usersVO);
		LOG.debug("=flag="+flag);
		
		Message message = new Message();
		message.setRegId(flag+"");
		
		if(flag == 1) {
			message.setMsgContents(usersVO.getName()+" 님의 데이터가 수정 되었습니다.");
		}else {
			message.setMsgContents(usersVO.getName()+" 님의 데이터 수정이 이루어지지 않았습니다.");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("===========================");
		LOG.debug("=json="+json);
		
		return json;
	 }
	
	@RequestMapping(value="users/doDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public String doDelete(UsersVO usersVO, Locale locale) {
		LOG.debug("=====================");
		LOG.debug("=doDelete=");
		LOG.debug("=param="+usersVO);
		
		int flag = usersService.doDelete(usersVO);
		LOG.debug("=flag="+flag);
		
		Message message = new Message();
		message.setRegId(flag+"");
		
		if(flag == 1) {
			message.setMsgContents(usersVO.getName()+" 님이 삭제 되었습니다.");
		}else {
			message.setMsgContents(usersVO.getName()+" 님이 삭제가 취소되었습니다.");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("===========================");
		LOG.debug("=json="+json);
		LOG.debug("===========================");
		return json;
		
	}
	
	@RequestMapping(value="users/doInsert.do", method = RequestMethod.POST)
	@ResponseBody
	public String doInsert(UsersVO usersVO, Locale locale) {
		LOG.debug("=====================");
		LOG.debug("=doInsert=");
		LOG.debug("=param="+usersVO);
		
		int flag = usersService.doInsert(usersVO);
		LOG.debug("=flag="+flag);
		
		Gson gson = new Gson();
		String json = gson.toJson(flag);
		
		return json;
	}
	
	
	
}
