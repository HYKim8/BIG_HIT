package com.bighit.on.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bighit.on.channel.ChannelVO;


@Repository("UsersDaoImpl")
public class UsersDaoImpl {
	 final static Logger   LOG = LoggerFactory.getLogger(UsersDaoImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;
	RowMapper rowMapper = new RowMapper<UsersVO>() {

		@Override
		public UsersVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UsersVO outVO = new UsersVO();
			
			outVO.setUser_serial(rs.getString("user_serial"));
			outVO.setWs_link(rs.getString("ws_link"));
			outVO.setEmail(rs.getString("email"));
			outVO.setPassword(rs.getString("password"));
			outVO.setName(rs.getString("name"));
			outVO.setNickname(rs.getString("nickname"));
			outVO.setProfile_img(rs.getString("profile_img"));
			outVO.setPosition(rs.getString("position"));
			outVO.setPhone_num(rs.getString("phone_num"));
			outVO.setCountry(rs.getInt("country"));
			outVO.setState(rs.getInt("state"));
			outVO.setOnline_state(rs.getInt("online_state"));
			outVO.setReg_id(rs.getString("reg_id"));
			outVO.setReg_dt(rs.getString("reg_dt"));
			
			return outVO;
			
		}
		
	};
	
	public UsersDaoImpl() {}
	
	public List<UsersVO> doSelectList(ChannelVO channelVO){
		List<UsersVO> list  = null;
		
		StringBuilder sb=new StringBuilder();
		sb.append(" SELECT  users.*                                    \n");
		sb.append(" FROM users                                         \n");
		sb.append(" JOIN channel_users                                 \n");
		sb.append(" ON users.user_serial = channel_users.user_serial   \n");
		sb.append(" AND channel_users.ch_link = ?                      \n");
		LOG.debug("========================");
		LOG.debug("=sql\n="+sb.toString());
		LOG.debug("=param="+channelVO);
		LOG.debug("========================");
		
		list = this.jdbcTemplate.query(sb.toString(), new Object[] {channelVO.getChLink()}, rowMapper);
		for(UsersVO vo:list) {
			LOG.debug("====================================");
			LOG.debug("=vo="+vo);
			LOG.debug("====================================");
		}
		
		return list;
				
	}
	
	public UsersVO doSelectOne(String userSerial) throws Exception{
		UsersVO outVO = null; 
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT                   \n");
		sb.append("     user_serial,         \n");
		sb.append("     ws_link,             \n");
		sb.append("     email,               \n");
		sb.append("     password,            \n");
		sb.append("     name,                \n");
		sb.append("     nickname,            \n");
		sb.append("     profile_img,         \n");
		sb.append("     position,            \n");
		sb.append("     phone_num,           \n");
		sb.append("     country,             \n");
		sb.append("     state,               \n");
		sb.append("     online_state,        \n");
		sb.append("     reg_id,              \n");
		sb.append("     reg_dt               \n");
		sb.append(" FROM users               \n");
		sb.append(" WHERE user_serial = ?    \n");
		LOG.debug("========================");
		LOG.debug("=sql="+sb.toString());
		LOG.debug("=param="+userSerial);
		LOG.debug("========================");
		
		Object[] args = {userSerial};
		outVO = (UsersVO) this.jdbcTemplate.queryForObject(sb.toString(), args, rowMapper);
		LOG.debug("outVO = "+outVO);
		
		return outVO;
	}
	
	public int doUpdate(UsersVO usersVO) {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE users              \n");
		sb.append(" SET  email = ?            \n");
		sb.append("     ,password = ?         \n");
		sb.append("     ,name = ?             \n");
		sb.append("     ,nickname = ?         \n");
		sb.append("     ,profile_img = ?      \n");
		sb.append("     ,position = ?         \n");
		sb.append("     ,phone_num = ?        \n");
		sb.append("     ,country = ?          \n");
		sb.append("     ,state = ?            \n");
		sb.append("     ,online_state = ?     \n");
		sb.append(" WHERE user_serial = ?     \n");
		LOG.debug("========================");
		LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("=param="+usersVO);
		LOG.debug("========================");
		
		Object[] args = { usersVO.getEmail(),
						usersVO.getPassword(),
						usersVO.getName(),
						usersVO.getNickname(),
						usersVO.getProfile_img(),
						usersVO.getPosition(),
						usersVO.getPhone_num(),
						usersVO.getCountry(),
						usersVO.getState(),
						usersVO.getOnline_state(),
						usersVO.getUser_serial()
		};
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);
		return flag;
		
	}
	
	public int doDelete(UsersVO usersVO) {
		int flag = 0;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM users        \n");
		sb.append(" WHERE user_serial = ?    \n");
		
		LOG.debug("=====================================");
		LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("=param="+usersVO);
		LOG.debug("=====================================");
		
		Object[] args = {usersVO.getUser_serial()};
		flag = this.jdbcTemplate.update(sb.toString(),args);
		LOG.debug("flag = "+flag);
		
		return flag;
	}
	
	public int doInsert(UsersVO usersVO) {
		int flag = 0;
		
		Object[] args = { 	usersVO.getUser_serial(),
							usersVO.getWs_link(),
							usersVO.getEmail(),
							usersVO.getPassword(),
							usersVO.getName(),
							usersVO.getNickname(),
							usersVO.getProfile_img(),
							usersVO.getPosition(),
							usersVO.getPhone_num(),
							usersVO.getCountry(),
							usersVO.getState(),
							usersVO.getOnline_state(),
							usersVO.getReg_id()	
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO users (      \n");
		sb.append("     user_serial,         \n");
		sb.append("     ws_link,             \n");
		sb.append("     email,               \n");
		sb.append("     password,            \n");
		sb.append("     name,                \n");
		sb.append("     nickname,            \n");
		sb.append("     profile_img,         \n");
		sb.append("     position,            \n");
		sb.append("     phone_num,           \n");
		sb.append("     country,             \n");
		sb.append("     state,               \n");
		sb.append("     online_state,        \n");
		sb.append("     reg_id,              \n");
		sb.append("     reg_dt               \n");
		sb.append(" ) VALUES (               \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                   \n");
		sb.append("     ?,                   \n");
		sb.append("      SYSDATE             \n");
		sb.append(" )                        \n");
		LOG.debug("========================");
		LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("=param="+usersVO);
		LOG.debug("========================");
		
		flag= this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("flag = "+flag);
		
		return flag;

	}
	
}
