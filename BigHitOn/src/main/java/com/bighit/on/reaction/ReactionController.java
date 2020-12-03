package com.bighit.on.reaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bighit.on.cmn.Message;
import com.bighit.on.user.dao.UsersVO;
import com.google.gson.Gson;

@Controller
public class ReactionController {
	final Logger   LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ReactionService reactionService;
	
	
	@RequestMapping(value="reaction/doInsert.do",method=RequestMethod.POST)
	@ResponseBody
	public Message doInsert(String resId, String thrKey, String regId, HttpServletRequest request) throws Exception{
		ReactionVO reactionVO = new ReactionVO();
		Message message=new Message();
		
		//HttpSession session = request.getSession();
		//String regId = (String) session.getAttribute("regId");	
		//UsersVO regId = (UsersVO) session.getAttribute("usersVO");
		
		LOG.debug("====regId==="+regId);
		
		
		reactionVO.setThrKey(thrKey);
		reactionVO.setRegId(regId);
		reactionVO.setResId(resId);
		reactionVO.setEmoji(1);
		
		int flag = 0;
		String msgContents = "";
		try {
	    flag = this.reactionService.doInsert(reactionVO);
	    message.setMsgContents("좋아요 성공");
	    
		}catch(DuplicateKeyException d) {
		flag = reactionService.doDelete(reactionVO);
		message.setMsgContents("좋아요 취소");
		
		return message;
		}
		
		return message;
		
	}
	
	@RequestMapping(value="reaction/doSelectCnt.do",method=RequestMethod.POST)
	@ResponseBody
	public int doSelectCnt(@RequestParam("thrKey") String thrKey,ReactionVO reactionVO) {
		LOG.debug("==doSelectCnt==");
		
		reactionVO.setThrKey(thrKey);
		
		int selectCnt = this.reactionService.doSelectCnt(reactionVO);
		
		LOG.debug("==================");
        LOG.debug("=selectCnt="+selectCnt);
        LOG.debug("==================");     
		
		return selectCnt;
	}
	
}
