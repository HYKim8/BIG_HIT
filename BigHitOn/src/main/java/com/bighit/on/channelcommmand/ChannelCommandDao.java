package com.bighit.on.channelcommmand;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("ChannelCommandDao")
public class ChannelCommandDao {
	final static Logger   LOG = LoggerFactory.getLogger(ChannelCommandDao.class);
	//
	@Autowired
	JdbcTemplate jdbcTemplate;
	RowMapper rowMapper = new RowMapper<ChannelCommandVO>() {
		@Override
		public ChannelCommandVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChannelCommandVO outVO = new ChannelCommandVO();
			outVO.setChLink(rs.getString("ch_link"));
			outVO.setComId(rs.getInt("com_id"));
			return outVO;
		}
	};
	/**
	 * 채널링크 생성
	 * @param channelLinkVO
	 * @return
	 */
	public int doInsert(ChannelCommandVO channelCommandVO) {
		int flag=0;
		Object[] args = {channelCommandVO.getChLink(),
						 channelCommandVO.getComId()
				
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO channel_command ( \n");
		sb.append("    ch_link,                   \n");
		sb.append("    com_id                     \n");
		sb.append(" ) VALUES (                    \n");
		sb.append("    ?,                         \n");
		sb.append("    ?                          \n");
		sb.append(" )                             \n");
		flag = this.jdbcTemplate.update(sb.toString(), args);
		return flag;
	}
	
	/**
	 * 
	 */
	public int doDelete(ChannelCommandVO channelCommandVO) {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM channel_command  \n");
		sb.append("WHERE ch_link = ?            \n");
		sb.append("    AND com_id = ?           \n");
		
		Object[] args = {channelCommandVO.getChLink(),
				 channelCommandVO.getComId()
		};
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		return flag;
		
		
	}
}