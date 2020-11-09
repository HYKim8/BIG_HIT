package com.bighit.on.workspace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("WorkSpaceServiceImpl")
public class WorkSpaceServiceImpl implements WorkSpaceService {
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WorkSpaceDaoImpl workSpaceDao;
	
	@Override
	public int workSpaceCK(WorkSpaceVO workSpaceVO) {
		WorkSpaceVO workSpaceCheck = workSpaceDao.doSelectOne(workSpaceVO);
		LOG.debug("workSpaceCheck.getWsLink():"+workSpaceCheck.getWsLink());
		if(null != workSpaceCheck.getWsLink()) {
			LOG.debug("중복되는 workspace입니다");
			return 1;
		}
		else {
			LOG.debug("중복되지않는 workspace입니다");
			return workSpaceDao.doInsert(workSpaceVO);
		}
	}
	
	@Override
	public int doInsert(WorkSpaceVO workSpaceVO) {
		
		
		return 0;
		
	}

	@Override
	public int doDelete(WorkSpaceVO workSpaceVO) {
		return workSpaceDao.doDelete(workSpaceVO);
	}

	@Override
	public WorkSpaceVO doSelectOne(WorkSpaceVO wsLink) {
		return workSpaceDao.doSelectOne(wsLink);
	}



}
