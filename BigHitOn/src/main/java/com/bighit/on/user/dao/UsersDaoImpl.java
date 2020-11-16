package com.bighit.on.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bighit.on.channel.ChannelVO;
import com.bighit.on.workspace.WorkSpaceVO;


@Repository("UsersDaoImpl")
public class UsersDaoImpl {
	 final static Logger   LOG = LoggerFactory.getLogger(UsersDaoImpl.class);

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "com.bighit.on.users";
	
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
			outVO.setThumb("thumb");
			return outVO;
			
		}
		
	};
	
	public UsersDaoImpl() {}
	
	/**
	 * 워크스페이스에 소속된 유저들
	 * @param workSpaceVO
	 * @return
	 */
	public List<UsersVO> doSelectList(WorkSpaceVO workSpaceVO){
		LOG.debug("===========================");
		LOG.debug("=doSelectList=");
		LOG.debug("===========================");
		 
		String statement = NAMESPACE + ".doSelectListFromWs";
		LOG.debug("=statement : "+statement);
		LOG.debug("=workSpaceVO="+workSpaceVO);                             

		List<UsersVO> list = this.sqlSessionTemplate.selectList(statement, workSpaceVO);
		
		for(UsersVO vo : list) {
			LOG.debug("=vo="+vo);
		}
		
		return list;
	}
	
	/**
	 * 해당 채널에 소속된 유저들 
	 * @param channelVO
	 * @return
	 */
	public List<UsersVO> doSelectList(ChannelVO channelVO){
		LOG.debug("===========================");
		LOG.debug("=doSelectList=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doSelectListFromCh";
		LOG.debug("=statement : "+statement);
		LOG.debug("=channelVO="+channelVO);
		
		List<UsersVO> list = this.sqlSessionTemplate.selectList(statement, channelVO);
		for(UsersVO vo:list) {
			LOG.debug("=vo="+vo);
		}
		
		return list;
				
	}
	
	public UsersVO doSelectOne(String userSerial) {
		LOG.debug("===========================");
		LOG.debug("=doSelectOne=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doSelectOne";
		LOG.debug("=statement : "+statement);
		LOG.debug("=userSerial="+userSerial);
		
		Object[] args = {userSerial};
		UsersVO outVO = (UsersVO) this.sqlSessionTemplate.selectOne(statement, userSerial);
		LOG.debug("outVO = "+outVO);
		
		return outVO;
	}
	
	public int doUpdate(UsersVO usersVO) {
		LOG.debug("===========================");
		LOG.debug("=doUpdate=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doUpdate";
		LOG.debug("=statement : "+statement);
		LOG.debug("=usersVO : "+usersVO);
		LOG.debug("===========================");
		
		int flag = sqlSessionTemplate.update(statement,usersVO);
		LOG.debug("=flag="+flag);
		
		return flag;
		
	}
	
	public int doDelete(UsersVO usersVO) {
		LOG.debug("===========================");
		LOG.debug("=doDelete=");
		LOG.debug("===========================");
		
		String statement = NAMESPACE + ".doDelete";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=usersVO : "+usersVO);
		LOG.debug("===========================");
		
		int flag = sqlSessionTemplate.delete(statement, usersVO);
		LOG.debug("=flag="+flag);
		
		return flag;
	}
	
	public int doInsert(UsersVO usersVO) {
		LOG.debug("===========================");
		LOG.debug("=doInsert=");
		LOG.debug("===========================");
		//등록 : namespace+id = com.sist.ehr.board.doInsert
		String statement = NAMESPACE + ".doInsert";
		LOG.debug("===========================");
		LOG.debug("=statement : "+statement);
		LOG.debug("=usersVO : "+usersVO);
		LOG.debug("===========================");
		
		int flag = sqlSessionTemplate.insert(statement, usersVO);
		sqlSessionTemplate.update(NAMESPACE+".updateKey");
		return flag;

	}
	/**
	 * 아직 사용되지 않은 배정될 키 리턴 
	 * @return
	 */
	public String doGetKey() {
		return sqlSessionTemplate.selectOne(NAMESPACE+".getNowKey");		
	}

//	/**
//	 * name 체크
//	 * @param usersVO
//	 * @return 1(성공)/0(실패)
//	 */
//	public int nameCheck(UsersVO usersVO) {
//		String statement = this.NAMESPACE+".nameCheck";
//		LOG.debug("========================");
//		LOG.debug("=statement="+statement);
//		LOG.debug("=param="+usersVO);
//		LOG.debug("========================");	
//		int flag =sqlSessionTemplate.selectOne(statement, usersVO);    
//		LOG.debug("=flag="+flag);
//        return flag;
//	}
//	
//	/**
//	 * 비밀번호 확인
//	 * @param usersVO
//	 * @return 1(성공)/0(실패)
//	 */
//	public int passWdCheck(UsersVO usersVO) {
//		String statement = this.NAMESPACE+".passWdCheck"; 
//		LOG.debug("========================");
//		LOG.debug("=statement="+statement);
//		LOG.debug("=param="+usersVO);
//		LOG.debug("========================");	
//		int flag =sqlSessionTemplate.selectOne(statement, usersVO);    
//		LOG.debug("=flag="+flag);
//        return flag;
//	}
	
	
}
