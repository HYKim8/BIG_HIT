package com.bighit.on.save;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bighit.on.cmn.DTO;
import com.bighit.on.cmn.DaoInterface;
import com.bighit.on.reminder.ReminderVO;

@Repository("SaveThrDaoImpl")
public class SaveThrDaoImpl {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	RowMapper rowMapper = new RowMapper<SaveThrVO>() {
		@Override
		public SaveThrVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			SaveThrVO outVO = new SaveThrVO();
			
			outVO.setThrKey(rs.getString("thr_key"));
			outVO.setRegDt(rs.getString("reg_dt"));
			outVO.setRegId(rs.getString("reg_id"));
			
			return outVO;
		}
	};
	/**
	 * 쓰레드 저장 
	 * @param saveThrVO
	 * @return
	 */
	
	public int doInsert(SaveThrVO saveThrVO) {
		int flag = 0;
		Object[] args = {
					saveThrVO.getRegId(),
					saveThrVO.getThrKey()				
		};
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO save_thr (   \n");
		sb.append("    reg_id,              \n");
		sb.append("    thr_key,             \n");
		sb.append("    reg_dt               \n");
		sb.append(") VALUES (               \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    sysdate              \n");
		sb.append(")						\n");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		return flag;
	}
	/**
	 * 쓰레드 저장 취소
	 * @param saveThrVO
	 * @return
	 */
	public int doDelete(SaveThrVO saveThrVO) {
		int flag = 0;
		StringBuilder sb = new StringBuilder();
		Object[] args = {
				saveThrVO.getRegId(),
				saveThrVO.getThrKey()				
		};
		sb.append("DELETE FROM save_thr \n");
		sb.append("WHERE                \n");
		sb.append("    reg_id = ?       \n");
		sb.append("    AND thr_key = ?	\n");
		flag = this.jdbcTemplate.update(sb.toString(), args);
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
	
	public List<SaveThrVO> doSelectList(DTO dto) {
		SaveThrVO inVO = (SaveThrVO) dto;
		List<SaveThrVO> outList = null;
		Object[] args = {
			inVO.getRegId()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT                   \n");
		sb.append("    thr_key,             \n");
		sb.append("    reg_id,              \n");
		sb.append("    reg_dt               \n");
		sb.append("FROM                     \n");
		sb.append("    save_thr             \n");
		sb.append("WHERE                    \n");
		sb.append("    reg_id = ?           \n");
		outList = this.jdbcTemplate.query(sb.toString(), args, rowMapper);
		return outList;
	}

}
