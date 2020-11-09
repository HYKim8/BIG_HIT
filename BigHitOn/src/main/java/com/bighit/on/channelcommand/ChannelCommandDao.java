package com.bighit.on.channelcommand;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bighit.on.channel.ChannelVO;
import com.bighit.on.cmn.DTO;
import com.bighit.on.command.CommandVO;
import com.bighit.on.user.dao.UsersVO;

@Repository("ChannelCommandDao")
public class ChannelCommandDao {
	final static Logger   LOG = LoggerFactory.getLogger(ChannelCommandDao.class);
	//
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "com.bighit.on.channelcommand";
	
	//RowMapper rowMapper = new RowMapper<ChannelCommandVO>() {
	//	@Override
	//	public ChannelCommandVO mapRow(ResultSet rs, int rowNum) throws SQLException {
	//		ChannelCommandVO outVO = new ChannelCommandVO();
	//		outVO.setChLink(rs.getString("ch_link"));
	//		outVO.setComId(rs.getInt("com_id"));
	//		return outVO;
	//	}
	//};
	/**
	 * 채널링크 생성
	 * @param channelLinkVO
	 * @return
	 */
	public int doInsert(ChannelCommandVO channelCommandVO) 
	{
		LOG.debug("=====================");
		LOG.debug("=doInsert=");
		LOG.debug("=====================");
		String statement = NAMESPACE +".doInsert";
		LOG.debug("=statement="+statement);
		LOG.debug("=param ==="+channelCommandVO);
		
		int flag = sqlSessionTemplate.insert(statement, channelCommandVO);
		LOG.debug("-doInsert flag=" + flag);
		return flag;		
				
	}
	
	/**
	 * 
	 */
	public int doDelete(ChannelCommandVO channelCommandVO) {
		{
			LOG.debug("=====================");
			LOG.debug("=doInsert=");
			LOG.debug("=====================");
			String statement = NAMESPACE +".doDelete";
			LOG.debug("=statement="+statement);
			LOG.debug("=param ==="+channelCommandVO);
			
			int flag = sqlSessionTemplate.delete(statement, channelCommandVO);
			LOG.debug("-doInsert flag=" + flag);
			return flag;			
		}		
	}
	/**
	 * 채널 VO입력시
	 * 해당 채널의 걸려있는 모든 행 삭제
	 * 커맨드VO 입력시
	 * 해당 커맨드가 있는 모든 행 삭제
	 * @param dto
	 * @return
	 */
	public int doDeleteAll(ChannelVO inVO) {
		return sqlSessionTemplate.delete(NAMESPACE +".doDeleteAllInChannel", inVO);		
	}
	public int doDeleteAll(CommandVO inVO) {
		return sqlSessionTemplate.delete(NAMESPACE +".doDeleteAllInCommand", inVO);		
	}
}