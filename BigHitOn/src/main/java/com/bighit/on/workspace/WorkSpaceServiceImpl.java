package com.bighit.on.workspace;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("WorkSpaceServiceImpl")
public class WorkSpaceServiceImpl implements WorkSpaceService {
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WorkSpaceDaoImpl workSpaceDao;
	
	/**
	 * 워크스페이스링크 중복 체크
	 */
	@Override
	public int workSpaceLinkCK(WorkSpaceVO workSpaceVO) {
		WorkSpaceVO workSpaceCheck = workSpaceDao.doSelectOne(workSpaceVO);
		LOG.debug("workSpaceCheck.getWsLink():"+workSpaceCheck.getWsLink());
		if(null != workSpaceCheck.getWsLink()) {
			LOG.debug("중복되는 workspace입니다");
			return 1;
		}
		else {
			LOG.debug("중복되지않는 workspace입니다");
			return 0;
		}
	}
	
	@Override
	public int workSpaceNameCK(WorkSpaceVO workSpaceVO) {
		if(workSpaceVO.getWsName().length()<=8) {
			LOG.debug("workSpaceVO.getWsName().length():"+workSpaceVO.getWsName().length());
			LOG.debug("워크스페이스 이름이 짧습니다. 다시 지어주세요");
			
			return 1;
		}
		LOG.debug("workSpaceVO.getWsName().length():"+workSpaceVO.getWsName().length());
		LOG.debug("이름 생성 완료");
		return 0;
	}
	
	@Override
	public int workSpacePCK(WorkSpaceVO workSpaceVO) {
		
		if(workSpaceVO.getProject().length()<=10) {
			LOG.debug("workSpaceVO.getProject().length():"+workSpaceVO.getProject().length());
			LOG.debug("프로젝트 이름이 짧습니다. 다시 지어주세요");
			
			return 1;
		}
		LOG.debug("workSpaceVO.getWsName().length():"+workSpaceVO.getWsName().length());
		LOG.debug("프로젝트 생성 완료");
		return 0;
	}
	
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

	@Override
	public List<WorkSpaceVO> doSelectList(WorkSpaceVO workSpaceVO) {
		return workSpaceDao.doSelectList(workSpaceVO);
	}


}
