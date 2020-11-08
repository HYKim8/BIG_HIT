package com.bighit.on.file;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
public class FileController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FileService fileService;
	
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
	
	@RequestMapping(value = "file/doUpload.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView fileUpload(MultipartHttpServletRequest request) {
		LOG.debug("-------------------------");
		LOG.debug("-file/doUpload.do-");
		LOG.debug("-------------------------");
		
		
		
		
		
		
		return null;
	}
	
	
}
