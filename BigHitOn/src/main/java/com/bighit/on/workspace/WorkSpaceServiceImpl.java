package com.bighit.on.workspace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("WorkSpaceServiceImpl")
public class WorkSpaceServiceImpl implements WorkSpaceService {
	
	@Autowired
	private WorkSpaceDaoImpl workSpaceDao;
	
	@Override
	public int doInsert(WorkSpaceVO workSpaceVO) {
		return workSpaceDao.doInsert(workSpaceVO);
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
