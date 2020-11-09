package com.bighit.on.reaction;

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
import com.bighit.on.mention.MentionDaoImpl;
import com.bighit.on.mention.MentionVO;
import com.bighit.on.save.SaveThrVO;
import com.bighit.on.thread.ThreadDao;
import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;


@Repository("ReactionDaoImpl")
public class ReactionDaoImpl {
	final static Logger   LOG = LoggerFactory.getLogger(MentionDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "com.bighit.on.reaction";
	
	/**
	 * 반응 기능
	 * 키는 반응키 누른 사람,반응 종류,반응을 한 쓰레드
	 * 저장은 반응을 받은 사람(쓰레드 작성자)의 워크스페이스 상에서
	 * @param reactionVO
	 * @return
	 */
	public int doInsert(ReactionVO reactionVO)
	{
		LOG.debug("=====================");
		LOG.debug("=doInsert=");
		LOG.debug("=====================");
		//등록 : namespace+id = com.bighit.on.channel.doInsert
		String statement = NAMESPACE +".doInsert";
		LOG.debug("=statement="+statement);
		LOG.debug("=param ==="+reactionVO);
		
		int flag = sqlSessionTemplate.insert(statement, reactionVO);
		LOG.debug("-doInsert flag=" + flag);
		return flag;		
	}
	/**
	 * 반응 삭제(서비스에선 중복 검사후 있으면)
	 * @param reactionVO
	 * @return
	 */
	public int doDelete(ReactionVO reactionVO)
	{
		LOG.debug("=====================");
		LOG.debug("=doInsert=");
		LOG.debug("=====================");
		String statement = NAMESPACE +".doDelete";
		LOG.debug("=statement="+statement);
		LOG.debug("=param ==="+reactionVO);
		
		int flag = sqlSessionTemplate.delete(statement, reactionVO);
		LOG.debug("-doInsert flag=" + flag);
		return flag;			
	}
	/**
	 * 쓰레드키를 참조하여 해당 쓰레드의 
	 * 반응들 다건 조회
	 * @param inVO
	 * @return 
	 */
	public List<ReactionVO> doSelectList(ReactionVO inVO){
		LOG.debug("=====================");
		LOG.debug("=doSelectList=");
		LOG.debug("=====================");
		//등록 : namespace+id = com.sist.ehr.channel.doSelectList
		String statement = NAMESPACE +".doSelectList";		
		LOG.debug("=statement="+statement);
		LOG.debug("-param-\n" + inVO);
		
		List<ReactionVO> list = this.sqlSessionTemplate.selectList(statement, inVO);
		
		for(ReactionVO vo : list) {
			LOG.debug("===========================");
			LOG.debug("=doSelectList vo="+vo);
			LOG.debug("===========================");
		}
		
		return list;	
	}
	/**
	 * 유저 키를 참조하여 
	 * 언급 당한 유저의 워크스페이스에서 
	 * 언급 다건조회 
	 * @param inVO
	 * @return ThreadVO list
	 */
//	public List<ThreadVO> doSelectList(UsersVO inVO){
//		List<ThreadVO> outList = null;
//		Object[] args = {
//				inVO.getUser_serial()
//		};
//		StringBuilder sb = new StringBuilder();
//		sb.append("SELECT *                                                 \n");
//		sb.append("FROM thread a JOIN (SELECT DISTINCT t.thr_key as thr_key \n");     
//		sb.append("					FROM thread t JOIN reaction r           \n");
//		sb.append("					ON  r.res_id = ?                        \n");
//		sb.append("					and r.thr_key = t.thr_key) b            \n");
//		sb.append("ON a.thr_key = b.thr_key									\n");
//		outList = this.jdbcTemplate.query(sb.toString(), args, threadDao.getRowMapper() );
//		return outList;
//	}
}
