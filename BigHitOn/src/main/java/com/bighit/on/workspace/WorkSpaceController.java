package com.bighit.on.workspace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class WorkSpaceController {
	
	final Logger   LOG = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	WorkSpaceService workSpaceService;
	
	@RequestMapping(value = "workspace/doDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public int doDelete(WorkSpaceVO workSpaceVO) {
		LOG.debug("===================================");
		LOG.debug("=doDelete=");
		LOG.debug("=param="+workSpaceVO);
		
		int flag = this.workSpaceService.doDelete(workSpaceVO);
		LOG.debug("=flag="+flag);
		return flag;
	}
}
