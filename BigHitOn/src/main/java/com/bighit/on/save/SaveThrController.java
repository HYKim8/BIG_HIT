package com.bighit.on.save;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bighit.on.channel.ChannelVO;
import com.bighit.on.cmn.Message;
import com.bighit.on.thread.ThreadServiceImpl;
import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;
import com.google.gson.Gson;

@Controller
public class SaveThrController {
	final static Logger LOG = LoggerFactory.getLogger(SaveThrController.class);
	
	@Autowired
	SaveThrService saveThrService;
	
	@Autowired
	ThreadServiceImpl threadService;
	
	@RequestMapping(value = "main/storedThread.do", method = RequestMethod.GET)
	public String storedThread() {
		LOG.debug("==main/storedThread.do==");
		
		return "save_thread/stored_thread";
	}
	
	// pin 된 것 리스트
	@RequestMapping(value = "main/doSelectListIsPinned.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectListIsPinned(ChannelVO channelVO) {
		LOG.debug("main/doSelectListIsPinned.do");
		List<ThreadVO> list = threadService.doSelectListIsPinned(channelVO);
				
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		return json;
	}
	
	// 스레드 리스트
	@RequestMapping(value = "saveThread/doSelectList.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectList(HttpServletRequest req) {
		LOG.debug("========================");
		LOG.debug("==save/doSelectList.do==");
		LOG.debug("========================");
		
		HttpSession session = req.getSession();
		
		// for test 나중에 세션으로 멤버VO 받을 것
//		session.setAttribute("memberId", "UAUO88RAHER");
		UsersVO sessionUser = (UsersVO)session.getAttribute("usersVO");
		// for test
//		
//		String userSerial = (String) session.getAttribute("memberId");
//		
//		UsersVO usersVO = new UsersVO();
//		usersVO.setUser_serial(userSerial);
		
		List<ThreadVO> list = saveThrService.doSelectList(sessionUser);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		return json;
	}
	
	// 스레드 저장
	@RequestMapping(value="save/doSaveThread.do", method = RequestMethod.POST)
	@ResponseBody
	public Message doSaveThread(SaveThrVO saveThrVO, HttpServletRequest req, HttpServletResponse res) {
		LOG.debug("========================");
		LOG.debug("==save/doSaveThread.do==");
		LOG.debug("========================");
		
//		HttpSession session = req.getSession();
//		
//		// for test 나중에 세션으로 멤버VO 받을 것
//		session.setAttribute("memberId", "UAUO88RAHER");
//		// for test
//		
//		String regId = (String) session.getAttribute("memberId");
//		
//		saveThrVO.setRegId(regId);
		int flag = 0;
		String msgContents = "";
		try {
			flag = saveThrService.doInsert(saveThrVO);
			msgContents = "쓰레드 저장";
			LOG.debug(msgContents);
		} catch (DuplicateKeyException e) {
			res.setStatus(404);
			saveThrService.doDelete(saveThrVO);
			msgContents = "저장된 쓰레드 삭제"; 
			LOG.debug(msgContents);
		}
		
		Message msg = new Message();
		msg.setRegId(flag+"");
		msg.setMsgContents(flag == 1 ? msgContents : "쓰레드 저장 실패" );
		return msg;
		
	}
	
	@RequestMapping(value = "save/doTest.do", method = RequestMethod.GET)
	public String doTest() {
		
		LOG.debug("TEST PAGE(save_thread)");
		
		return "save_thread/save_thread";
	}
	
}
