package com.bighit.on.file;

import java.io.IOException;
import java.net.URL;
import java.util.List;

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

import com.google.gson.Gson;

@CrossOrigin
@Controller
public class FileController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
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
	
	
	@RequestMapping(value = "file/doUpload.do", method = RequestMethod.POST)
	@ResponseBody
	public String doUpload(MultipartFile file) throws IllegalStateException, IOException {
		LOG.debug("-------------------------");
		LOG.debug("-file/doUpload.do-");
		LOG.debug("-------------------------");
		
//		INSERT INTO file_thr (
//				file_id,
//				file_name,
//				thr_key,
//				file_url,
//				reg_id,
//				reg_dt,
//				ch_link
//	) VALUES (
//				file_seq.nextVal,
//				#{fileName},
//				#{thrKey},
//				#{fileUrl},
//				#{regId},
//				SYSDATE,
//				#{chLink}
		
		// 나중에 controller로 profileimg, file 등 분류를 하면 될 듯. ID도 받고 UUID도 추가하고.
		String keyName = "profileimg/"+file.getOriginalFilename();
		fileService.doFileUpload(keyName, file);
		
		try {
			LOG.debug("file is :" + file.toString());
		} catch(Exception e) {
			return "error occured" + e.getMessage();
		}
		
		return "file/file";
	}
	
	
}
