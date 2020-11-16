package com.bighit.on.file;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.bighit.on.user.dao.UsersServiceImpl;
import com.bighit.on.user.dao.UsersVO;
import com.bighit.on.workspace.WorkSpaceVO;
import com.google.gson.Gson;

@CrossOrigin
@Controller
public class FileController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FileService fileService;
	
	@Autowired
	UsersServiceImpl userService;
	
	@Autowired
	FileDaoImpl fileDao;
	
	// json 데이터로 응답을 보내기 위한 
    @Autowired
    MappingJackson2JsonView jsonView;
    
    @RequestMapping(value = "file/test.do", method = RequestMethod.GET)
    public String test(String status) {
    	
    	LOG.debug("in to TEST");
    	
    	
    	
    	return "file/test";
    }
    
    @RequestMapping(value = "file/testImg.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String testImg(HttpServletRequest req, String keyName) {
    	
    	// 처음 로딩( $(document).ready(function(){} )
    	// workspace 링크 session이든 뭐든 암튼 데이터 얻기
    	HttpSession session = req.getSession();
    	
    	// for test
    	session.setAttribute("wsLink", "1");
    	// for test
    	
    	String wsLink = (String) session.getAttribute("wsLink");
    	
    	// workSpaceService.doSelectUser~~~~ 해서 그 특정 워크 스페이스의 유저 리스트 뽑기.
    	WorkSpaceVO workSpaceVO = new WorkSpaceVO();
    	workSpaceVO.setWsLink(wsLink);
    	
    	// WorkSpace 내부의 모든 유저의 Thumb 데이터 얻어서 session에 User_serial_thumb으로 등록.
    	// ${hContext.concat(sessionScope['U5835RE6LL2thumb']) }
    	List<UsersVO> userList = userService.doSelectList(workSpaceVO);
    	for(UsersVO vo : userList) {
    		if(null != vo.getThumb()) {
    			session.setAttribute(vo.getUser_serial()+"thumb", vo.getThumb());
    		} else {
    			session.setAttribute(vo.getUser_serial()+"thumb", "/resources/img/default.jpg");
    		}
    		
    	}
    	
    	
    	
    	return "file/test";
    }
    
    @RequestMapping(value = "file/thumb_check.do", method = RequestMethod.GET)
    @ResponseBody
    public String thumb_check(UsersVO usersVO) {
    	
    	LOG.debug("-------------------------");
		LOG.debug("-thumb_check-");
		LOG.debug("-------------------------");
    	// 404error가 뜬 이미지
    	// 아이디가 넘어와서
    	
    	// String keyName = String 잘라서 key_name만 딴다. 앞부분 길이는 같으므로 ?로 짜르고 첫번째꺼에 일정 길이 이후로 가져오면 될 듯.
    	String keyName = fileService.generateKeyName(usersVO.getThumb());
    	
    	// URL url = fileService.doFileDownload(keyName);
    	URL url = fileService.doFileDownload(keyName);
    	
    	// String userThumb = url.toString();
    	String userThumb = url.toString();
    	usersVO.setThumb(userThumb);
    	
    	// userService.doUpdateThumb(userId, userThumb);
    	userService.doUpdate(usersVO);
    	
    	Gson gson = new Gson();
    	String json = gson.toJson(userThumb);
    	
    	return json;
    }
    
	@RequestMapping(value = "file/file_view.do", method = RequestMethod.GET)
	public String file_view(Model model) {
		LOG.debug("-------------------------");
		LOG.debug("-file/file_view.do-");
		LOG.debug("-------------------------");
		
		return "file/file";
	}
	
	@RequestMapping(value = "main/main.do", method = RequestMethod.GET)
	public String main_view() {
		LOG.debug("-------------------------");
		LOG.debug("-file/file_view.do-");
		LOG.debug("-------------------------");
		
		return "main/main";
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "file/doDownload.do", method = RequestMethod.POST)
	@ResponseBody
	public String downloadFile(FileVO fileVO) throws IOException {
		LOG.debug("-------------------------");
		LOG.debug("-file/doDownload.do-");
		LOG.debug("-------------------------");

		
		FileVO tmpVO = fileService.doSelectOne(fileVO);
		
		String keyName = tmpVO.getFileUrl();
		
		URL url = fileService.doFileDownload(keyName);
		LOG.debug("-------------------------");
		LOG.debug("-url-" + url.toString());
		LOG.debug("-------------------------");
		// 임의로 설정. 원래는 file_id를 받아 doSelectOne(fileVO) 한 후 url(key_name)을 받아 다운받기로 해야할 듯. 

		Gson gson = new Gson();
		String json = gson.toJson(url.toString());
		
		LOG.debug("-------------------------");
		LOG.debug("-json-" + json);
		LOG.debug("-------------------------");
		
		return json;
	}
	

	
	@RequestMapping(value = "file/doSelectList.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectList(FileVO fileVO, Model model) {
		LOG.debug("-------------------------");
		LOG.debug("-file/doSelectList.do-");
		LOG.debug("-------------------------");

		List<FileVO> list = this.fileService.doSelectListChLink(fileVO);

		Gson gson = new Gson();
		String json = gson.toJson(list);

		LOG.debug("-------------------------");
		LOG.debug("-json-" + json);
		LOG.debug("-------------------------");

		return json;
	}
	
	@RequestMapping(value = "file/doUpdateProfileImg.do", method = RequestMethod.POST)
	@ResponseBody
	public String doUpdateProfileImg(HttpServletRequest req, MultipartFile file, String fileType, UsersVO usersVO) throws IllegalStateException, IOException {
		LOG.debug("-------------------------");
		LOG.debug("-file/doUpdateProfileImg.do-");
		LOG.debug("-------------------------");
		
		MultipartFile profileImgResized;
		MultipartFile thumbImgResized;
		
		// fileType 제한 걸 것 jpg만 되게.
		LOG.debug("file type : " + fileType);
		
		HttpSession session = req.getSession();
		
		// for test
		session.setAttribute("id", "KIM");
		session.setAttribute("thrKey", "1");
		session.setAttribute("chLink", "1");
		session.setAttribute("user_serial", "1");
		// for test
		
		String uuid = UUID.randomUUID().toString();
		
		String userId = (String) session.getAttribute("id");
		String profileImg = userId + "_profile";
		String thumbImg = userId + "_thumb";
		String keyNameProfile = "profileImg/" + profileImg + "/" + uuid + "_profile." + fileType;
		String keyNameThumb = "thumbImg/" + thumbImg + "/" + uuid + "_thumb." + fileType;
		
		try {
			profileImgResized = fileService.doResizeProfile(file);
			thumbImgResized = fileService.doResizeThumb(file);
		} catch (Exception e) {
			LOG.debug("-------------------------");
			LOG.debug("not select img file");
			LOG.debug("-------------------------");
			
			return "file/file";
		}
		
		
			LOG.debug("Profile image Upload to S3");
			fileService.doFileUpload(keyNameProfile, profileImgResized);
			LOG.debug("Thumbnail image Upload to S3");
			fileService.doFileUpload(keyNameThumb, thumbImgResized);
		
		// UserService를 이용하여 profile keyName, thumb keyName 등록 -> pre-signed url 만드는데는 비용이 들지 않으므로 url로 만들어서 넣자.
//			String profileUrl = fileService.doFileDownload(keyNameProfile).toString();
//			String thumbUrl = fileService.doFileDownload(keyNameThumb).toString();
//			usersVO.setProfile_img(profileUrl);
//			usersVO.setThumb(thumbUrl);
//			userService.doUpdate(usersVO);
			
		return "file/file";
	}
	
	
	@RequestMapping(value = "file/doUpload.do", method = RequestMethod.POST)
	@ResponseBody
	public String doUpload(HttpServletRequest req, MultipartFile file, String fileType) throws IllegalStateException, IOException {
		LOG.debug("-------------------------");
		LOG.debug("-file/doUpload.do-");
		LOG.debug("-------------------------");
		
		LOG.debug("file Type : " + fileType);
		HttpSession session = req.getSession();
		// for test
		session.setAttribute("id", "KIM");
		session.setAttribute("thrKey", "1");
		session.setAttribute("chLink", "1");
		// for test
		
		String userId = (String) session.getAttribute("id");
		String thrKey = (String) session.getAttribute("thrKey");
		String chLink = (String) session.getAttribute("chLink");
		
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
	
	
}
