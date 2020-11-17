package com.bighit.on.user.dao;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.bighit.on.channel.ChannelVO;
import com.bighit.on.cmn.Message;
import com.bighit.on.cmn.Search;
import com.bighit.on.thread.ThreadVO;
import com.bighit.on.workspace.WorkSpaceService;
import com.bighit.on.workspace.WorkSpaceVO;
import com.google.gson.Gson;

@Controller
public class UsersController {
	
	final Logger LOG = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	WorkSpaceService workSpaceService;
	
	@Autowired
	MessageSource messageSource;

	
	@RequestMapping(value="users/logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		HttpSession session =  req.getSession();
		session.invalidate();
		return "main/main";
	}
	
	@RequestMapping(value="users/doLogin.do", method = RequestMethod.POST)
	@ResponseBody
	public Message doLogin(UsersVO usersVO, HttpServletRequest req,Locale locale,HttpServletResponse res) {
		Message message=new Message();
		LOG.debug("usersVO"+usersVO);
		UsersVO sessionUser = this.usersService.doSelectOne(usersVO.getWs_link(),usersVO.getEmail());
		LOG.debug("=======================");
		LOG.debug("=sessionUser=="+sessionUser);
		LOG.debug("=======================");
		
		HttpSession session =  req.getSession();
		session.setAttribute("usersVO", sessionUser);
//		int flag = this.usersService.emailCheck(usersVO);
//		if(1==flag) {
//			
//		}else {
//			
//		}
		
		return message;
	}
		
	@RequestMapping(value="users/loginView.do", method = RequestMethod.GET)
	public String loginView(UsersVO user,Model model) {
		model.addAttribute("usersVO", user);
		LOG.debug("=user=="+user);
		
		return "users/userws";
	}
	
	@RequestMapping(value="users/signUpView.do", method = RequestMethod.GET)
	public String signUpView(UsersVO user,Model model) {	
		model.addAttribute("usersVO", user);
		LOG.debug("=user=="+user);
		
		return "users/users";
	}
	
	@RequestMapping(value="users/doSignUp.do", method = RequestMethod.POST)
	@ResponseBody
	public Message doSignUp(UsersVO usersVO, HttpServletRequest req,Locale locale,HttpServletResponse res) {
		Message message=new Message();
		
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
		
		int flag = this.usersService.emailCheck(usersVO);
		message.setRegId(String.valueOf(flag));
		
		if(1==flag) {
			message.setMsgContents("이메일을 확인하세요.");
		}else {
			message.setMsgContents("회원가입 성공.");
		}
		
		int sessionUser = this.usersService.doInsert(usersVO);
		LOG.debug("=======================");
		LOG.debug("=sessionUser=="+sessionUser);
		LOG.debug("=======================");
		
		HttpSession session =  req.getSession();
		session.setAttribute("usersVO", sessionUser);

		return message;
		
	}
	
	
	@RequestMapping(value="users/doSelectListFromWs.do", method = RequestMethod.GET)
	@ResponseBody
	public String doSelectList(WorkSpaceVO workSpaceVO) {
		LOG.debug("=====================");
		LOG.debug("=doSelectList=");
		LOG.debug("=param="+workSpaceVO);
		
		List<UsersVO> list = this.usersService.doSelectList(workSpaceVO);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(list);
		LOG.debug("=====================");
		LOG.debug("=json="+json);
		
		 return json;
	}
	
	@RequestMapping(value="users/doSelectListFromCh.do", method = RequestMethod.GET)
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
		
		Message message = new Message();
		message.setRegId(flag+"");
		
		if(flag == 1) {
			message.setMsgContents(usersVO.getName()+" 님이 등록 되었습니다.");
		}else {
			message.setMsgContents(usersVO.getName()+" 님이 등록이 취소되었습니다.");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(flag);
		
		return json;
	}
	
	@RequestMapping(value="users/wsList.do",method = RequestMethod.GET)
	   @ResponseBody
	   public List<WorkSpaceVO> wsList(UsersVO user) throws Exception {
	      LOG.debug("=thread_view=");
	      
	      List<WorkSpaceVO> list = workSpaceService.doSelectList(user);
	      
	      for(WorkSpaceVO vo:list) {
	      LOG.debug(vo.toString());
	      }
	      return list;
	   }
	   
	
}
