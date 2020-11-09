package com.bighit.on.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
public class FileController {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FileService fileService;
	
	@Autowired
	FileDaoImpl fileDao;
	
	// json 데이터로 응답을 보내기 위한 
    @Autowired
    MappingJackson2JsonView jsonView;
	
	@RequestMapping(value = "file/file_view.do", method = RequestMethod.GET)
	public String file_view() {
		LOG.debug("-------------------------");
		LOG.debug("-file/file_view.do-");
		LOG.debug("-------------------------");
		
		return "file/file";
	}
	
	@RequestMapping(value = "file/doDowndolad.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView downloadFile() throws IOException {
		LOG.debug("-------------------------");
		LOG.debug("-file/doDownload.do-");
		LOG.debug("-------------------------");

		String keyName = "profileimg/testimg.png";
		File file = fileDao.doFileDownload(keyName);
		
		ModelAndView model = new ModelAndView();
		model.addObject("filedownloadurl", file);
		model.setViewName("file/file");
		
		return model;
	}
	

	
	@RequestMapping(value = "file/doUpload.do", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(MultipartFile filename) throws IllegalStateException, IOException {
		LOG.debug("-------------------------");
		LOG.debug("-file/doUpload.do-");
		LOG.debug("-------------------------");
		
		// 나중에 controller로 profileimg, file 등 분류를 하면 될 듯. ID도 받고.
		String upPath = "profileimg/"+filename.getOriginalFilename();
		fileService.doFileUpload(upPath, filename);
		try {
			LOG.debug("file is :" + filename.toString());
		} catch(Exception e) {
			return "error occured" + e.getMessage();
		}
		
		return "file/file";
	}
	
	
}
