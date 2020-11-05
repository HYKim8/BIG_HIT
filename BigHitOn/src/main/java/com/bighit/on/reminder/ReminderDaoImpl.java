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

@Repository("ReminderDaoImpl")
public class ReminderDaoImpl {
	final static Logger LOG = LoggerFactory.getLogger(ReminderDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "com.bighit.on.reminder.";
	
	public ReminderDaoImpl() {}

	RowMapper rowMapper = new RowMapper<ReminderVO>() {
		@Override
		public ReminderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReminderVO outVO = new ReminderVO();
			
			outVO.setThrKey(rs.getString("thr_key"));
			outVO.setRegDt(rs.getString("reg_dt"));
			outVO.setRegId(rs.getString("reg_id"));
			outVO.setRemindTime(rs.getString("remind_time"));
			outVO.setRemindId(rs.getString("remind_id"));
			
			return outVO;
		}

	};
	
	
	
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

	public List<ReminderVO> doSelectList(DTO dto) {
		ReminderVO inVO = (ReminderVO) dto;
		List<ReminderVO> outList = null;
		Object[] args = {
			inVO.getRegId(),
			inVO.getThrKey()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT                   \n");
		sb.append("    remind_id,           \n");
		sb.append("    thr_key,             \n");
		sb.append("    remind_time,         \n");
		sb.append("    reg_id,              \n");
		sb.append("    reg_dt               \n");
		sb.append("FROM                     \n");
		sb.append("    reminder             \n");
		sb.append("WHERE                    \n");
		sb.append("    reg_id = ?           \n");
		sb.append("    and thr_key = ?      \n");
		
		LOG.debug("---------------------------");
		LOG.debug("-sql-\n" + sb.toString());
		LOG.debug("-param-\n" + inVO);
		LOG.debug("---------------------------");
		
		outList = this.jdbcTemplate.query(sb.toString(), args, rowMapper);
		
		for(ReminderVO vo : outList) {
			LOG.debug("---------------------------");
			LOG.debug("-doSelectList outVO-" + vo);
			LOG.debug("---------------------------");
		}
		
		return outList;
	}

	
	
	
}

