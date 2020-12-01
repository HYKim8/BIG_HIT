package com.bighit.on.reaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bighit.on.cmn.Message;
import com.google.gson.Gson;

@Controller
public class ReactionController {
	final Logger   LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ReactionService reactionService;
	
	
	@RequestMapping(value="reaction/doInsert.do",method=RequestMethod.POST)
	@ResponseBody
	public int doInsert(@RequestParam("resId") String resId, 
							   HttpServletRequest request) throws Exception{
		ReactionVO reactionVO = new ReactionVO();
		Message message=new Message();
		
		HttpSession session = request.getSession();
		String regId = (String) session.getAttribute("regId");		
		LOG.debug("====regId==="+regId);
		
		reactionVO.setRegId(regId);
		reactionVO.setResId(resId);
		reactionVO.setEmoji(1);
		
		int flag = this.reactionService.doInsert(reactionVO);
		
		if(flag==1) {
			message.setMsgContents("좋아요 성공");
		}else {
			message.setMsgContents("좋아요 실패");
		}
		
		
		return flag;
		
	}
	
	@RequestMapping(value="reaction/doSelectCnt.do",method=RequestMethod.POST)
	@ResponseBody
	public String doSelectCnt(@RequestParam("thrKey") String thrKey,ReactionVO reactionVO) {
		LOG.debug("==doSelectCnt==");
		
		reactionVO.setThrKey(thrKey);
		
		ReactionVO reactionCnt = this.reactionService.doSelectCnt(reactionVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(reactionCnt);
		
		LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");     
		
		return json;
	}
	
}
