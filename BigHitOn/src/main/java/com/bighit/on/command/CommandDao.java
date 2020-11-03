package com.bighit.on.command;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bighit.on.cmn.DTO;




@Repository("CommandDaoImpl")
public class CommandDao {
	final static Logger LOG = LoggerFactory.getLogger(CommandDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	DataSource dataSource;
	
	RowMapper rowMapper= new RowMapper<CommandVO>() 
    {
		public CommandVO mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			CommandVO outVO = new CommandVO();
			
			outVO.setComId(rs.getInt("com_id"));
			outVO.setAppName(rs.getString("app_name"));
			outVO.setCmdName(rs.getString("cmd_name"));
			outVO.setParamCnt(rs.getInt("param_cnt"));
			
			return outVO;
		}
   };
   
		 public CommandDao() {}
		 
		 public int doInsert(CommandVO commandVO) {
		 
		 int flag = 0;
		 Object[] args = { commandVO.getComId(),
			   			 commandVO.getAppName(),
			   			 commandVO.getCmdName(),
			   			 commandVO.getParamCnt()
			   		   };
		 
		 StringBuilder sb = new StringBuilder();
		 sb.append("INSERT INTO command (    \n");
		 sb.append("           com_id,       \n");
		 sb.append("           app_name,     \n");
		 sb.append("           cmd_name,     \n");
		 sb.append("           param_cnt     \n");
		 sb.append("           ) VALUES (    \n");
		 sb.append("           ?,            \n");
		 sb.append("           ?,            \n");
		 sb.append("           ?,            \n");
		 sb.append("           ?);           \n");
		 LOG.debug("===========================");
		 LOG.debug("===param==="+commandVO);
		 LOG.debug("===========================");
		 
		 flag = this.jdbcTemplate.update(sb.toString(), args);
		 LOG.debug("=flag="+flag);

		 
		 return flag;
		 }
		 
		 public int doDelete(CommandVO commandVO) {
			 int flag = 0;
				
				StringBuilder  sb=new StringBuilder();
				sb.append(" DELETE FROM COMMAND \n");
				sb.append(" WHERE com_id = ?        \n");
				
				LOG.debug("=====================================");
				//LOG.debug("=sql=\n"+sb.toString());
				LOG.debug("=param="+commandVO);
				LOG.debug("=====================================");
				Object[] args = {commandVO.getComId()};
				flag = this.jdbcTemplate.update(sb.toString(), args);
		
				return flag;
		 }
		 
		 public int doUpdate(CommandVO commandVO) {
			int flag = 0;
			
			StringBuilder sb=new StringBuilder();
			sb.append("UPDATE COMMAND         \n");
			sb.append("SET app_name = ?,      \n");
			sb.append("    cmd_name = ?,      \n");
			sb.append("    param_cnt = ?      \n");
			sb.append("WHERE com_id = ?       \n");
			LOG.debug("========================");
			LOG.debug("==param=="+commandVO);
			LOG.debug("========================");
			
			Object[] args = {commandVO.getAppName(),
							 commandVO.getCmdName(),
							 commandVO.getParamCnt(),
							 commandVO.getComId()};
			flag = this.jdbcTemplate.update(sb.toString(), args);
			LOG.debug("=flag="+flag);		
			return flag;
			}
			
		 public List<CommandVO> doSelectList(CommandVO commandVO){
			 List<CommandVO> list = null;
			 
			 StringBuilder sb = new StringBuilder();
			 sb.append("	 SELECT com_id,         \n");
			 sb.append("       app_name,            \n");
			 sb.append("       cmd_name,            \n");
			 sb.append("       param_cnt            \n");
			 sb.append("FROM COMMAND                \n");
			 sb.append("WHERE com_id like ?         \n");
			 sb.append("ORDER BY com_id;            \n");
			 LOG.debug("======================");
			 LOG.debug("==param=="+commandVO);
			 LOG.debug("======================");
			 
			 list = this.jdbcTemplate.query(sb.toString(), 
		               new Object[] {"%"+commandVO.getComId()+"%"}, 
		               rowMapper);
			 for(CommandVO vo:list) {
					LOG.debug("====================================");
					LOG.debug("=vo="+vo);
					LOG.debug("====================================");
				}
			 return list;
		 }
		 
		 public CommandVO doSelectOne(int comId)
		 {
			 CommandVO outVO = null;
			 StringBuilder  sb=new StringBuilder();
			 sb.append("	 SELECT com_id,         \n");
			 sb.append("       app_name,            \n");
			 sb.append("       cmd_name,            \n");
			 sb.append("       param_cnt            \n");
			 sb.append("FROM COMMAND                \n");
			 sb.append("WHERE com_id = ?         \n");
			 sb.append("ORDER BY com_id;            \n");
			 LOG.debug("======================");
			 LOG.debug("==param=="+comId);
			 LOG.debug("======================");
			 
			 Object args[] = {comId};
				outVO = (CommandVO) this.jdbcTemplate.queryForObject(sb.toString(), 
		    			                        args, 
		    			                        rowMapper);
				
				LOG.debug("========================");
				LOG.debug("=outVO="+outVO);
				LOG.debug("========================");	
				
			 return outVO;
		 }
		 
		 
		}
		 
	


