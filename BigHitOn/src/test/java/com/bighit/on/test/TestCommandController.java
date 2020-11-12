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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bighit.on.cmn.Message;
import com.bighit.on.command.CommandDao;
import com.bighit.on.command.CommandService;
import com.bighit.on.command.CommandVO;
import com.google.gson.Gson;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                 "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class TestCommandController {
	final Logger LOG = LoggerFactory.getLogger(TestCommandController.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	CommandService commandService;
	
	@Autowired
	CommandDao commandDao;
	
	List<CommandVO> commands;
	
	MockMvc mockMvc;
	
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.debug("=mockMvc=" + mockMvc);
		assertThat(mockMvc, is(notNullValue()));
		
		commands = Arrays.asList( new CommandVO(10,"testa","testCmd",1));
	}
	
	@Test
	@Ignore
	public void doInsert() throws Exception {
		CommandVO commandVO = commands.get(0);
		commandDao.doDelete(commandVO);
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.post("/command/doInsert.do")
				 .param("comId", commandVO.getComId()+"")
				 .param("appName", commandVO.getAppName())
				 .param("cmdName", commandVO.getCmdName())
				 .param("paramCnt", commandVO.getParamCnt()+"");
	
		ResultActions resultActions =mockMvc.perform(createMessage)	
				  //.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
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
	
	@Test
	@Ignore
	public void doDelete() throws Exception {
		CommandVO commandVO = commands.get(0);
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.post("/command/doDelete.do")
				 .param("comId", commandVO.getComId()+"");
		
		ResultActions resultActions =mockMvc.perform(createMessage)	
		//.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
		.andExpect(status().is2xxSuccessful());	
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===========================");
		LOG.debug("=result=" + result);
		LOG.debug("===========================");
		
		Gson gson=new Gson();
		
		Message message = gson.fromJson(result, Message.class);
		LOG.debug("===========================");
		LOG.debug("=message=" + message);
		LOG.debug("===========================");
	}
	
	@Test
	public void doUpdate() throws Exception {
		CommandVO commandVO = commands.get(0);
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.post("/command/doUpdate.do")	
				 .param("appName", commandVO.getAppName()+"t")
				 .param("cmdName", commandVO.getCmdName()+"a")
				 .param("comId", commandVO.getComId()+"");
		
		ResultActions resultActions =mockMvc.perform(createMessage)	
				//.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful());	
				
				String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
				LOG.debug("===========================");
				LOG.debug("=result=" + result);
				LOG.debug("===========================");
				
				Gson gson=new Gson();
				
				Message message = gson.fromJson(result, Message.class);
				LOG.debug("===========================");
				LOG.debug("=message=" + message);
				LOG.debug("===========================");
	}
	
}
