package com.bighit.on.reaction;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("ReactionDaoImpl")
public class ReactionDaoImpl {
	@Autowired
	JdbcTemplate jdbcTemplate;
	RowMapper rowMapper = new RowMapper<ReactionVO>() {
		@Override
		public ReactionVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReactionVO outVO = new ReactionVO();
			outVO.setEmoji(rs.getInt("emoji"));
			outVO.setResId(rs.getString("res_id"));
			outVO.setThrKey(rs.getString("thr_key"));
			outVO.setRegDt(rs.getString("reg_dt"));
			outVO.setRegId(rs.getString("reg_id"));
			
			return outVO;
		}
	};
	/**
	 * 반응 기능
	 * 키는 반응키 누른 사람,반응 종류,반응을 한 쓰레드
	 * 저장은 반응을 받은 사람(쓰레드 작성자)의 워크스페이스 상에서
	 * @param reactionVO
	 * @return
	 */
	public int doInsert(ReactionVO reactionVO)
	{
		int flag =0;
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO reaction (   \n");
		sb.append("    emoji,              	\n");
		sb.append("    thr_key,             \n");
		sb.append("    res_id,              \n");		
		sb.append("    reg_id,              \n");
		sb.append("    reg_dt               \n");
		sb.append(") VALUES (               \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    ?,                   \n");
		sb.append("    sysdate              \n");
		sb.append(")						\n");
		
		Object[] args = {	
				reactionVO.getEmoji(),
				reactionVO.getThrKey(),
				reactionVO.getResId(),				
				reactionVO.getRegId()
		};
		flag = this.jdbcTemplate.update(sb.toString(), args);
		return flag;		
	}
	/**
	 * 반응 삭제(서비스에선 중복 검사후 있으면)
	 * @param reactionVO
	 * @return
	 */
	public int doDelete(ReactionVO reactionVO)
	{
		int flag =0;
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM reaction  \n");
		sb.append("WHERE                \n");
		sb.append("    reg_id = ?       \n");
		sb.append("    AND thr_key = ?	\n");
		sb.append("    AND emoji = ?	\n");
		Object[] args = {				
				reactionVO.getRegId(),
				reactionVO.getThrKey(),
				reactionVO.getEmoji()
		};
		flag = this.jdbcTemplate.update(sb.toString(), args);
		return flag;		
	}
}
