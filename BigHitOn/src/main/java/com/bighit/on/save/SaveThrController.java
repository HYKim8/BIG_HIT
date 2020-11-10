package com.bighit.on.save;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;

@Controller
public class SaveThrController {
	final static Logger LOG = LoggerFactory.getLogger(SaveThrController.class);
	
	@Autowired
	SaveThrService saveThrService;
	
	@RequestMapping(value="save/doSelectList.do",method = RequestMethod.GET)
	public String doSelectList(UsersVO user,Model model){
		//세션 구현후 여기부분 처리요망
		List<ThreadVO> list = saveThrService.doSelectList(user);
		model.addAttribute("list",list);
		
		String view = "save/save_list";
		return view;
	}
}
