package com.bighit.on.command;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bighit.on.cmn.DTO;




	@Repository
	public class CommandDao {
		final Logger LOG = LoggerFactory.getLogger(CommandDao.class);
		
		@Autowired
		SqlSessionTemplate sqlSessionTemplate;
		
		final String NAMESPACE = "com.bighit.on.command";
	
	public CommandDao() {}

	public int doInsert(CommandVO commandVO) {
		LOG.debug("=======================");
		LOG.debug("====doInsert====");
		LOG.debug("=======================");	
	
		String statement = NAMESPACE+".doInsert";
		LOG.debug("====statement===="+statement);
		LOG.debug("====commandVO===="+commandVO);
		int flag = sqlSessionTemplate.insert(statement, commandVO);
		LOG.debug("-doInsert flag=" + flag);
	
	
		return flag;
	}

	public int doDelete(CommandVO commandVO) {
		LOG.debug("=======================");
		LOG.debug("====doDelete====");
		LOG.debug("=======================");	
	
		String statement = NAMESPACE+".doDelete";
		LOG.debug("====statement===="+statement);
		LOG.debug("====commandVO===="+commandVO);
		int flag = sqlSessionTemplate.delete(statement, commandVO);
		LOG.debug("-doDelete flag=" + flag);
	
	
		return flag;
	}

	public int doUpdate(CommandVO commandVO) {
		LOG.debug("=======================");
		LOG.debug("====doUpdate====");
		LOG.debug("=======================");	
		
		String statement = NAMESPACE+".doUpdate";
		LOG.debug("====statement===="+statement);
		LOG.debug("====commandVO===="+commandVO);
		
		int flag = sqlSessionTemplate.update(statement, commandVO);
		LOG.debug("-doDelete flag=" + flag);
	
	
		return flag;
	}		
	
	public List<CommandVO> doSelectList(CommandVO commandVO) {
		LOG.debug("=====================");
		LOG.debug("=doSelectList=");
		LOG.debug("=====================");
		
		String statement = NAMESPACE +".doSelectList";		
		LOG.debug("=statement="+statement);
		
		List<CommandVO> list = this.sqlSessionTemplate.selectList(statement, commandVO);
		
		for(CommandVO vo : list) {
			LOG.debug("=vo="+vo);
		}
		
		return list;
	}
	public List<ComChLinkVO> doSelectListChLink(ComChLinkVO comChLinkVO){
		LOG.debug("=======================");
		LOG.debug("====doSelectListChLink====");
		LOG.debug("=======================");
		String statement = NAMESPACE +".doSelectListChLink";		
		LOG.debug("=statement="+statement);
		
		List<ComChLinkVO> list = this.sqlSessionTemplate.selectList(statement, comChLinkVO);
		
		for(ComChLinkVO vo : list) {
			LOG.debug("=vo="+vo);
		}
		
		return list;
	}
	public CommandVO doSelectOne(CommandVO commandVO) {
		LOG.debug("=======================");
		LOG.debug("====doSelectOne====");
		LOG.debug("=======================");	
		
		String statement = NAMESPACE +".doSelectOne";	
		LOG.debug("=statement="+statement);
		LOG.debug("====commandVO===="+commandVO);
		
		CommandVO outVO = this.sqlSessionTemplate.selectOne(statement,commandVO);
		LOG.debug("=outVO="+outVO);
		
		return outVO;
	}
}
		 
	


