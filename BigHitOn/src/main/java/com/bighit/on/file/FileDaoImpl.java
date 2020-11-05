package com.bighit.on.file;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bighit.on.cmn.DTO;
import com.bighit.on.reminder.ReminderDaoImpl;
import com.bighit.on.reminder.ReminderVO;

@Repository("FileDaoImpl")
public class FileDaoImpl {
	final static Logger LOG = LoggerFactory.getLogger(ReminderDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	private final String NAMESPACE = "com.bighit.on.file.";
	
	public FileDaoImpl() {
		super();
	}
	
	public int doInsert(FileVO fileVO) {
		LOG.debug("=======================");
		LOG.debug("====doInsert====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doInsert";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====fileVO===="+fileVO);
		
		return sqlSessionTemplate.insert(statement, fileVO);
	}
	
	public int doDelete(FileVO fileVO) {
		LOG.debug("=======================");
		LOG.debug("====doDelete====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doDelete";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====fileVO===="+fileVO);
		
		return sqlSessionTemplate.delete(statement, fileVO);
	}

	public int doUpdate(FileVO fileVO) {
		LOG.debug("=======================");
		LOG.debug("====doUpdate====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doUpdate";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====fileVO===="+fileVO);
		
		return sqlSessionTemplate.update(statement, fileVO);
	}

	public FileVO doSelectOne(FileVO fileVO) {
		LOG.debug("=======================");
		LOG.debug("====doSelectOne====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doSelectOne";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====fileVO===="+fileVO);
		
		FileVO outVO = sqlSessionTemplate.selectOne(statement, fileVO);
		LOG.debug("====outVO====" + outVO);
		
		return outVO;
	}
	
	public List<FileVO> doSelectListThrKey(FileVO fileVO){
		LOG.debug("=======================");
		LOG.debug("====doSelectListThrKey====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doSelectListThrKey";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====fileVO===="+fileVO);
		
		List<FileVO> outList = sqlSessionTemplate.selectList(statement, fileVO);
		
		for(FileVO vo : outList) {
			LOG.debug("====outVO====" + vo);
		}
		
		return outList;
	}
	
	public List<FileVO> doSelectListChLink(FileVO fileVO){
		LOG.debug("=======================");
		LOG.debug("====doSelectListChLink====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doSelectListChLink";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====fileVO===="+fileVO);
		
		List<FileVO> outList = sqlSessionTemplate.selectList(statement, fileVO);
		
		for(FileVO vo : outList) {
			LOG.debug("====outVO====" + vo);
		}
		
		return outList;
	}
	

	
	
	
}

