package com.bighit.on.file;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.apigateway.model.Model;
import com.bighit.on.user.dao.UsersVO;

@Controller
public class FileController {

	final Logger LOG = LoggerFactory.getLogger(FileController.class);
	S3Util s3 = new S3Util();
	String bucketName = "kghbucket";
	
	@RequestMapping(value = "file/file_view.do")
	public String fileView() {
		LOG.debug("===================");
		LOG.debug("==fileView() ==");
		LOG.debug("===================");
		
		return "file/file";
	}
	
	@ResponseBody
	@RequestMapping(value = "file/file_test.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String fileView2(MultipartFile file, String str, HttpSession session, HttpServletRequest request,
			Model model) throws IOException {
		
		LOG.debug("originalName: " + file.getOriginalFilename());
		
		// 프로필 이미지의 추가 경로
		String uploadPath = "kgh/profileImage";
		
		ResponseEntity<String> img_path = new ResponseEntity<>(
				UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
				HttpStatus.CREATED);
		
		String user_imgPath = (String) img_path.getBody();
		
		LOG.debug("user_imgPath : " + user_imgPath);
		
		UsersVO inVO = new UsersVO();
		inVO.setProfile_img(user_imgPath);
		UsersVO userSession = (UsersVO) session.getAttribute("login");
		
		
		
		
		return "file/test";
	}
	
	
	
	
}
