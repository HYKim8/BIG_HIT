package com.bighit.on.reminder;

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
import com.bighit.on.file.FileVO;

@Repository("ReminderDaoImpl")
public class ReminderDaoImpl {
	final static Logger LOG = LoggerFactory.getLogger(ReminderDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "com.bighit.on.reminder.";
	
	public ReminderDaoImpl() {}
	
	/**
	 * need thrKey, remindTime, getId
	 */
	public int doInsert(ReminderVO reminderVO) {
		LOG.debug("=======================");
		LOG.debug("====doInsert====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doInsert";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====reminderVO===="+reminderVO);
		
		return sqlSessionTemplate.insert(statement, reminderVO);
	}

	/**
	 * need reminderId
	 */
	public int doDelete(ReminderVO reminderVO) {
		LOG.debug("=======================");
		LOG.debug("====doDelete====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doDelete";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====reminderVO===="+reminderVO);
		
		return sqlSessionTemplate.delete(statement, reminderVO);
	}
	
	public int doUpdateTest(ReminderVO reminderVO) {
		LOG.debug("=======================");
		LOG.debug("====doUpdate====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doUpdate";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====reminderVO===="+reminderVO);
		
		return sqlSessionTemplate.update(statement, reminderVO);
	}
	
	public ReminderVO doSelectOne(ReminderVO reminderVO) {
		LOG.debug("=======================");
		LOG.debug("====doSelectOne====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doSelectOne";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====reminderVO===="+reminderVO);
		
		ReminderVO outVO = sqlSessionTemplate.selectOne(statement, reminderVO);
		LOG.debug("====outVO====" + outVO);
		
		return outVO;
	}

	public List<ReminderVO> doSelectList(ReminderVO reminderVO) {
		LOG.debug("=======================");
		LOG.debug("====doSelectList====");
		LOG.debug("=======================");
		
		String statement = NAMESPACE + "doSelectList";
		
		LOG.debug("====statement===="+statement);
		LOG.debug("====reminderVO===="+reminderVO);
		
		List<ReminderVO> outList = sqlSessionTemplate.selectList(statement, reminderVO);
		
		for(ReminderVO vo : outList) {
			LOG.debug("====outVO====" + vo);
		}
		
		return outList;
	}

	
	
	
}

