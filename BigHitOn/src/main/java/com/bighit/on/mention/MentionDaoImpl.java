package com.bighit.on.mention;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bighit.on.thread.ThreadDao;
import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;


@Repository("MentionDaoImpl")
public class MentionDaoImpl {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	ThreadDao threadDao;
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
	/**
	 * 멘션VO 쓰레드 키를 참조하여 
	 * 해당 쓰레드의 언급들 다건 조회 
	 * @param inVO
	 * @return
	 */
	public List<MentionVO> doSelectList(MentionVO inVO){
		List<MentionVO> outList = null;
		Object[] args = {
			inVO.getThrKey()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT                   \n");
		sb.append("    res_id,             \n");
		sb.append("    thr_key,             \n");
		sb.append("    reg_id,              \n");
		sb.append("    reg_dt               \n");
		sb.append("FROM                     \n");
		sb.append("    mention             \n");
		sb.append("WHERE                    \n");
		sb.append("    thr_key = ?           \n");
		outList = this.jdbcTemplate.query(sb.toString(), args, rowMapper);
		return outList;		
	}
	/**
	 * 유저키를 조회하여 
	 * 언급당한 유저의 워크스페이스에 다건 조회
	 * @param inVO
	 * @return ThreadVO list
	 */
	public List<ThreadVO> doSelectList(UsersVO inVO){
		List<ThreadVO> outList = null;
		Object[] args = {
				inVO.getUser_serial()
		};
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT *                                                 \n");
		sb.append("FROM thread a JOIN (SELECT DISTINCT t.thr_key as thr_key \n");     
		sb.append("					FROM thread t JOIN mention m           \n");
		sb.append("					ON  m.res_id = ?                        \n");
		sb.append("					and m.thr_key = t.thr_key) b            \n");
		sb.append("ON a.thr_key = b.thr_key									\n");
		outList = this.jdbcTemplate.query(sb.toString(), args, threadDao.getRowMapper() );
		return outList;
	}
}
