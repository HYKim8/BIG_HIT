package com.bighit.on.thread;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



@Repository("ThreadDao")
public class ThreadDao {
	 final static Logger   LOG = LoggerFactory.getLogger(ThreadDao.class);

	public ThreadDao() {		
	}

	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	 //setter를 통한 DI
	 DataSource  dataSource;
	   
	 RowMapper rowMapper= new RowMapper<ThreadVO>() 
	 {
		 public ThreadVO mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
			 ThreadVO outVO = new ThreadVO();
				
				outVO.setThrKey(rs.getString("thr_key"));
				outVO.setChLink(rs.getString("ch_link"));
				outVO.setContents(rs.getString("contents"));
				outVO.setIsPin(rs.getInt("is_pin"));
				outVO.setPinId(rs.getString("pin_id"));
				outVO.setRegId(rs.getString("reg_id"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setParentKey(rs.getString("parent_key"));
				
				return outVO;
			}
	   };
	   
	   
	   public RowMapper getRowMapper() {
			return rowMapper;
		}
	   

	public int doInsert(ThreadVO threadVO) 
		{
		   int flag = 0;
		   Object[] args = { threadVO.getThrKey(),
				   			 threadVO.getChLink(),
				   			 threadVO.getContents(),
				   			 threadVO.getIsPin(),
				   			 threadVO.getPinId(),
				   			 threadVO.getRegId(),
				   			 threadVO.getRegDt(),
				   			 threadVO.getModDt(),
				   			 threadVO.getParentKey()
		   };
		   
		StringBuilder  sb=new StringBuilder();
		sb.append("INSERT INTO THREAD (     \n");
		sb.append(" thr_key,                \n");
		sb.append(" ch_link,                \n");
		sb.append(" contents,               \n");
		sb.append(" is_pin,                 \n");
		sb.append(" pin_id,                 \n");
		sb.append(" reg_id,                 \n");
		sb.append(" reg_dt,                 \n");
		sb.append(" mod_dt,                 \n");
		sb.append(" parent_key              \n");
		sb.append(" ) VALUES (              \n");
		sb.append(" ?,                      \n");
		sb.append(" ?,                      \n");
		sb.append(" ?,                      \n");
		sb.append(" ?,                      \n");
		sb.append(" ?,                      \n");
		sb.append(" ?,                      \n");
		sb.append(" sysdate,                \n");
		sb.append(" sysdate,                \n");
		sb.append(" ?                       \n");
		sb.append(" )                       \n");
		LOG.debug("========================");
		LOG.debug("==param=\n=="+threadVO);
		LOG.debug("========================");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag="+flag);

		return flag;
	}
	   
	  public int doDelete(ThreadVO threadVO) {
		  int flag = 0;
		  
		  StringBuilder  sb=new StringBuilder();
		  sb.append(" DELETE FROM THREAD \n");
		  sb.append(" WHERE thr_key = ?  \n");
		  
		  LOG.debug("=====================================");
		  //LOG.debug("=sql=\n"+sb.toString());
	      LOG.debug("=param="+threadVO);
	      LOG.debug("=====================================");
	      
	      Object[] args = {threadVO.getThrKey()};
	      flag = this.jdbcTemplate.update(sb.toString(), args);
			
		  return flag;
	  }
	  
	  public int doUpdate(ThreadVO threadVO)
	  {
		  int flag = 0;
		  StringBuilder sb=new StringBuilder();
		  sb.append("UPDATE THREAD               \n");
		  sb.append("SET ch_link = ?,            \n");
		  sb.append("    contents = ?,           \n");
		  sb.append("    is_pin = ?,             \n");		  	 
		  sb.append("    reg_dt = sysdate,       \n");
		  sb.append("    mod_dt = sysdate,       \n");
		  sb.append("    parent_key = ?          \n");
		  sb.append("WHERE thr_key = ?           \n");
		  LOG.debug("========================");
			//LOG.debug("=sql\n="+sb.toString());
		  LOG.debug("=param="+threadVO);
		  LOG.debug("========================");	
		  
		  Object[] args = {threadVO.getChLink(), 
			        threadVO.getContents(),
			        threadVO.getIsPin(),
			        threadVO.getParentKey()};
		  flag = this.jdbcTemplate.update(sb.toString(), args);
		  LOG.debug("=flag="+flag);		
			
		  return flag;				
	  }
	  
	  public List<ThreadVO> doSelectAll(ThreadVO threadVO) {
		  List<ThreadVO> list = null;
		  StringBuilder sb=new StringBuilder();
		  sb.append("SELECT thr_key,                                       \n");
	      sb.append(" ch_link,                                             \n");
	      sb.append(" contents,                                            \n");
	      sb.append(" is_pin,                                              \n");
	      sb.append(" pin_id,                                              \n");
	      sb.append(" reg_id,                                              \n");
	      sb.append(" TO_CHAR(reg_dt,'YYYY-MM-DD HH24MISS') AS reg_dt,     \n");
	      sb.append(" TO_CHAR(mod_dt,'YYYY-MM-DD HH24MISS') AS mod_dt,     \n");
	      sb.append(" parent_key                                           \n");
	      sb.append(" FROM THREAD                                          \n");
	      sb.append(" WHERE thr_key like ?                                 \n");
	      sb.append(" ORDER BY thr_key                                     \n");
	      LOG.debug("========================");
			//LOG.debug("=sql\n="+sb.toString());
		  LOG.debug("=param="+threadVO);
		  LOG.debug("========================");
		  
		  list = this.jdbcTemplate.query(sb.toString(), 
	               new Object[] {"%"+threadVO.getThrKey()+"%"}, 
	               rowMapper);
		  
          for(ThreadVO vo:list) {
          LOG.debug("====================================");
          LOG.debug("=vo="+vo);
          LOG.debug("====================================");
          }
          
          return list;		                                                                 
	  }
	  
	  
	  public ThreadVO doSelectOne(String thrKey){
		  ThreadVO outVO = null;
		  StringBuilder  sb=new StringBuilder();
		  sb.append("SELECT thr_key,                                       \n");
	      sb.append(" ch_link,                                             \n");
	      sb.append(" contents,                                            \n");
	      sb.append(" is_pin,                                              \n");
	      sb.append(" pin_id,                                              \n");
	      sb.append(" reg_id,                                              \n");
	      sb.append(" TO_CHAR(reg_dt,'YYYY-MM-DD HH24MISS') AS reg_dt,     \n");
	      sb.append(" TO_CHAR(mod_dt,'YYYY-MM-DD HH24MISS') AS mod_dt,     \n");
	      sb.append(" parent_key                                           \n");
	      sb.append(" FROM THREAD                                          \n");
	      sb.append(" WHERE thr_key = ?                                 \n");
	      sb.append(" ORDER BY thr_key                                     \n");	      
	      LOG.debug("========================");
		  //LOG.debug("=sql\n="+sb.toString());
		  LOG.debug("=param="+thrKey);
		  LOG.debug("========================");	
		  
		  Object args[] = {thrKey};
			outVO = (ThreadVO) this.jdbcTemplate.queryForObject(sb.toString(), 
	    			                        args, 
	    			                        rowMapper);
			
			LOG.debug("========================");
			LOG.debug("=outVO="+outVO);
			LOG.debug("========================");			
	    	

	    	return outVO;
		  
	  }
		
}
		
	 
	

