package com.bighit.on.workspace;

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
import com.bighit.on.email.EmailVO;
import com.bighit.on.user.dao.UsersVO;
import com.google.gson.Gson;

@Controller
public class WorkSpaceController {
	// workspace_mng.jsp -> 단건조회

	final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WorkSpaceService workSpaceService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "workspace/doInsert.do", method = RequestMethod.POST)
	@ResponseBody
	public String doInsert(WorkSpaceVO workSpaceVO) {
		LOG.debug("===================================");
		LOG.debug("=doInsert=");
		LOG.debug("=param=" + workSpaceVO);

		Message message = workSpaceService.doInsert(workSpaceVO);
		
//		message.setRegId(String.valueOf(flag));
		
		LOG.debug("message" + message);
		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");
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
	public String doSelectList(UsersVO usersVO, Model model) {
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
	public String sendEmail(EmailVO emailVO) {
		
		workSpaceService.sendEmail(emailVO);
		
		return 0+"";
	}
	
	@RequestMapping(value = "workspace/teamUserAdd_view.do", method = RequestMethod.GET)
	public String teamUserAdd_view() {
		LOG.debug("-------------------------");
		LOG.debug("-workspace/teamUserAdd_view.do-");
		LOG.debug("-------------------------");
		
		return "workspace/workspace";
	}
}
