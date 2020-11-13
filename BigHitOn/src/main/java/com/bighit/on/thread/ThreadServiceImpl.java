package com.bighit.on.thread;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bighit.on.cmn.Search;


@Service
public class ThreadServiceImpl implements ThreadService {
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ThreadDao thrDao;
	@Override
	public List<ThreadVO> doSelectList(Search search){
		if(search.nonValueWord()) { 
			LOG.debug("here");
			return null;
		}
		
		return thrDao.doSelectList(search);
	}
	@Override
	public int doInsert(ThreadVO threadVO) {
		ThreadVO parentVO = new ThreadVO(); // 검색할려구 잠깐만듬		
		parentVO.setThrKey(threadVO.getParentKey()); //2 n
		parentVO = thrDao.doSelectOne(parentVO);
		
		if(threadVO.getParentKey() != null && parentVO.getThrKey() == threadVO.getParentKey()) {
			thrDao.doInsert(threadVO);
			parentVO.setChildCnt(parentVO.getChildCnt()+1);
					
			return thrDao.doUpdate(parentVO);
		}
		
		return thrDao.doInsert(threadVO);
	}
	
	@Override
	public int doDelete(ThreadVO threadVO) {
		return thrDao.doDelete(threadVO);
	}
	
	@Override
	public ThreadVO doSelectOne(ThreadVO threadVO) {
		return thrDao.doSelectOne(threadVO);
	}
	
	@Override
	public List<ThreadVO> doSelectAll(ThreadVO threadVO) {
		return thrDao.doSelectAll(threadVO);
	}
	
	@Override
	public int doUpdate(ThreadVO threadVO) {
		return thrDao.doUpdate(threadVO);
	}
	@Override
	public int doInsertRep(ThreadVO threadVO) {
		
		return 0;
	}

}
