package com.bighit.on.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bighit.on.thread.ThreadService;
import com.bighit.on.thread.ThreadVO;
import com.google.gson.Gson;

import com.bighit.on.cmn.Message;

//메소드 수행 순서:
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                 "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})

public class TestThreadController {
	final Logger LOG = LoggerFactory.getLogger(TestThreadController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	ThreadService threadService;
	
	List<ThreadVO> threads;
	
	MockMvc mockMvc;
	
	//String thrKey, String chLink, String contents, int isPin, String pinId, String regId, String regDt,String modDt, String parentKey
	@Before
	public void setUp() throws Exception {
		threads = Arrays.asList( new ThreadVO("5","1","testTest",1,"11111","HOON","2020-11-06","2020-11-06","aaaa"));
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.debug("=mockMvc=" + mockMvc);
		assertThat(mockMvc, is(notNullValue()));
	}
	
	@Test
	public void doInsert() throws Exception {
		//(#{parent_key}, #{ch_link}, #{contents},#{reg_id},#{reg_dt})
		ThreadVO threadVO = threads.get(0);
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.post("/thread/doInsert.do")
				 .param("parentKey", threadVO.getParentKey()+"")
				 .param("chLink", threadVO.getChLink()+"")
				 .param("contents", threadVO.getContents()+"")
				 .param("regId", threadVO.getRegId()+"")
				 .param("regDt", threadVO.getRegDt()+"");
		
		ResultActions resultActions =mockMvc.perform(createMessage)		
				  .andExpect(status().is2xxSuccessful());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===========================");
		LOG.debug("=result=" + result);
		LOG.debug("===========================");
		
		//json -> Message
		Gson gson=new Gson();
		
		Message message = gson.fromJson(result, Message.class);
		LOG.debug("===========================");
		LOG.debug("=message=" + message);
		LOG.debug("===========================");	
	}

}
