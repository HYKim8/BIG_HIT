package com.bighit.on.workspace;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("WorkSpaceDaoImpl")
public class WorkSpaceDaoImpl {
	
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
		int flag = 0;
		Object[] args = { workSpaceVO.getWsLink(),
						  workSpaceVO.getWsName(),
						  workSpaceVO.getProject(),
						  workSpaceVO.getRegId()							
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO workspace ( \n");
		sb.append("    ws_link,            \n");
		sb.append("    ws_name,            \n");
		sb.append("    project,            \n");
		sb.append("    reg_id,             \n");
		sb.append("    reg_dt              \n");
		sb.append(") VALUES (              \n");
		sb.append("    ?,                  \n");
		sb.append("    ?,                  \n");
		sb.append("    ?,                  \n");
		sb.append("    ?,                  \n");
		sb.append("    sysdate             \n");
		sb.append(")                       \n");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		return flag;
	}
	
	/**
	 * 워크스페이스 삭제
	 * @param workSpaceVO
	 * @return
	 */
	public int doDelete(WorkSpaceVO workSpaceVO) {
		int flag =0;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE FROM workspace \n");
		sb.append(" WHERE ws_link = ?     \n");
		
		Object[] args = {workSpaceVO.getWsLink()};
		flag = this.jdbcTemplate.update(sb.toString(), args);		
		return flag;
	}
}
