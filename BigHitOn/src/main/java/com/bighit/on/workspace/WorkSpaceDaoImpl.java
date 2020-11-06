package com.bighit.on.workspace;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("WorkSpaceDaoImpl")
public class WorkSpaceDaoImpl {
	final static Logger   LOG = LoggerFactory.getLogger(WorkSpaceDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "com.bighit.on.workspace";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	RowMapper rowMapper = new RowMapper<WorkSpaceVO>() {
		@Override
		public WorkSpaceVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			WorkSpaceVO outVO = new WorkSpaceVO();
			
			outVO.setWsLink(rs.getString("ws_link"));
			outVO.setWsName(rs.getString("ws_name"));
			outVO.setProject(rs.getString("project"));
			outVO.setRegId(rs.getString("reg_id"));
			outVO.setRegDt(rs.getString("reg_dt"));
			
			return outVO;
		}
	};
	
	/**
	 * 워크스페이스 생성
	 * @param workSpaceVO
	 * @return
	 */
	public int doInsert(WorkSpaceVO workSpaceVO) {
		LOG.debug("=====================");
		LOG.debug("=doInsert=");
		LOG.debug("=====================");
		//등록 : namespace+id = com.bighit.on.workspace.doInsert
		String statement = NAMESPACE +".doInsert";
		LOG.debug("=statement="+statement);
		LOG.debug("=param ==="+workSpaceVO);
		
		int flag = sqlSessionTemplate.insert(statement, workSpaceVO);
		
		LOG.debug("-doInsert flag=" + flag);
		return flag;
	}
	
	/**
	 * 워크스페이스 삭제
	 * @param workSpaceVO
	 * @return
	 */
	public int doDelete(WorkSpaceVO workSpaceVO) {
		LOG.debug("=====================");
		LOG.debug("=doDelete=");
		LOG.debug("=====================");
		//삭제 : namespace+id = com.bighit.on.workspace.doDelete
		String statement = NAMESPACE +".doDelete";
		LOG.debug("=statement="+statement);
		LOG.debug("=param=="+workSpaceVO);
		
		int flag = sqlSessionTemplate.delete(statement,workSpaceVO);
		LOG.debug("-doDelete flag==" + flag);
		return flag;
	}
	
	/**
	 * 워크스페이스 단건 조회
	 * @param wsLink
	 * @return
	 */
	public WorkSpaceVO doSelectOne(WorkSpaceVO wsLink) {
		LOG.debug("=====================");
		LOG.debug("=doSelectOne=");
		LOG.debug("=====================");	
		
		//단건조회 : namespace+id = com.bighit.on.workspace.doSelectOne		
		String statement = NAMESPACE +".doSelectOne";
		LOG.debug("=statement="+statement);
		LOG.debug("=param=="+wsLink);
		
		WorkSpaceVO outVO = sqlSessionTemplate.selectOne(statement,wsLink);
		
		LOG.debug("doSelectOne outVO = "+outVO);
		
		return outVO;
	}
	
	
}
