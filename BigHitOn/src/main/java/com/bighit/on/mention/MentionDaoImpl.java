package com.bighit.on.mention;

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

import com.bighit.on.channel.ChannelDaoImpl;
import com.bighit.on.channel.ChannelVO;
import com.bighit.on.thread.ThreadDao;
import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;


@Repository("MentionDaoImpl")
public class MentionDaoImpl {
	final static Logger   LOG = LoggerFactory.getLogger(MentionDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "com.bighit.on.mention";
	/**
	 * 언급
	 * @param mentionVO
	 * @return
	 */
	public int doInsert(MentionVO mentionVO)
	{
		LOG.debug("=====================");
		LOG.debug("=doInsert=");
		LOG.debug("=====================");
		//등록 : namespace+id = com.bighit.on.channel.doInsert
		String statement = NAMESPACE +".doInsert";
		LOG.debug("=statement="+statement);
		LOG.debug("=param ==="+mentionVO);
		
		int flag = sqlSessionTemplate.insert(statement, mentionVO);
		LOG.debug("-doInsert flag=" + flag);
		return flag;		
	}
	/**
	 * 언급 삭제(쓰레드 수정or삭제시)
	 * @param mentionVO
	 * @return
	 */
	public int doDelete(MentionVO mentionVO)
	{
		LOG.debug("=====================");
		LOG.debug("=doInsert=");
		LOG.debug("=====================");
		//등록 : namespace+id = com.bighit.on.channel.doInsert
		String statement = NAMESPACE +".doDelete";
		LOG.debug("=statement="+statement);
		LOG.debug("=param ==="+mentionVO);
		
		int flag = sqlSessionTemplate.delete(statement, mentionVO);
		LOG.debug("-doInsert flag=" + flag);
		return flag;	
	}
	/**
	 * 멘션VO 쓰레드 키를 참조하여 
	 * 해당 쓰레드의 언급들 다건 조회 
	 * @param inVO
	 * @return
	 */
	public List<MentionVO> doSelectList(MentionVO inVO){
		LOG.debug("=====================");
		LOG.debug("=doSelectList=");
		LOG.debug("=====================");
		//등록 : namespace+id = com.sist.ehr.channel.doSelectList
		String statement = NAMESPACE +".doSelectList";		
		LOG.debug("=statement="+statement);
		LOG.debug("-param-\n" + inVO);
		
		List<MentionVO> list = this.sqlSessionTemplate.selectList(statement, inVO);
		
		for(MentionVO vo : list) {
			LOG.debug("===========================");
			LOG.debug("=doSelectList vo="+vo);
			LOG.debug("===========================");
		}
		
		return list;
	}
	/**
	 * 유저키를 조회하여 
	 * 언급당한 유저의 워크스페이스에 다건 조회
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
//		sb.append("					FROM thread t JOIN mention m           \n");
//		sb.append("					ON  m.res_id = ?                        \n");
//		sb.append("					and m.thr_key = t.thr_key) b            \n");
//		sb.append("ON a.thr_key = b.thr_key									\n");
//		outList = this.jdbcTemplate.query(sb.toString(), args, threadDao.getRowMapper() );
//		return outList;
//	}
}
