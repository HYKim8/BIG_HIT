package com.bighit.on.cmn;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bighit.on.channel.ChannelDaoImpl;
import com.bighit.on.channel.ChannelServiceImpl;
import com.bighit.on.channel.ChannelVO;
import com.bighit.on.channelusers.ChannelUsersDao;
import com.bighit.on.channelusers.ChannelUsersVO;
import com.bighit.on.file.FileService;
import com.bighit.on.file.FileVO;
import com.bighit.on.reaction.ReactionService;
import com.bighit.on.reaction.ReactionVO;
import com.bighit.on.reminder.ReminderService;
import com.bighit.on.reminder.ReminderVO;
import com.bighit.on.thread.ThreadService;
import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersServiceImpl;
import com.bighit.on.user.dao.UsersVO;
import com.bighit.on.workspace.WorkSpaceServiceImpl;
import com.bighit.on.workspace.WorkSpaceVO;
import com.google.gson.Gson;

@Controller
public class MainController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired ChannelServiceImpl channelService;
	@Autowired WorkSpaceServiceImpl workSpaceService;
	@Autowired UsersServiceImpl usersService;
	@Autowired ChannelUsersDao channelUsersService;
	@Autowired ChannelDaoImpl channelDao;
	@Autowired ReminderService reminderService;
	@Autowired FileService fileService;
	@Autowired ThreadService threadService; 
	@Autowired ReactionService reactionService;
	@RequestMapping(value = "main/index.do", method = RequestMethod.GET)
	public ModelAndView main_view(HttpServletRequest req) {
		LOG.debug("-------------------------");
		LOG.debug("-main/index.do-");
		LOG.debug("-------------------------");
		
		HttpSession session = req.getSession();
		
		UsersVO usersVO = new UsersVO();
		usersVO = (UsersVO) session.getAttribute("usersVO");
		ReminderVO reminderVO = new ReminderVO();
		WorkSpaceVO ws = new WorkSpaceVO();
		ws.setWsLink(usersVO.getWs_link());
		
		ws = workSpaceService.doSelectOne(ws);
		LOG.debug("--ws----"+ws);
		boolean isReg = usersVO.getUser_serial().equals(ws.getRegId());
		ModelAndView mav = new ModelAndView();
		//채널 검색용 값 세팅
		Search search = new Search();
		Search chSearch = null;
		List<ThreadVO> threadList = null;
		ChannelVO nowCh = null;
		List<UsersVO> uslist = null;
		List<ThreadVO> pinThrList = null;
		String wsLink = usersVO.getWs_link();
		HashMap<String,UsersVO> userHash = null;
		HashMap<String,FileVO> fileHash = null;
		//첫번째 키 - 쓰레드키 
		//두번째 키 - emoji
		//값 -> 메시지(반응 갯수,간단 메시지 (a님,b님 외 n명 )) 
		HashMap<String, HashMap<Integer,Message>> reactionHash = new HashMap<String, HashMap<Integer,Message>>();
		//채널 검색이 들어갔을 경우
		if(req.getParameter("searchWord")!=null) {
			chSearch = new Search();
			chSearch.setSearchWord((String)req.getParameter("searchWord"));
			 if(req.getParameter("pageNum")==null) {
				 chSearch.setPageNum(1);
		     }
		      if(req.getParameter("pageSize")==null)
		      {
		    	  chSearch.setPageSize(20);
		      }
		    nowCh = new ChannelVO();
		    nowCh.setChLink(chSearch.getSearchWord());
		      
		    threadList = this.threadService.doSelectList(chSearch);
//		    Collections.reverse(threadList);
		      nowCh = this.channelService.doSelectOne(nowCh);
		      LOG.debug(chSearch.toString());
		      LOG.debug(threadList.toString());
		    uslist = this.usersService.doSelectList(nowCh);
		    pinThrList = this.threadService.doSelectListIsPinned(nowCh);
		    ReactionVO react = new ReactionVO();
		    
		    //채널 유저정보들 처리 파트
		    
		    //이부분 고침 위에 ws가 있어서 겹쳐서 밑에 List<UserVO>부분 끝에 ws로 바꿈 원래는 workSpaceVO 였음
		    //WorkSpaceVO workSpaceVO = new WorkSpaceVO();
			//workSpaceVO.setWsLink(wsLink);
			List<UsersVO> userList = usersService.doSelectList(ws);
			userHash = new HashMap<String,UsersVO>();
			for (UsersVO vo : userList) {
				if (null == vo.getThumb()) 
					vo.setThumb("/resources/img/default.jpg");			
				userHash.put(vo.getUser_serial(), vo);
				LOG.debug(vo.toString());
			}
		    
		  //반응 처리 파트, 
		    List<String> thrKeyList = threadService.doSelectList(nowCh);
		    for(String key: thrKeyList) {
		    	react.setThrKey(key);
		    	List<ReactionVO> reactList = reactionService.doSelectList(react);
		    	if(reactList.size()!=0) reactionHash.put(key, new HashMap<Integer, Message>());
		    	for(ReactionVO reactEl : reactList) {
		    		int emoji =  reactEl.getEmoji();
		    		Message msg;
		    		//반응 최초 입력
		    		if(! reactionHash.get(key).containsKey(emoji))
		    		{
		    			msg = new Message();
		    			//regId에 갯수를 넣을것임.
		    			msg.setRegId("1");
		    			msg.setMsgContents(userHash.get(reactEl.getRegId()).getName()+"님");		    			
		    		}
		    		else 
		    		{
		    			msg = reactionHash.get(key).get(emoji);
		    			msg.setRegId((Integer.parseInt(msg.getRegId())+1)+"");
		    			msg.setMsgContents(msg.getMsgContents()+","+userHash.get(reactEl.getRegId()).getName()+"님");
		    		}
		    		reactionHash.get(key).put(emoji,msg);
		    	}
		    }
		    
		    //파일 처리 파트 . 해당 채널의 파일VO 불러옴
		    FileVO searchFileVO = new FileVO();
		    searchFileVO.setChLink(nowCh.getChLink());
		    List<FileVO> fileList = fileService.doSelectListChLink(searchFileVO);
		    //파일 해쉬 키: 쓰레드 키 , 값: 파일VO
		    fileHash = new HashMap<String, FileVO>();
		    
		    for(FileVO vo:fileList) {
		    	fileHash.put(vo.getThrKey(), vo);		    	
		    }
		}
		
		
		search.setSearchWord(usersVO.getUser_serial());
		wsLink = usersVO.getWs_link();
		reminderVO.setWsLink(wsLink);
		
		LOG.debug("users' Thumbnail into Session(User_serialthumb)");
		
		
		LOG.debug("chList");
		search.setSearchDiv("10");
		List<ChannelVO> channelList = channelService.doSelectList(search);
		
		LOG.debug("DMList");
		search.setSearchDiv("20");
		List<ChannelVO> channelListDM = channelService.doSelectList(search);
		for(ChannelVO vo : channelListDM) {
			//채널이름이 상대방이름 / 내이름 으로 되어있음 이걸 split  
			String names[] = vo.getChName().split("/");
			// 잘못들어가있을 경우 아예 break, 첫번째가 내이름과 같으면 두번째이름을 채널 네임, 아닐시 반대  
			if(names.length != 2) break;
			
			String chName = names[0].equals(usersVO.getName())? names[1] : names[0];
			vo.setChName(chName);
		}
		
		
		
		LOG.debug("ReminderList");
		List<ReminderVO> reminderList = reminderService.doSelectList(reminderVO);
		
		LOG.debug("workspaceList");
		List<WorkSpaceVO> workspaceList = workSpaceService.doSelectList(usersVO);
		
		
		
		mav.setViewName("main/main2");
		mav.addObject("workspaceList", workspaceList);
		mav.addObject("channelList", channelList);
		mav.addObject("channelListDM", channelListDM);
		mav.addObject("reminderList", reminderList);
		mav.addObject("isReg",isReg);
		if(chSearch!=null )
		{
			mav.addObject("searchVO",chSearch);
			mav.addObject("threadList", threadList);
			mav.addObject("nowCh",nowCh);
			mav.addObject("uslist",userHash);
			mav.addObject("pinList",pinThrList);
			mav.addObject("reactHash",reactionHash);
			mav.addObject("fileHash", fileHash);
//			ChannelVO nowCh = null;
//			List<UsersVO> uslist = null;
		}
		return mav;
	}
	
	@RequestMapping(value = "main/wsChanger.do", method = RequestMethod.GET)
	@ResponseBody
	public String wsChanger(HttpServletRequest req, String toWsLink) {
		
		LOG.debug("wsChanger");
		LOG.debug("toWsLink" + toWsLink);
		HttpSession session = req.getSession();
		UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
		String workLink= toWsLink;
		String email = usersVO.getEmail();
		usersVO=usersService.doSelectOne(workLink, email);
		ChannelVO channelVO = channelService.doSelectOne2(workLink);
		session.setAttribute("usersVO", usersVO);
		session.setAttribute("channelVO", channelVO);
		LOG.debug("==userVO=="+usersVO);
		LOG.debug("==channelVO123123=="+session.getAttribute("channelVO"));
		return channelVO.getChLink();
	}
	
	@RequestMapping(value = "main/addchannel.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
    @ResponseBody
	public String addChannel(HttpServletRequest req, ChannelVO channelVO) {
		LOG.debug("-------------------------");
		LOG.debug("-main/addchannel.do-");
		LOG.debug("-------------------------"); 
	    
		HttpSession session = req.getSession();
		
		UsersVO usersVO = new UsersVO();
		usersVO = (UsersVO) session.getAttribute("usersVO");
		
		String wsLink = usersVO.getWs_link();
		String User_serial = usersVO.getUser_serial();
		
	    channelVO.setWsLink(wsLink);
	    channelVO.setChAccess("1");
	    channelVO.setRegId(User_serial);
	    
	    ChannelUsersVO channelUsersVO = new ChannelUsersVO();
	    // 클라이언트 2명이 동시에 만들면 유령 채널이 생기지 않을까?
	    String tmpKey = channelDao.doGetKey();
	    channelUsersVO.setChLink(tmpKey);
	    channelUsersVO.setUserSerial(User_serial);
	    channelUsersVO.setNotification(1);
	    
	    channelService.doInsert(channelVO);
	    channelUsersService.doInsert(channelUsersVO);
	    
	    Gson gson = new Gson();
    	String json = gson.toJson(channelVO);
		
		return json;
	}
	
	@RequestMapping(value = "reminder/doChkAlarm.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doChkAlarm(HttpServletRequest req) {
		LOG.debug("-------------------------");
		LOG.debug("-reminder/doChkAlarm.do-");
		LOG.debug("-------------------------");
		
		HttpSession session = req.getSession();
		UsersVO usersVO = new UsersVO();
		usersVO = (UsersVO) session.getAttribute("usersVO");
		
		String wsLink = usersVO.getWs_link();
		//ajax
		ReminderVO reminderVO = new ReminderVO();
		reminderVO.setWsLink(wsLink);
		
		List<ReminderVO> outList = reminderService.doSelectList(reminderVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(outList);
		
		LOG.debug(json);
		
		
		
		return json;
	}
	
	@RequestMapping(value = "file/doUpload.do", method = RequestMethod.POST)
	@ResponseBody
	public String doUpload(HttpServletRequest req, MultipartFile file, String fileType, String thrKey, String chLink) throws IllegalStateException, IOException {
		LOG.debug("-------------------------");
		LOG.debug("-file/doUpload.do-");
		LOG.debug("-------------------------");
		
		LOG.debug("file Type : " + fileType);
		HttpSession session = req.getSession();
		
		UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
		String userId = usersVO.getUser_serial();
		// thrKey(ajax)
		// chLink(ajax)
		
		String keyName = fileService.doMakeKeyName(fileType, file.getOriginalFilename());
		fileService.doFileUpload(keyName, file);
		
		FileVO fileVO = new FileVO();
		fileVO.setChLink(chLink);
		fileVO.setFileName(file.getOriginalFilename());
		fileVO.setFileUrl(keyName);
		fileVO.setThrKey(thrKey);
		fileVO.setRegId(userId);
		
		fileService.doInsert(fileVO);
		
		try {
			LOG.debug("file is :" + file.toString());
		} catch(Exception e) {
			return "error occured" + e.getMessage();
		}
		
		return "file/file";
	}
	
	@RequestMapping(value = "reminder/doSelectList.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectListReminder(HttpServletRequest req) {
		LOG.debug("-------------------------");
		LOG.debug("-reminder/doSelectList.do-");
		LOG.debug("-------------------------");
		
		HttpSession session = req.getSession();
		UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
		
		ReminderVO reminderVO = new ReminderVO();
		reminderVO.setWsLink(usersVO.getWs_link());
		
		Gson gson = new Gson();
		String json = gson.toJson(reminderVO);
		
		return json;
	}
	
	@RequestMapping(value = "reminder/doInsert.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsertReminder(HttpServletRequest req, ReminderVO reminderVO) {
		LOG.debug("-------------------------");
		LOG.debug("-reminder/doInsert.do-");
		LOG.debug("-------------------------");
		
		HttpSession session = req.getSession();
		UsersVO usersVO = new UsersVO();
		usersVO = (UsersVO) session.getAttribute("usersVO");
		LOG.debug("usersVO : " + usersVO);
		
		reminderVO.setRegId(usersVO.getUser_serial());
		reminderVO.setWsLink(usersVO.getWs_link());
		
		LOG.debug("reminderVO : " + reminderVO);
		
		reminderService.doInsert(reminderVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(reminderVO);
		
		return json;
	}
	
	@RequestMapping(value = "main/doUpdateUser.do", method = RequestMethod.POST)
	@ResponseBody
	public String doUpdateUser(HttpServletRequest req, UsersVO usersVO) {
		LOG.debug("-------------------------");
		LOG.debug("-main/doUpdateUser.do-");
		LOG.debug("-------------------------");
		
		HttpSession session = req.getSession();
		
		UsersVO sessionUser = (UsersVO) session.getAttribute("usersVO");
		
		sessionUser.setNickname(usersVO.getNickname());
		sessionUser.setPosition(usersVO.getPosition());
		sessionUser.setPhone_num(usersVO.getPhone_num());
		
		usersService.doUpdate(sessionUser);
		
		session.setAttribute("usersVO", sessionUser);
		
		return "main/index.do";
		
	}
	
	@RequestMapping(value = "file/doUpdateProfileImg.do", method = RequestMethod.POST)
	@ResponseBody
	public String doUpdateProfileImg(HttpServletRequest req, MultipartFile file)
			throws IllegalStateException, IOException {
		LOG.debug("-------------------------");
		LOG.debug("-file/doUpdateProfileImg.do-");
		LOG.debug("-------------------------");

		MultipartFile profileImgResized;
		MultipartFile thumbImgResized;

		// fileType 제한 걸 것 jpg만 되게.
		LOG.debug("file type : " + ".jpg");

		HttpSession session = req.getSession();
		
		UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");

		String uuid = UUID.randomUUID().toString();

		String userId = usersVO.getUser_serial();
		String profileImg = userId + "_profile";
		String thumbImg = userId + "_thumb";
		String keyNameProfile = "profileImg/" + profileImg + "/" + uuid + "_profile." + "jpg";
		String keyNameThumb = "thumbImg/" + thumbImg + "/" + uuid + "_thumb." + "jpg";

		try {
			profileImgResized = fileService.doResizeProfile(file);
			thumbImgResized = fileService.doResizeThumb(file);
		} catch (NullPointerException e) {
			LOG.debug("file이 null입니다.");
			return null;
		}
		

		LOG.debug("Profile image Upload to S3");
		fileService.doFileUpload(keyNameProfile, profileImgResized);
		LOG.debug("Thumbnail image Upload to S3");
		fileService.doFileUpload(keyNameThumb, thumbImgResized);

		String profileUrl = fileService.doFileDownload(keyNameProfile).toString();
		String thumbUrl = fileService.doFileDownload(keyNameThumb).toString();

		usersVO.setProfile_img(profileUrl);
		usersVO.setThumb(thumbUrl);
		usersService.doUpdate(usersVO);

		session.setAttribute("usersVO", usersVO);
		
		return "file/file";
	}
	
}
