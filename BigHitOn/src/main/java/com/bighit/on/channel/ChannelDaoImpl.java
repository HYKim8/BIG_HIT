package com.bighit.on.channel;

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

import com.bighit.on.channelusers.ChannelUsersVO;
import com.bighit.on.cmn.DTO;
import com.bighit.on.cmn.Search;
import com.bighit.on.user.dao.UsersDaoImpl;
import com.bighit.on.user.dao.UsersVO;

@Repository("ChannelDaoImpl")
public class ChannelDaoImpl {
	final static Logger   LOG = LoggerFactory.getLogger(ChannelDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	UsersDaoImpl userdao; 
	
	private final String NAMESPACE = "com.bighit.on.channel";
	
	RowMapper rowMapper = new RowMapper<ChannelVO>() {
		@Override
		public ChannelVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChannelVO outVO = new ChannelVO();
			outVO.setChLink(rs.getString("ch_link"));
			outVO.setWsLink(rs.getString("ws_link"));
			outVO.setChName(rs.getString("ch_name"));
			outVO.setTopic(rs.getString("topic"));
			outVO.setChDescription(rs.getString("ch_description"));
			outVO.setChAccess(rs.getString("ch_access"));
			outVO.setRegId(rs.getString("reg_id"));
			outVO.setRegDt(rs.getString("reg_dt"));
			
			return outVO;
			
		}
	};
	
	/**
	 * 채널 생성
	 * @param channelVO
	 * @return
	 */
	public int doInsert(ChannelVO channelVO) {
		LOG.debug("=====================");
		LOG.debug("=doInsert=");
		LOG.debug("=====================");
		//등록 : namespace+id = com.bighit.on.channel.doInsert
		String statement = NAMESPACE +".doInsert";
		LOG.debug("=statement="+statement);
		LOG.debug("=param ==="+channelVO);
		
		int flag = sqlSessionTemplate.insert(statement, channelVO);
		sqlSessionTemplate.update(NAMESPACE+".updateKey");
		LOG.debug("-doInsert flag=" + flag);
		return flag;
	}
	
	/**
	 * 채널 삭제
	 * @param channelVO
	 * @return
	 */
	public int doDelete(ChannelVO channelVO) {
		LOG.debug("=====================");
		LOG.debug("=doDelete=");
		LOG.debug("=====================");
		//삭제 : namespace+id = com.bighit.on.channel.doDelete
		String statement = NAMESPACE +".doDelete";
		LOG.debug("=statement="+statement);
		LOG.debug("=param=="+channelVO);
		
		int flag = sqlSessionTemplate.delete(statement,channelVO);
		LOG.debug("-doDelete flag==" + flag);
		
		return flag;
	}
	
	/**
	 * 채널 나가기
	 * @param channelVO
	 * @return
	 */
	public int doDelete2(ChannelUsersVO channelUsersVO) {
		LOG.debug("=====================");
		LOG.debug("=doDelete=");
		LOG.debug("=====================");
		//삭제 : namespace+id = com.bighit.on.channel.doDelete
		String statement = NAMESPACE +".doDelete2";
		LOG.debug("=statement="+statement);
		LOG.debug("=param=="+channelUsersVO);
		
		int flag = sqlSessionTemplate.delete(statement,channelUsersVO);
		LOG.debug("-doDelete flag==" + flag);
		
		return flag;
	}
	
	/**
	 * 채널 단건조회
	 * @param chLink
	 * @return
	 */
	public ChannelVO doSelectOne(ChannelVO channel) {
		LOG.debug("=====================");
		LOG.debug("=doSelectOne=");
		LOG.debug("=====================");	
		
		//단건조회 : namespace+id = com.bighit.on.channel.doSelectOne		
		String statement = NAMESPACE +".doSelectOne";
		LOG.debug("=statement="+statement);
		LOG.debug("=param=="+channel);
		
		Object args[] = {channel.getChLink()};
		ChannelVO outVO = sqlSessionTemplate.selectOne(statement,channel);
		LOG.debug("=doSelectOne outVO="+outVO);
		
		return outVO;
		
	}
	
	/**
	 * 채널'일반' 단건조회
	 * @param chLink
	 * @return
	 */
	public ChannelVO doSelectOne2(String wsLink) {
//		LOG.debug("=====================");
//		LOG.debug("=doSelectOne=");
//		LOG.debug("=====================");	
//		
//		//단건조회 : namespace+id = com.bighit.on.channel.doSelectOne		
//		String statement = NAMESPACE +".doSelectOne2";
//		LOG.debug("=statement="+statement);
//		LOG.debug("=param=="+wsLink);
//		
//		Object args[] = {wsLink};
//		ChannelVO outVO = sqlSessionTemplate.selectOne( NAMESPACE +".doSelectOne2",wsLink);
//		LOG.debug("=doSelectOne outVO="+outVO);
//		
		return sqlSessionTemplate.selectOne( NAMESPACE +".doSelectOne2",wsLink);
		
	}
	
	
	/**
	 * 채널 리스트
	 * @param channelVO
	 * @return
	 */
	public List<ChannelVO> doSelectList(ChannelVO channelVO) {
		LOG.debug("=====================");
		LOG.debug("=doSelectList=");
		LOG.debug("=====================");
		//등록 : namespace+id = com.sist.ehr.channel.doSelectList
		String statement = NAMESPACE +".doSelectList";		
		LOG.debug("=statement="+statement);
		LOG.debug("-param-\n" + channelVO);
		
		List<ChannelVO> list = this.sqlSessionTemplate.selectList(statement, channelVO);
		
		for(ChannelVO vo : list) {
			LOG.debug("===========================");
			LOG.debug("=doSelectList vo="+vo);
			LOG.debug("===========================");
		}
		
		return list;
		
	}
	
	/**
	 * 
	 * @param channelVO
	 * @return
	 */	
	public List<ChannelVO> doSelectList(Search search) {
		if(search.nonValueDiv() || search.nonValueWord() 
				|| ( search.getSearchDiv() !="10" && search.getSearchDiv() !="20" ) ) return null;
		
		UsersVO user = new UsersVO();
		
		user.setUser_serial(search.getSearchWord());
		
		user = userdao.doSelectOne(user.getUser_serial());
		LOG.debug("=====================");
		LOG.debug("=doSelectList=");
		LOG.debug("=====================");
		//div가 10 이면 ch리스트 20이면 dm리스트
		String statement = search.getSearchDiv() == "10" ? NAMESPACE + ".chlistFromUser" : NAMESPACE + ".dmlistFromUser";		
		LOG.debug("=statement="+statement);
		LOG.debug("-param-\n" + user);
		
		List<ChannelVO> list = this.sqlSessionTemplate.selectList(statement, user);
		
		for(ChannelVO vo : list) {
			LOG.debug("===========================");
			LOG.debug("=doSelectList vo="+vo);
			LOG.debug("===========================");
		}
		
		return list;
		
	}
	
	/**
	 * 아직 사용되지 않은 배정될 키 리턴 
	 * @return
	 */
	public String doGetKey() {
		return sqlSessionTemplate.selectOne(NAMESPACE+".getNowKey");		
	}
	
}
