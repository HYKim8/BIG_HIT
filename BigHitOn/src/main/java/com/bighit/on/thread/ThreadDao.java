package com.bighit.on.thread;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bighit.on.channel.ChannelVO;
import com.bighit.on.cmn.Search;






@Repository
public class ThreadDao {
    final  Logger   LOG = LoggerFactory.getLogger(ThreadDao.class);

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    
    final String NAMESPACE = "com.bighit.on.thread";
    
   public ThreadDao() {      
   }
   //채널에 있는 모든 쓰레드키만 가져옴 
   public List<String> doSelectList(ChannelVO channelVO){
	   String statement = NAMESPACE + ".doSelectListInCh";
	   
	   List<String> strList = sqlSessionTemplate.selectList(statement,channelVO);
	   for(String str: strList) LOG.debug("=========="+str+"==========");
	   
	   return strList;
   }
   public List<ThreadVO> doSelectList(Search search){
      LOG.debug("=======================");
      LOG.debug("====doSelectList====");
      LOG.debug("=======================");
      
      String statement = NAMESPACE+".doSelectList";
      LOG.debug("====statement===="+statement);
      LOG.debug("====search===="+search);
      List<ThreadVO> result = sqlSessionTemplate.selectList(statement, search);
      LOG.debug(result.toString());
      for(ThreadVO vo: result) LOG.debug(vo.toString());
      return result;
   }
      
   public int doInsertRep(ThreadVO threadVO) {
      LOG.debug("=======================");
      LOG.debug("====doInsertRep====");
      LOG.debug("=======================");
      
      String statement = NAMESPACE+".doInsertRep";
      LOG.debug("====statement===="+statement);
      LOG.debug("====threadVO===="+threadVO);
      int flag = sqlSessionTemplate.insert(statement, threadVO);
      LOG.debug("-doInsert flag=" + flag);
      
      
      return flag;
   }
   
   public List<ThreadVO> doSelectListIsPinned(ChannelVO channelVO){
	   LOG.debug("=======================");
	   LOG.debug("====doSelectListIsPinned====");
	   LOG.debug("=======================");
	   
	   String statement = NAMESPACE + ".doSelectListIsPinned";
	   LOG.debug("====statement===="+statement);
	   LOG.debug("====channelVO===="+channelVO);
	   
	   List<ThreadVO> outList = sqlSessionTemplate.selectList(statement, channelVO);
	   
	   for(ThreadVO tvo : outList) {
		   LOG.debug("tVO : " + tvo);
	   }
	   
	   return outList;
   }
   
   
   public int doInsert(ThreadVO threadVO) {
   LOG.debug("=======================");
   LOG.debug("====doInsert====");
   LOG.debug("=======================");
   
   String statement = NAMESPACE+".doInsert";
   LOG.debug("====statement===="+statement);
   LOG.debug("====threadVO===="+threadVO);
   int flag = sqlSessionTemplate.insert(statement, threadVO);
   LOG.debug("-doInsert flag=" + flag);
   
   
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
        LOG.debug("-doDelete flag=" + flag);
         
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
     
     public int doPin(ThreadVO threadVO)
     {
        LOG.debug("=======================");
        LOG.debug("====doPin====");
        LOG.debug("=======================");
        String statement = NAMESPACE+".doPin";
        
        LOG.debug("====statement===="+statement);
         LOG.debug("====threadVO===="+threadVO);
        int flag = sqlSessionTemplate.update(statement, threadVO);   
         
        return flag;            
     }
     
     public List<ThreadVO> doSelectChildList(ThreadVO threadVO) {
        LOG.debug("=======================");
        LOG.debug("====doSelectChildList====");
        LOG.debug("=======================");
        
        String statement = NAMESPACE+".doSelectChildList";
        List<ThreadVO> result = sqlSessionTemplate.selectList(statement, threadVO);
        LOG.debug("====statement===="+statement);
        LOG.debug(result.toString());
        for(ThreadVO vo: result) LOG.debug(vo.toString());
         return result;                                                                       
        }
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
   //     LOG.debug("=======================");
   //     LOG.debug("====doSelectAll====");
   //     LOG.debug("=======================");
   //     String statement = NAMESPACE+".doSelectAll";
   //     
   //     LOG.debug("====statement===="+statement);
    //     LOG.debug("====threadVO===="+threadVO);
   //     ThreadVO outVO = this.sqlSessionTemplate.selectOne(statement, threadVO);
     //     
     //     return outVO;                                                                       
    // }
    // 
     
     public List<ThreadVO> doSelectAll(ThreadVO threadVO) {
        LOG.debug("=======================");
         LOG.debug("====doSelectList====");
         LOG.debug("=======================");
         
         String statement = NAMESPACE+".doSelectAll";
         LOG.debug("====statement===="+statement);
         LOG.debug("====threadVO===="+threadVO);
         
         List<ThreadVO> outVO = this.sqlSessionTemplate.selectList(statement,threadVO);
         LOG.debug("=outVO="+outVO);
         
         return outVO;
     }
     public ThreadVO doSelectOne(ThreadVO threadVO){
          LOG.debug("=======================");
         LOG.debug("====doSelectOne====");
         LOG.debug("=======================");
         
         String statement = NAMESPACE+".doSelectOne";
         LOG.debug("====statement===="+statement);
         LOG.debug("====threadVO===="+threadVO);
         
         ThreadVO outVO = this.sqlSessionTemplate.selectOne(statement,threadVO);
         LOG.debug("=outVO="+outVO);
         
         return outVO;
         
        
     }
     
}
      
    
   