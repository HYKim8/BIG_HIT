package com.bighit.on.command;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@Autowired
	MessageSource messageSource;
	
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
	
	@RequestMapping(value="command/doDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public String doDelete(CommandVO commandVO) {
		LOG.debug("===================================");
		LOG.debug("=doDelete=");
		LOG.debug("=param="+commandVO);
		
		int flag = commandService.doDelete(commandVO);
	    LOG.debug("==================");
	    LOG.debug("=flag="+flag);
	    LOG.debug("==================");
	    
	    Message message = new Message();
	    message.setRegId(flag+"");
	    
	    if(flag ==1 ) {
        	message.setMsgContents(commandVO.getComId()+"삭제 성공.");
        }else {
        	message.setMsgContents(commandVO.getComId()+" 삭제 실패.");
        }
	    Gson gson=new Gson();
	     String json = gson.toJson(message);
	     LOG.debug("==================");
	     LOG.debug("=json="+json);
	     LOG.debug("==================");         
	        
	     return json;
	
	}
	
	@RequestMapping(value="command/doUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public String doUpdate(CommandVO commandVO) {
		LOG.debug("===================================");
		LOG.debug("=doUpdate=");
		LOG.debug("=param="+commandVO);
		
		int flag = commandService.doUpdate(commandVO);
	    LOG.debug("==================");
	    LOG.debug("=flag="+flag);
	    LOG.debug("==================");
	    
	    Message message = new Message();
	    message.setRegId(flag+"");
	    
	    if(flag ==1 ) {
        	message.setMsgContents(commandVO.getComId()+"수정 성공.");
        }else {
        	message.setMsgContents(commandVO.getComId()+"수정 실패.");
        }
	    Gson gson=new Gson();
	     String json = gson.toJson(message);
	     LOG.debug("==================");
	     LOG.debug("=json="+json);
	     LOG.debug("==================");         
	        
	     return json;
		
	}
	
	@RequestMapping(value="command/doSelect.do", method = RequestMethod.POST)
	@ResponseBody
	public String doSelectList(CommandVO commandVO) {
		List<CommandVO> list = this.commandService.doSelectList(commandVO);
		
		Gson gson=new Gson();
        
		String json = gson.toJson(list);
        LOG.debug("3==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");
        
        return json;
	}
	
	@RequestMapping(value="command/doSelectOne.do", method = RequestMethod.POST)
	@ResponseBody
	public String doSelectOne(CommandVO commandVO,Locale locale,Model model) {
		LOG.debug("==================");
        LOG.debug("=CommandVO="+commandVO);
        LOG.debug("==================");
        
        if( 0==commandVO.getComId()) {
			throw new IllegalArgumentException("ComId를 확인하세요.");
		}
        
        CommandVO outVO = this.commandService.doSelectOne(commandVO);
      
		Gson gson=new Gson();
        
		String json = gson.toJson(outVO);
        LOG.debug("3==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");
        
        return json;
	}
	@RequestMapping(value="command/doSelectListChLink.do", method = RequestMethod.POST)
	@ResponseBody
	public String doSelectListChLink(ComChLinkVO comChLinkVO) {
		List<ComChLinkVO> list = this.commandService.doSelectListChLink(comChLinkVO);
		
		Gson gson=new Gson();
        
		String json = gson.toJson(list);
        LOG.debug("3==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");
        
        return json;
	
	}
	
}
