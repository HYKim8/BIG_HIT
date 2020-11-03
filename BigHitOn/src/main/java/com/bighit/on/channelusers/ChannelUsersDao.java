package com.bighit.on.channelusers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bighit.on.channel.ChannelVO;
import com.bighit.on.cmn.DTO;
import com.bighit.on.user.dao.UsersVO;


@Repository("ChannelUsersDao")
public class ChannelUsersDao {
	final static Logger LOG = LoggerFactory.getLogger(ChannelUsersDao.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	RowMapper rowMapper = new RowMapper<ChannelUsersVO>() {
		@Override
		public ChannelUsersVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChannelUsersVO outVO = new ChannelUsersVO();
			outVO.setChLink(rs.getString("ch_link"));
			outVO.setUserSerial(rs.getString("user_serial"));
			outVO.setNotification(rs.getInt("notification"));
			return outVO;
		}
	};
	/**
	 * doInsert
	 * 
	 * @param inVO
	 * @return
	 */
	public int doInsert(ChannelUsersVO inVO) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO channel_users (  \n");
		sb.append("    ch_link,                 \n");
		sb.append("    user_serial,             \n");
		sb.append("    notification             \n");
		sb.append(") VALUES (                   \n");
		sb.append("    ?,                       \n");
		sb.append("    ?,                       \n");
		sb.append("    ?                        \n");
		sb.append(")							\n");
		Object args[] = {inVO.getChLink(),inVO.getUserSerial(),inVO.getNotification() };
		int flag = this.jdbcTemplate.update(sb.toString(), args);
		return flag;
	}
	/**
	 * do Delete
	 * @param inVO
	 * @return
	 */
	public int doDelete(ChannelUsersVO inVO) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM channel_users  \n");
		sb.append("WHERE                      \n");
		sb.append("    ch_link = ?            \n");
		sb.append("    AND user_serial = ?	  \n");
		Object args[] = {inVO.getChLink(),inVO.getUserSerial()};
		int flag = this.jdbcTemplate.update(sb.toString(), args);
		return flag;
	}
	/**
	 * 채널VO 입력시 
	 * 해당 채널에 걸려있는 모든 행 삭제,
	 * 유저VO 입력시 
	 * 해당 유저한테 걸려있는 모든 행 삭제
	 * @param dto
	 * @return
	 */
	public int doDeleteAll(DTO dto) {
		if(!(dto instanceof ChannelVO) && !(dto instanceof UsersVO)) {
			return 0;
		}
		StringBuilder sb = new StringBuilder();
		Object arg = null;
		sb.append("DELETE FROM channel_users  \n");
		sb.append("WHERE                      \n");		
		if(dto instanceof ChannelVO) {
			sb.append("    ch_link = ?            \n");
			arg = ((ChannelVO)dto).getChLink();
		}
		else {
			sb.append("    user_serial = ?	  	  \n");
			arg = ((UsersVO)dto).getUser_serial();
		}
		int flag = this.jdbcTemplate.update(sb.toString(), arg);
		return flag;		
	}
	
}
