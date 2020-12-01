package com.bighit.on.thread;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bighit.on.cmn.Message;
import com.bighit.on.cmn.Search;
import com.bighit.on.mention.MentionService;
import com.bighit.on.reaction.ReactionService;
import com.bighit.on.save.SaveThrService;
import com.google.gson.Gson;

@Controller
public class ThreadController {
    //board_list.jsp ->조회
   //board_write.jsp ->등록
   //board_mng.jsp   ->단건조회,수정,삭제
   final Logger   LOG = LoggerFactory.getLogger(this.getClass());
   
   @Autowired
   ThreadServiceImpl threadService;
   
   @Autowired
   MentionService mentionService;
   
   @Autowired
   ReactionService reactionService;
   
   @Autowired
   SaveThrService saveThrService;
   
   @RequestMapping(value="thread/doSelectChildList.do", method = RequestMethod.GET)
   @ResponseBody
   public List<ThreadVO> doSelectChildList(ThreadVO threadVO, Model model) {
      LOG.debug("==doSelectChildList==");
      List<ThreadVO> outVO = this.threadService.doSelectChildList(threadVO);
      
      for(ThreadVO vo: outVO) {
            LOG.debug(vo.toString());
        }
      Gson gson = new Gson();
      String json = gson.toJson(outVO);
      
      LOG.debug("==================");
      LOG.debug("=json="+json);
      LOG.debug("==================");
         
      return outVO;
   
   }
   
   @RequestMapping(value="thread/doSelectOne.do", method = RequestMethod.GET)
   @ResponseBody
   public String doSelectOne(ThreadVO threadVO, Model model) {
      //String returnURL = "thread/thread_rep";
      LOG.debug("=doSelectOne=");   
      ThreadVO outVO = this.threadService.doSelectOne(threadVO);
      
      Gson gson = new Gson();
      String json = gson.toJson(outVO);
      //model.addAttribute("vo", outVO);
      LOG.debug("==================");
      LOG.debug("=json="+json);
      LOG.debug("==================");
      
      return json;
   }
   
   @RequestMapping(value="thread/moreList.do",method = RequestMethod.GET)
   @ResponseBody
   public List<ThreadVO> MoreList(Search search) throws Exception {
      LOG.debug("=thread_view=");
      
      if(search.getPageNum()==0) {
         search.setPageNum(1);
      }
      if(search.getPageSize()==0)
      {
         search.setPageSize(100);
      }
      
      List<ThreadVO> threadList = this.threadService.doSelectList(search);
      
      for(ThreadVO vo:threadList) {
      LOG.debug(vo.toString());
      }
      return threadList;
   }
   
   @RequestMapping(value="thread/ListView.do",method = RequestMethod.GET)
   public String ListView(Search search, Model model) throws Exception {
      LOG.debug("=thread_view=");
      
      if(search.getPageNum()==0) {
         search.setPageNum(1);
      }
      if(search.getPageSize()==0)
      {
         search.setPageSize(100);
      }
      model.addAttribute("searchVO", search);
      
      List<ThreadVO> threadList = this.threadService.doSelectList(search);
      model.addAttribute("threadList", threadList);
      for(ThreadVO vo:threadList) {
      LOG.debug(vo.toString());
      }
      String view = "main/main2";
      return view;
   }
   
   
   @RequestMapping(value="thread/doInsertRep.do", method = RequestMethod.POST)
   @ResponseBody
   public String doInsertRep(ThreadVO threadVO) {
      LOG.debug("===================================");
      LOG.debug("=doInsertRep=");
      LOG.debug("=param="+threadVO);  
      int flag = threadService.doInsertRep(threadVO);
      LOG.debug("==================");
      LOG.debug("=flag="+flag);
      LOG.debug("==================");
      
      Message  message=new Message();
       message.setRegId(flag+"");
       
       if(flag ==1 ) {
             message.setMsgContents(threadVO.getRegId()+" 댓글 등록 되었습니다.");
          }else {
             message.setMsgContents(threadVO.getRegId()+" 댓글 등록 실패.");
          }
       Gson gson=new Gson();
       String json = gson.toJson(message); 
       LOG.debug("==================");
       LOG.debug("=json="+json);
       LOG.debug("==================");         
          
       return json; 
   }
   
