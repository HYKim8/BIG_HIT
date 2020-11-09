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
		flag = this.sqlSessionTemplate.update(sb.toString(), args);
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
		
		flag = this.sqlSessionTemplate.update(sb.toString(), args);
		return flag;		
	}
	/**
	 * 채널 VO입력시
	 * 해당 채널의 걸려있는 모든 행 삭제
	 * 커맨드VO 입력시
	 * 해당 커맨드가 있는 모든 행 삭제
	 * @param dto
	 * @return
	 */
	public int doDeleteAll(DTO dto) {
		if(!(dto instanceof ChannelVO) && !(dto instanceof CommandVO)) {
			return 0;
		}
		
		
		StringBuilder sb = new StringBuilder();
		Object arg =null;
		sb.append("DELETE FROM channel_command  \n");
		if(dto instanceof ChannelVO) {
			sb.append("WHERE ch_link = ?            \n");
			arg = ((ChannelVO)dto).getChLink();
		}
		else {
			sb.append("WHERE com_id = ?             \n");
			arg = ((CommandVO)dto).getComId();
		}		
		int flag = this.sqlSessionTemplate.update(sb.toString(), arg);
		return flag;		
	}
}