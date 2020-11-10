package com.bighit.on.mention;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bighit.on.reaction.ReactionService;
import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;

@Controller
public class MentAndReactController {
	final static Logger LOG = LoggerFactory.getLogger(MentAndReactController.class);
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired 
	MentionService mentionService;
	@Autowired 
	ReactionService reactionService;
	
	@RequestMapping(value="MenAndRea/doSelectList.do",method = RequestMethod.GET)
	public String doSelectList(UsersVO user,Model model) {
		List<ThreadVO> list = mentionService.doSelectList(user);
		List<ThreadVO> tmp = reactionService.doSelectList(user);
		
		for(ThreadVO vo : tmp) list.add(vo);
		list.sort(new Comparator<ThreadVO>() {

			@Override
			public int compare(ThreadVO o1, ThreadVO o2) {
				int comp = 0;
				try {
					comp = (sdf.parse(o1.getRegDt().trim())).compareTo(sdf.parse(o2.getRegDt().trim()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return comp;
			}
			
		});
		
		model.addAttribute("list", list);		
		String view = "MenAndRea/list";
		return view;
	}
}
