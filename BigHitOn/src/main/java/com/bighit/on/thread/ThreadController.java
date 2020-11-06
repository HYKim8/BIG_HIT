package com.bighit.on.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bighit.on.cmn.Message;
import com.google.gson.Gson;

public class ThreadController {
    //board_list.jsp ->조회
	//board_write.jsp ->등록
	//board_mng.jsp   ->단건조회,수정,삭제
	final Logger   LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ThreadService threadService;
	
	@RequestMapping(value="=thread/doInsert.do", method = RequestMethod.POST)
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
	
}
