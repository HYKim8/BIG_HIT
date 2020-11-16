package com.bighit.on.channelusers;

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


@Repository("ChannelUsersDao")
public class ChannelUsersDao {
	final static Logger LOG = LoggerFactory.getLogger(ChannelUsersDao.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "com.bighit.on.channelusers";
	
//	RowMapper rowMapper = new RowMapper<ChannelUsersVO>() {
//		@Override
//		public ChannelUsersVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//			ChannelUsersVO outVO = new ChannelUsersVO();
//			outVO.setChLink(rs.getString("ch_link"));
//			outVO.setUserSerial(rs.getString("user_serial"));
//			outVO.setNotification(rs.getInt("notification"));
//			return outVO;
//		}
//	};
	public int doUpdate(ChannelUsersVO inVO)
	{
		return sqlSessionTemplate.update(NAMESPACE+".doUpdate",inVO);		
	}
	/**
	 * 유저가 속한 워크스페이스의 일반,랜덤 채널에 유저를 소속시킨다.
	 * @param inVO
	 * @return
	 */
	public int doWorkSpaceInsert(UsersVO inVO)
	{
		
		return sqlSessionTemplate.insert(NAMESPACE+".generalInsert",inVO) == 1 
				&& sqlSessionTemplate.insert(NAMESPACE+".randomInsert",inVO) ==1 ? 1:0;		
	}
	/**
	 * doInsert
	 * l
	 * @param inVO
	 * @return
	 */
	public int doInsert(ChannelUsersVO inVO)
		{
			LOG.debug("=====================");
			LOG.debug("=doInsert=");
			LOG.debug("=====================");
			String statement = NAMESPACE +".doInsert";
			LOG.debug("=statement="+statement);
			LOG.debug("=param ==="+inVO);
			
			int flag = sqlSessionTemplate.insert(statement, inVO);
			LOG.debug("-doInsert flag=" + flag);
			return flag;		
		}
	/**
	 * do Delete
	 * @param inVO
	 * @return
	 */
	public int doDelete(ChannelUsersVO inVO) 
		{
			LOG.debug("=====================");
			LOG.debug("=doInsert=");
			LOG.debug("=====================");
			String statement = NAMESPACE +".doDelete";
			LOG.debug("=statement="+statement);
			LOG.debug("=param ==="+inVO);
			
			int flag = sqlSessionTemplate.delete(statement, inVO);
			LOG.debug("-doInsert flag=" + flag);
			return flag;			
		}
	/**
	 * 채널VO 입력시 
	 * 해당 채널에 걸려있는 모든 행 삭제,
	 * 유저VO 입력시 
	 * 해당 유저한테 걸려있는 모든 행 삭제,
	 * @param dto
	 * @return
	 */
		public int doDeleteAll(ChannelVO inVO) {
			return sqlSessionTemplate.delete(NAMESPACE +".doDeleteAllInUser", inVO);		
		}
		public int doDeleteAll(UsersVO inVO) {
			return sqlSessionTemplate.delete(NAMESPACE +".doDeleteAllInUsers", inVO);		
		}
	}
