package com.bighit.on.reaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bighit.on.cmn.DTO;
import com.bighit.on.save.SaveThrVO;
import com.bighit.on.thread.ThreadDao;
import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;


@Repository("ReactionDaoImpl")
public class ReactionDaoImpl {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	ThreadDao threadDao;
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
	/**
	 * 쓰레드키를 참조하여 해당 쓰레드의 
	 * 반응들 다건 조회
	 * @param inVO
	 * @return 
	 */
	public List<ReactionVO> doSelectList(ReactionVO inVO){
		List<ReactionVO> outList = null;
		Object[] args = {
			inVO.getThrKey()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT                   \n");
		sb.append("    emoji,             \n");
		sb.append("    res_id,             \n");
		sb.append("    thr_key,             \n");
		sb.append("    reg_id,              \n");
		sb.append("    reg_dt               \n");
		sb.append("FROM                     \n");
		sb.append("    reaction             \n");
		sb.append("WHERE                    \n");
		sb.append("    thr_key = ?           \n");
		outList = this.jdbcTemplate.query(sb.toString(), args, rowMapper);
		return outList;		
	}
	/**
	 * 유저 키를 참조하여 
	 * 언급 당한 유저의 워크스페이스에서 
	 * 언급 다건조회 
	 * @param inVO
	 * @return ThreadVO list
	 */
	public List<ThreadVO> doSelectList(UsersVO inVO){
		List<ThreadVO> outList = null;
		Object[] args = {
				inVO.getUser_serial()
		};
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT *                                         \n");
		sb.append("FROM thread                                      \n");
		sb.append("WHERE                                            \n");
		sb.append("    thr_key = (SELECT DISTINCT t.thr_key         \n");
		sb.append("                FROM thread t JOIN reaction r    \n");
		sb.append("                ON  r.res_id = ?                 \n");
		sb.append("                and r.thr_key = t.thr_key)		\n");
		outList = this.jdbcTemplate.query(sb.toString(), args, threadDao.getRowMapper() );
		return outList;
	}
}
