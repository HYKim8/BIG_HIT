package com.bighit.on.workspace;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bighit.on.cmn.Message;


public class WorkSpaceController {
	//workspace_mng.jsp -> 단건조회
	
	final Logger   LOG = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	WorkSpaceService workSpaceService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value="workspace/doInsert.do", method = RequestMethod.POST)
	@ResponseBody
	public Message doInsert(WorkSpaceVO workSpaceVO) {
		LOG.debug("===================================");
		LOG.debug("=doInsert=");
		LOG.debug("=param="+workSpaceVO);
		
		int flag = workSpaceService.doInsert(workSpaceVO);
		Message message = new Message();
		message.setRegId(String.valueOf(flag));
		
		if(flag>0) {
			message.setMsgContents("등록");
		}
		else {
			message.setMsgContents("등록실패");
		}
		LOG.debug("message"+message);
		return message;
	}
		
	
	@RequestMapping(value = "workspace/doDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public int doDelete(WorkSpaceVO workSpaceVO) {
		LOG.debug("===================================");
		LOG.debug("=doDelete=");
		LOG.debug("=param="+workSpaceVO);
		
		int flag = this.workSpaceService.doDelete(workSpaceVO);
		LOG.debug("=flag="+flag);
		Message message = new Message();
		message.setRegId(String.valueOf(flag));
		
		if(flag > 0) {
			message.setMsgContents("삭제");
		}
		else {
			message.setMsgContents("삭제 실패");
		}
		
		return flag;
	}
	
	@RequestMapping(value = "workspace/doSelectOne.do", method = RequestMethod.GET)
	@ResponseBody
	public String doSelectOne(WorkSpaceVO workSpaceVO, Model model) {
		String returnURL = "workspace/workspace_mng";
		LOG.debug("===================================");
		LOG.debug("=doSelectOne=");
		LOG.debug("=param="+workSpaceVO);
		
		WorkSpaceVO outVO = this.workSpaceService.doSelectOne(workSpaceVO);
		model.addAttribute("vo", outVO);
		
		
		return returnURL;
		
	}
	
}
