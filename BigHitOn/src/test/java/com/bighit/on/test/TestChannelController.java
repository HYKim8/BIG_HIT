package com.bighit.on.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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

import com.bighit.on.channel.ChannelDaoImpl;
import com.bighit.on.channel.ChannelService;
import com.bighit.on.channel.ChannelVO;
import com.bighit.on.cmn.Message;
import com.google.gson.Gson;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class TestChannelController {
	Logger  LOG = LoggerFactory.getLogger(TestChannelController.class);
	
	@Autowired // 테스트 컨텍스트 프레임워크는 일치하는 컨텍스트를 찾아 DI해 준다.
	WebApplicationContext webApplicationContext;
	
	@Autowired
	ChannelService channelService;
	
	List<ChannelVO> channels;
	
	// 브라우저 대신 Mock
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		channels=Arrays.asList(new ChannelVO("12","1","testCh1","=testch1test=","testspace","1","jhs",""),
							   new ChannelVO("123","1","testCh1_U","=testch1test_U=","testspace_U","1","jhs_U",""));
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.debug("=mockMvc=" + mockMvc);
		assertThat(mockMvc, is(notNullValue()));
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//성공
	@Test
	public void doInsert() throws Exception {
		ChannelVO channelVO = channels.get(0);
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.post("/channel/doInsert.do")
				 .param("chLink", channelVO.getChLink())
				 .param("wsLink", channelVO.getWsLink())
				 .param("chName", channelVO.getChName())
				 .param("topic", channelVO.getTopic())
				 .param("chDescription", channelVO.getChDescription())
				 .param("chAccess", channelVO.getChAccess())
				 .param("regId", channelVO.getRegId());
		ResultActions resultActions = mockMvc.perform(createMessage)
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
	
	//성공
	@Test
	@Ignore
	public void doDelete() throws Exception {
		ChannelVO channelVO = channels.get(0);
		LOG.debug("channelVO"+channelVO);
		
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.post("/channel/doDelete.do")
				 .param("chLink", channelVO.getChLink());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
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
	
	//성공
	@Test
	public void doSelectOne() throws Exception {
		ChannelVO channelVO = channels.get(0);
		
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.get("/channel/doSelectOne.do")
				 .param("chLink", channelVO.getChLink());
		
		ResultActions resultActions = mockMvc.perform(createMessage)
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
