package com.bighit.on.workspace;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bighit.on.channel.ChannelServiceImpl;
import com.bighit.on.channel.ChannelVO;
import com.bighit.on.cmn.Message;
import com.bighit.on.email.EmailVO;
import com.bighit.on.user.dao.UsersDaoImpl;
import com.bighit.on.user.dao.UsersService;
import com.bighit.on.user.dao.UsersVO;
import com.google.gson.Gson;

@Controller
public class WorkSpaceController {
	// workspace_mng.jsp -> 단건조회

	final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WorkSpaceService workSpaceService;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	ChannelServiceImpl channelServiceImpl; 
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	UsersDaoImpl usdao;
	@RequestMapping(value = "workspace/doInsert.do", method = RequestMethod.POST)
	@ResponseBody
	public String doInsert(WorkSpaceVO workSpaceVO, HttpServletRequest req) {
		LOG.debug("===================================");
		LOG.debug("=doInsert=");
		LOG.debug("=param=" + workSpaceVO);

		HttpSession session = req.getSession();
		workSpaceVO.setRegId(usdao.doGetKey());
		ChannelVO gen = new ChannelVO("", workSpaceVO.getWsLink(), "일반", "일반", "이것은 언제나 모두를 포함하게 될 단 하나의 채널로 공지를 올리고 팀 전체의 대화를 나누기에 적합한 공간입니다.", "1", workSpaceVO.getRegId(), workSpaceVO.getRegDt());
		ChannelVO ran = new ChannelVO("", workSpaceVO.getWsLink(), "랜덤", "랜덤", "이것은 나머지 모든 것을 위한 채널입니다. 팀원들이 농담하거나 순간적인 아이디어나 재미있는 GIF를 공유하는 곳이죠! 마음껏 즐기세요!", "1", workSpaceVO.getRegId(), workSpaceVO.getRegDt());
		
		channelServiceImpl.doInsert(gen);
		channelServiceImpl.doInsert(ran);
		
		UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
		usersVO.setWs_link(workSpaceVO.getWsLink());
		usersVO.setNickname("nickname");
		usersVO.setProfile_img("");
		usersVO.setPosition("general");
		usersVO.setPhone_num("010-1234-5678");
		usersVO.setCountry(0000);
		usersVO.setState(0);
		usersVO.setOnline_state(0);
		usersVO.setReg_id("");
		usersVO.setReg_dt("");
		usersVO.setThumb("");
		
		usersService.doInsert(usersVO);
		Message message = workSpaceService.doInsert(workSpaceVO);
		WorkSpaceVO sessionWorkSpace = this.workSpaceService.doSelectOne(workSpaceVO);
		//session.setAttribute("WorkSpaceVO", sessionWorkSpace);
		
		
		
//		message.setRegId(String.valueOf(flag));
		
		LOG.debug("message" + message);
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");
		LOG.debug("===usersVO.getReg_id()="+usersVO.getReg_id());
		
		return json;
	}

	@RequestMapping(value = "workspace/doDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public String doDelete(WorkSpaceVO workSpaceVO) {
		LOG.debug("===================================");
		LOG.debug("=doDelete=");
		LOG.debug("=param=" + workSpaceVO);

		int flag = this.workSpaceService.doDelete(workSpaceVO);
		LOG.debug("=flag=" + flag);
		Message message = new Message();
		message.setRegId(flag + "");

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

	@RequestMapping(value = "workspace/doSelectOne.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"	
			)
	@ResponseBody
	public String doSelectOne(WorkSpaceVO workSpaceVO) {
		LOG.debug("===================================");
		LOG.debug("=doSelectOne=");
		LOG.debug("=param="+workSpaceVO);
		
		WorkSpaceVO outVO = this.workSpaceService.doSelectOne(workSpaceVO);
		Gson gson = new Gson();
		String json = gson.toJson(outVO);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");
		
		return json;
		
	}
	
	@RequestMapping(value = "workspace/doSelectList.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8"	
			)
	@ResponseBody
	public String doSelectList(UsersVO usersVO, Model model, HttpServletRequest req) {
		LOG.debug("-------------------------");
		LOG.debug("-doSelectList-");
		LOG.debug("-------------------------");
		
		List<WorkSpaceVO> list = workSpaceService.doSelectList(usersVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		LOG.debug("-------------------------");
		LOG.debug("-json-"+json);
		LOG.debug("-------------------------");
		
		return json;
	}
	
	@RequestMapping(value = "workspace/sendEmail.do", method = RequestMethod.POST)
	@ResponseBody
	public String sendEmail(EmailVO emailVO, UsersVO usersVO) {
		
		
		
		int flag = this.usersService.emailCheck(usersVO);
		if(flag==0) workSpaceService.sendEmail(emailVO);
		LOG.debug("=flag=" + flag);
		Message message = new Message();
		message.setRegId(flag + "");

		if (flag > 0) {
			message.setMsgContents("이미 이 워크스페이스에 참여된 사용자입니다.");			

		} else {
			message.setMsgContents("전송 성공.");
		}
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}
	
	@RequestMapping(value = "workspace/teamUserAdd_view.do", method = RequestMethod.GET)
	public String teamUserAdd_view() {
		LOG.debug("-------------------------");
		LOG.debug("-workspace/teamUserAdd_view.do-");
		LOG.debug("-------------------------");
		
		return "workspace/workspace";
	}
}
