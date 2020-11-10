package com.bighit.on.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bighit.on.cmn.Message;
import com.google.gson.Gson;

@Controller
public class CommandController {
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CommandService commandService;
	
	@RequestMapping(value="command/doInsert.do", method = RequestMethod.POST)
	@ResponseBody
	public String doInsert(CommandVO commandVO) {
		LOG.debug("===================================");
		LOG.debug("=doInsert=");
		LOG.debug("=param="+commandVO);
		
		int flag = commandService.doInsert(commandVO);
	    LOG.debug("==================");
	    LOG.debug("=flag="+flag);
	    LOG.debug("==================");
	    
	    Message message = new Message();
	    message.setRegId(flag+"");
	    
	    if(flag ==1 ) {
        	message.setMsgContents(commandVO.getComId()+"이 등록 되었습니다.");
        }else {
        	message.setMsgContents(commandVO.getComId()+" 등록 실패.");
        }
	    Gson gson=new Gson();
	     String json = gson.toJson(message);
	     LOG.debug("==================");
	     LOG.debug("=json="+json);
	     LOG.debug("==================");         
	        
	     return json; 
	}
}