   @RequestMapping(value="thread/doInsert.do", method = RequestMethod.POST)
   @ResponseBody
   public String doInsert(ThreadVO threadVO) {
      LOG.debug("===================================");
      LOG.debug("=doInsert=");
      LOG.debug("=param="+threadVO);
       int flag = threadService.doInsert(threadVO);
       LOG.debug("==================");
        LOG.debug("=flag="+flag);
        LOG.debug("==================");   
        
        Message  message=new Message();
        message.setRegId(flag+"");
        
        if(flag ==1 ) {
              message.setMsgContents(threadVO.getRegId()+" 님이 등록 되었습니다.");
           }else {
              message.setMsgContents(threadVO.getRegId()+" 님 등록 실패.");
           }
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");         
           
        return json; 
   }
   
   @RequestMapping(value="thread/doDelete.do", method = RequestMethod.POST)
   @ResponseBody
   public String doDelete(ThreadVO threadVO) {
      LOG.debug("===================================");
      LOG.debug("=doDelete=");
      LOG.debug("=param="+threadVO);
      
      int flag = threadService.doDelete(threadVO);
      LOG.debug("==================");
       LOG.debug("=flag="+flag);
       LOG.debug("=================="); 
        
        Message  message=new Message();
        message.setThrKey(flag+"");
        
        if(flag == 1) {
           message.setMsgContents(threadVO.getThrKey()+"삭제 성공");
        }else {
           message.setMsgContents(threadVO.getThrKey()+"삭제 실패");
        }
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");         
           
        return json; 
   }
   
   @RequestMapping(value="thread/doUpdate.do", method = RequestMethod.POST)
   @ResponseBody
   public String doUpdate(ThreadVO threadVO) {
      LOG.debug("===================================");
      LOG.debug("=doUpdate=");
      LOG.debug("=param="+threadVO);
      
      int flag = threadService.doUpdate(threadVO);
      LOG.debug("==================");
       LOG.debug("=flag="+flag);
       LOG.debug("==================");
       
       Message  message=new Message();
       message.setThrKey(flag+"");
       
       if(flag == 1) {
           message.setMsgContents(threadVO.getThrKey()+"수정 성공");
        }else {
           message.setMsgContents(threadVO.getThrKey()+"수정 실패");
        }
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("==================");
        LOG.debug("=json="+json);
        LOG.debug("==================");         
           
        return json;
   }
   
   @RequestMapping(value="thread/doSelectAll.do", method = RequestMethod.POST)
   @ResponseBody
   public String doSelectAll(ThreadVO threadVO, Model model){
      LOG.debug("===================================");
      LOG.debug("=doSelectAll=");
      
      
      List<ThreadVO> threadList = this.threadService.doSelectAll(threadVO);
      model.addAttribute("list", threadList);
      for(ThreadVO vo:threadList) {
      LOG.debug(vo.toString());
      }
      String view = "thread/thread_list";
      return view;
   }
   
   @RequestMapping(value="thread/doPin.do", method = RequestMethod.POST)
   @ResponseBody
   public Message doPin(ThreadVO threadVO) {
      LOG.debug("===================================");
      LOG.debug("=dopin=");
      
      int pin = threadVO.getIsPin() ;
      threadVO.setIsPin(pin == 1 ? 0 : 1 );
      //pin이 0이되어야하면 비워야함.
      if(pin == 1) threadVO.setPinId("");
      LOG.debug("=param="+threadVO);
      int flag = threadService.doPin(threadVO);
      String msgContents =  flag == 1 ?  ( pin == 0 ? "고정 됐습니다" : "고정 해체 됐습니다") : "기능 오류" ;
      
      
      
      LOG.debug("==================");
      LOG.debug("=flag="+flag);
      LOG.debug("==================");
       
       Message  message=new Message();
       message.setThrKey(flag+"");
       message.setMsgContents(msgContents);
           
       return message;
   }
   
   
}