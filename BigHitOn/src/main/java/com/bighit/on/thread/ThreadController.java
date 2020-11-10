package com.bighit.on.thread;

import java.util.List;

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
public class ThreadController {
    //board_list.jsp ->조회
	//board_write.jsp ->등록
	//board_mng.jsp   ->단건조회,수정,삭제
	final Logger   LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ThreadService threadService;
	
	@RequestMapping(value="thread/doInsert.do", method = RequestMethod.POST)
	@ResponseBody
	public String doInsert(ThreadVO threadVO) {
		LOG.debug("===================================");
		LOG.debug("=doInsert=");
		LOG.debug("=param="+threadVO);
		
		 int flag = threadService.doInsert(threadVO);
		 LOG.debug("==================");
	     LOG.debug("=flag="+flag);
	     LOG.debug("==================");   
	     
	     Message  message=new Message();
	     message.setRegId(flag+"");
	     
	     if(flag ==1 ) {
	        	message.setMsgContents(threadVO.getRegId()+" 님이 등록 되었습니다.");
	        }else {
	        	message.setMsgContents(threadVO.getRegId()+" 님 등록 실패.");
	        }
	     Gson gson=new Gson();
	     String json = gson.toJson(message);
	     LOG.debug("==================");
	     LOG.debug("=json="+json);
	     LOG.debug("==================");         
	        
	     return json; 
	}
	
	@RequestMapping(value="thread/doDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public String doDelete(ThreadVO threadVO) {
		LOG.debug("===================================");
		LOG.debug("=doDelete=");
		LOG.debug("=param="+threadVO);
		
		int flag = threadService.doDelete(threadVO);
		LOG.debug("==================");
	    LOG.debug("=flag="+flag);
	    LOG.debug("=================="); 
	     
	     Message  message=new Message();
	     message.setThrKey(flag+"");
	     
	     if(flag == 1) {
	    	 message.setMsgContents(threadVO.getThrKey()+"삭제 성공");
	     }else {
	    	 message.setMsgContents(threadVO.getThrKey()+"삭제 실패");
	     }
	     Gson gson=new Gson();
	     String json = gson.toJson(message);
	     LOG.debug("==================");
	     LOG.debug("=json="+json);
	     LOG.debug("==================");         
	        
	     return json; 
	}
	
	@RequestMapping(value="thread/doUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public String doUpdate(ThreadVO threadVO) {
		LOG.debug("===================================");
		LOG.debug("=doUpdate=");
		LOG.debug("=param="+threadVO);
		
		int flag = threadService.doUpdate(threadVO);
		LOG.debug("==================");
	    LOG.debug("=flag="+flag);
	    LOG.debug("==================");
	    
	    Message  message=new Message();
	    message.setThrKey(flag+"");
	    
	    if(flag == 1) {
	    	 message.setMsgContents(threadVO.getThrKey()+"수정 성공");
	     }else {
	    	 message.setMsgContents(threadVO.getThrKey()+"수정 실패");
	     }
	     Gson gson=new Gson();
	     String json = gson.toJson(message);
	     LOG.debug("==================");
	     LOG.debug("=json="+json);
	     LOG.debug("==================");         
	        
	     return json;
	}
	//public List<ThreadVO> doSelectAll(ThreadVO threadVO){
	//
	//}
	
	
}
