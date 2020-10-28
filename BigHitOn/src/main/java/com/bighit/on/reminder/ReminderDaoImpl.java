package com.bighit.on.reminder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	public int doInsert(DTO dto) {
		int flag = 0;
		ReminderVO inVO = new ReminderVO();
		inVO = (ReminderVO) dto;
		Object[] args = {
			inVO.getThrKey(),
			inVO.getRemindTime(),
			inVO.getRegId()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO reminder (remind_id,                    \n");
		sb.append("						thr_key,                       \n");
		sb.append("						remind_time,                   \n");
		sb.append("						reg_id,                        \n");
		sb.append("						reg_dt) VALUES                 \n");
		sb.append("					(remind_seq.nextVal,?,TO_DATE(?,'yyyy/MM/dd hh24:mi'),?,sysdate) \n");

		LOG.debug("---------------------------");
		LOG.debug("-sql-\n" + sb.toString());
		LOG.debug("-param-\n" + inVO);
		LOG.debug("---------------------------");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);

		LOG.debug("---------------------------");
		LOG.debug("-doInsert flag-" + flag);
		LOG.debug("---------------------------");

		return flag;
	}

	/**
	 * need reminderId
	 */
	public int doDelete(DTO dto) {
		int flag = 0;
		ReminderVO inVO = (ReminderVO) dto;
		// inVO id 체크 후 다르면 거절할 것. view 단에서 할 수 있으면 거기서 짜를 것.
		Object[] args = {
			inVO.getRemindId()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM reminder       \n");
		sb.append(" WHERE remind_id = ?        \n");
		
		LOG.debug("---------------------------");
		LOG.debug("-sql-\n" + sb.toString());
		LOG.debug("-param-\n" + inVO);
		LOG.debug("---------------------------");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("---------------------------");
		LOG.debug("-doDelete flag-" + flag);
		LOG.debug("---------------------------");
		
		return flag;
	}

	
	
	public int doUpdateTest(DTO dto) {
		int flag = 0;
		ReminderVO inVO = (ReminderVO) dto;
		Object[] args = {
			inVO.getRemindTime(),
			inVO.getRemindId()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE reminder         \n");
		sb.append("SET                     \n");
		sb.append("    remind_time = TO_DATE(?,'yyyy/MM/dd hh24:mi'),    \n");
		sb.append("    reg_dt = sysdate    \n");
		sb.append("WHERE                   \n");
		sb.append("    remind_id = ?       \n");
		
		LOG.debug("---------------------------");
		LOG.debug("-sql-\n" + sb.toString());
		LOG.debug("-param-\n" + inVO);
		LOG.debug("---------------------------");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("---------------------------");
		LOG.debug("-doUpdate flag-" + flag);
		LOG.debug("---------------------------");
		
		return flag;
		
	}
	
//	public Connection getConnection() throws ClassNotFoundException, SQLException {
//		Connection conn = null;
//		final String DB_URL = "jdbc:oracle:thin:@211.238.142.124:1521:orcl";
//		final String DB_USER = "BIG_HIT";// 접근 user id
//		final String DB_PASSWD = "bighit1119";// 접근 비번
//		// 1.JDBC DRIVER LOADING
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		conn  = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
//		return conn;
//	}
//	
//	public int doUpdate(DTO dto) {
//		int flag = 0;
//		ReminderVO inVO = (ReminderVO) dto;
//		
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			connection = getConnection();
//			connection.setAutoCommit(true);
//			StringBuilder sb = new StringBuilder();
//			sb.append("UPDATE reminder         \n");
//			sb.append("SET                     \n");
//			sb.append("    remind_time = TO_DATE(?,'yyyy/MM/dd hh24:mi'),    \n");
//			sb.append("    reg_dt = sysdate    \n");
//			sb.append("WHERE                   \n");
//			sb.append("    remind_id = ?       \n");
//			
//			LOG.debug("---------------------------");
//			LOG.debug("-sql-\n" + sb.toString());
//			LOG.debug("-param-\n" + inVO);
//			LOG.debug("---------------------------");
//			
//			pstmt = connection.prepareStatement(sb.toString());
//			pstmt.setString(1, inVO.getRemindTime());
//			pstmt.setString(2, inVO.getRemindId());
//			
//			flag = pstmt.executeUpdate();
//			LOG.debug("---------------------------");
//			LOG.debug("-doUpdate flag-" + flag);
//			LOG.debug("---------------------------");
//			
//			
//		} catch (Exception e) {
//			LOG.debug("---------------");
//			LOG.debug(e.getMessage());
//			LOG.debug("---------------");
//			e.printStackTrace();
//		} finally {
//			if (null != pstmt) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					LOG.debug("---------------------");
//					LOG.debug("-PreparedStatement close--" + e.getMessage());
//					LOG.debug("---------------------");
//					e.printStackTrace();
//				}
//			}
//			if (null != connection) {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					LOG.debug("---------------------");
//					LOG.debug("-Connection close--" + e.getMessage());
//					LOG.debug("---------------------");
//					e.printStackTrace();
//				}
//			}
//		}
//		return flag;
//	}

	/**
	 * need remindId
	 */
	public DTO doSelectOne(DTO dto) {
		ReminderVO inVO = (ReminderVO) dto;
		ReminderVO outVO = null;
		Object[] args = {
				inVO.getRemindId()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT                 \n");
		sb.append("    remind_id,         \n");
		sb.append("    thr_key,           \n");
		sb.append("    remind_time,       \n");
		sb.append("    reg_id,            \n");
		sb.append("    reg_dt             \n");
		sb.append("FROM                   \n");
		sb.append("    reminder           \n");
		sb.append("WHERE                  \n");
		sb.append("    remind_id = ?      \n");
		
		LOG.debug("---------------------------");
		LOG.debug("-sql-\n" + sb.toString());
		LOG.debug("-param-\n" + inVO);
		LOG.debug("---------------------------");
		
		outVO = (ReminderVO) this.jdbcTemplate.queryForObject(sb.toString(), args, rowMapper);
		
		LOG.debug("---------------------------");
		LOG.debug("-doSelectOne outVO-" + outVO);
		LOG.debug("---------------------------");
		
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

