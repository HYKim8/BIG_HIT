package com.bighit.on.mention;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("MentionDaoImpl")
public class MentionDaoImpl {
	@Autowired
	JdbcTemplate jdbcTemplate;
	RowMapper rowMapper = new RowMapper<MentionVO>() {
		@Override
		public MentionVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			MentionVO outVO = new MentionVO();
			outVO.setResId(rs.getString("res_id"));
			outVO.setThrKey(rs.getString("thr_key"));
			outVO.setRegDt(rs.getString("reg_dt"));
			outVO.setRegId(rs.getString("reg_id"));
			
			return outVO;
		}
	};
	/**
	 * 언급
	 * @param mentionVO
	 * @return
	 */
	public int doInsert(MentionVO mentionVO)
	{
		int flag =0;
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO mention (    \n");
		sb.append("    res_id,              \n");
		sb.append("    thr_key,             \n");
		sb.append("    reg_id,              \n");
		sb.append("    reg_dt               \n");
		sb.append(") VALUES (               \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    sysdate              \n");
		sb.append(")						\n");
		
		Object[] args = {				
				mentionVO.getResId(),
				mentionVO.getThrKey(),
				mentionVO.getRegId()
		};
		flag = this.jdbcTemplate.update(sb.toString(), args);
		return flag;		
	}
	/**
	 * 언급 삭제(쓰레드 수정or삭제시)
	 * @param mentionVO
	 * @return
	 */
	public int doDelete(MentionVO mentionVO)
	{
		int flag =0;
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM mention  \n");
		sb.append("WHERE                \n");
		sb.append("    res_id = ?       \n");
		sb.append("    AND thr_key = ?	\n");
		
		Object[] args = {				
				mentionVO.getResId(),
				mentionVO.getThrKey()
		};
		flag = this.jdbcTemplate.update(sb.toString(), args);
		return flag;		
	}
}
