package com.bighit.on.save;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bighit.on.cmn.DTO;
import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;

@Repository("SaveThrDaoImpl")
public class SaveThrDaoImpl {
	
	final static Logger   LOG = LoggerFactory.getLogger(SaveThrDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "com.bighit.on.save";
	
	public boolean doCheck(SaveThrVO saveThrVO)
	{
		return (int)(sqlSessionTemplate.selectOne(NAMESPACE+".doCheck", saveThrVO)) == 1 ;	
	}
	
	/**
	 * 쓰레드 저장 
	 * @param saveThrVO
	 * @return
	 */
	
	public int doInsert(SaveThrVO saveThrVO) {
		LOG.debug("saveThrVO : " + saveThrVO);
		String statement = NAMESPACE +".doInsert";
		int flag = sqlSessionTemplate.insert(statement, saveThrVO);
		LOG.debug("-doInsert flag=" + flag);
		return flag;
	}
	/**
	 * 쓰레드 저장 취소
	 * @param saveThrVO
	 * @return
	 */
	public int doDelete(SaveThrVO saveThrVO) {
		String statement = NAMESPACE +".doDelete";
		int flag = sqlSessionTemplate.delete(statement, saveThrVO);
		LOG.debug("-doInsert flag=" + flag);
		return flag;
	}

	public int doUpdate(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	public DTO doSelectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<SaveThrVO> doSelectList(SaveThrVO inVO) {
		String statement = NAMESPACE +".doSelectList";
		List<SaveThrVO> list = this.sqlSessionTemplate.selectList(statement, inVO);
		
		for(SaveThrVO vo : list) {
			LOG.debug("===========================");
			LOG.debug("=doSelectList vo="+vo);
			LOG.debug("===========================");
		}
		return list;
	}
	public List<ThreadVO> doSelectList(UsersVO inVO) {
		String statement = NAMESPACE +".doSelectSavedThreadList";
		List<ThreadVO> list = this.sqlSessionTemplate.selectList(statement, inVO);
		LOG.debug("param:"+inVO);
		for(ThreadVO vo : list) {
			LOG.debug("===========================");
			LOG.debug("=doSelectList vo="+vo);
			LOG.debug("===========================");
		}
		
		return list;
	}
}
