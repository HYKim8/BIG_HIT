package com.bighit.on.thread;

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





@Repository
public class ThreadDao2 {
	 final  Logger   LOG = LoggerFactory.getLogger(ThreadDao2.class);

	 @Autowired
	 SqlSessionTemplate sqlSessionTemplate;
	 
	 private final String NAMESPACE = "com.bighit.on.thread";
	 
	public ThreadDao2() {		
	}

	   

	public int doInsert(ThreadVO threadVO) {
	LOG.debug("=======================");
	LOG.debug("====doInsert====");
	LOG.debug("=======================");
	
	String statement = NAMESPACE+".doInsert";
	LOG.debug("====statement===="+statement);
	LOG.debug("====threadVO===="+threadVO);
	int flag = sqlSessionTemplate.insert(statement, threadVO);
	
	return flag;
	}
	   
	  public int doDelete(ThreadVO threadVO) {
		  LOG.debug("=======================");
		  LOG.debug("====doDelete====");
		  LOG.debug("=======================");
		  String statement = NAMESPACE+".doDelete";
		  
		  LOG.debug("====statement===="+statement);
	      LOG.debug("====threadVO===="+threadVO);
		  int flag = sqlSessionTemplate.delete(statement, threadVO);
			
		  return flag;
	  }
	  
	  public int doUpdate(ThreadVO threadVO)
	  {
		  LOG.debug("=======================");
		  LOG.debug("====doUpdate====");
		  LOG.debug("=======================");
		  String statement = NAMESPACE+".doUpdate";
		  
		  LOG.debug("====statement===="+statement);
	      LOG.debug("====threadVO===="+threadVO);
		  int flag = sqlSessionTemplate.update(statement, threadVO);	
			
		  return flag;				
	  }
	  
	 // public List<ThreadVO> doSelectChildList(ThreadVO threadVO) {
	 //       List<ThreadVO> list = null;
	 //       StringBuilder sb=new StringBuilder();
	 //       sb.append("SELECT thr_key,                                       \n");
	 //        sb.append(" ch_link,                                             \n");
	 //        sb.append(" contents,                                            \n");
	 //        sb.append(" is_pin,                                              \n");
	 //        sb.append(" pin_id,                                              \n");
	 //        sb.append(" reg_id,                                              \n");
	 //        sb.append(" TO_CHAR(reg_dt,'YYYY-MM-DD HH24MISS') AS reg_dt,     \n");
	 //        sb.append(" TO_CHAR(mod_dt,'YYYY-MM-DD HH24MISS') AS mod_dt,     \n");
	 //        sb.append(" parent_key                                           \n");
	 //        sb.append(" FROM THREAD                                          \n");
	 //        sb.append(" WHERE parent_key = ?                                 \n");
	 //        
	 //       LOG.debug("========================");
	 //        //LOG.debug("=sql\n="+sb.toString());
	 //       LOG.debug("=param="+threadVO);
	 //       LOG.debug("========================");
	 //       
	 //       list = this.jdbcTemplate.query(sb.toString(), 
	 //                 new Object[] {"%"+threadVO.getThrKey()+"%"}, 
	 //                 rowMapper);
	 //       
	 //         for(ThreadVO vo:list) {
	 //         LOG.debug("====================================");
	 //         LOG.debug("=vo="+vo);
	 //         LOG.debug("====================================");
	 //         }
	 //         
	 //         return list;                                                                       
	 //    }
	//public List<ThreadVO> doSelectPinList(ThreadVO threadVO) {
	//        List<ThreadVO> list = null;
	//        StringBuilder sb=new StringBuilder();
	//        sb.append("SELECT thr_key,                                       \n");
	//         sb.append(" ch_link,                                             \n");
	//         sb.append(" contents,                                            \n");
	//         sb.append(" is_pin,                                              \n");
	//         sb.append(" pin_id,                                              \n");
	//         sb.append(" reg_id,                                              \n");
	//         sb.append(" TO_CHAR(reg_dt,'YYYY-MM-DD HH24MISS') AS reg_dt,     \n");
	//         sb.append(" TO_CHAR(mod_dt,'YYYY-MM-DD HH24MISS') AS mod_dt,     \n");
	//         sb.append(" parent_key                                           \n");
	//         sb.append(" FROM THREAD                                          \n");
	//         sb.append(" WHERE ch_link =?                                       \n");
	//        sb.append("    AND is_pin = 1                                       \n");
	//         sb.append(" ORDER BY thr_key                                     \n");
	//         LOG.debug("========================");
	//         //LOG.debug("=sql\n="+sb.toString());
	//        LOG.debug("=param="+threadVO);
	//        LOG.debug("========================");
	//        
	//        list = this.jdbcTemplate.query(sb.toString(), 
	//                  new Object[] {"%"+threadVO.getThrKey()+"%"}, 
	//                  rowMapper);
	//        
	//          for(ThreadVO vo:list) {
	//          LOG.debug("====================================");
	//          LOG.debug("=vo="+vo);
	//          LOG.debug("====================================");
	//          }
	//          
	//          return list;                                                                       
	//     }
	  
	 // public List<ThreadVO> doSelectAll(ThreadVO threadVO) {
	//	  LOG.debug("=======================");
	//	  LOG.debug("====doSelectAll====");
	//	  LOG.debug("=======================");
	//	  String statement = NAMESPACE+".doSelectAll";
	//	  
	//	  LOG.debug("====statement===="+statement);
	 //     LOG.debug("====threadVO===="+threadVO);
	//	  ThreadVO outVO = this.sqlSessionTemplate.selectOne(statement, threadVO);
     //     
     //     return outVO;		                                                                 
	 // }
	 // 
	  
	  
	  public ThreadVO doSelectOne(ThreadVO threadVO){
		    LOG.debug("=======================");
			LOG.debug("====doSelectTitleOne====");
			LOG.debug("=======================");
			
			String statement = NAMESPACE+".doSelectOne";
			LOG.debug("====statement===="+statement);
			LOG.debug("====threadVO===="+threadVO);
			
			ThreadVO outVO = this.sqlSessionTemplate.selectOne(statement,threadVO);
			LOG.debug("=outVO="+outVO);
			
			return outVO;
			
		  
	  }
		
}
		
	 
	
