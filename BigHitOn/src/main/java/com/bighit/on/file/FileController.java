package com.bighit.on.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.bighit.on.user.dao.UsersServiceImpl;
import com.bighit.on.user.dao.UsersVO;
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
    
	@RequestMapping(value = "file/file_view.do", method = RequestMethod.GET)
	public String file_view(Model model) {
		LOG.debug("-------------------------");
		LOG.debug("-file/file_view.do-");
		LOG.debug("-------------------------");
		
		return "file/file";
	}
	
	@RequestMapping(value = "file/do404Chk.do", method = RequestMethod.POST)
	@ResponseBody
	public String do404Chk(HttpServletRequest req, Model model, String userSerial) {
		LOG.debug("-------------------------");
		LOG.debug("-file/do404Chk.do-");
		LOG.debug("-------------------------");
		
		
//		404면 넘어온다
//		특정 유저 id를 갖고 있다.
//		db에 접근해서 그 유저의 thumbnail 주소 값을 다시 받아온다.
//		값을 수정한다.
		
		// for Test
		UsersVO usersVO = new UsersVO();
		usersVO.setThumb("C:\\BIGHIT_thumbnail\\2.jpg");
		// for Test
		
		
		
		
		return "file/file";
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
		
		// UserService를 이용하여 profile keyName, thumb keyName 등록
//		usersVO.setThumb(keyNameThumb);
//		usersVO.setProfile_img(keyNameProfile);
//		userService.doUpdate(usersVO);
		
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
