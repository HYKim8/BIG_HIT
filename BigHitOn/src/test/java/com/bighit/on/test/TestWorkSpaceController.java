package com.bighit.on.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
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
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bighit.on.cmn.Message;
import com.bighit.on.workspace.WorkSpaceService;
import com.bighit.on.workspace.WorkSpaceVO;
import com.google.gson.Gson;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class TestWorkSpaceController {
	final Logger   LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	WorkSpaceService workSpaceService;
	
	List<WorkSpaceVO> workSpaces;
	
	// 브라우저 대신 Mock
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		workSpaces=Arrays.asList(new WorkSpaceVO("2","정현수","정현수","jhs","")
								,new WorkSpaceVO("3","jhs_ws","bighit","jhs","")					
		);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		LOG.debug("=mockMvc=" + mockMvc);
		assertThat(mockMvc, is(notNullValue()));
	}
	
	@Test
	@Ignore
	public void doInsert() throws Exception {
		WorkSpaceVO workSpaceVO = workSpaces.get(0);
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.post("/workspace/doInsert.do")
				 .param("wsLink", workSpaceVO.getWsLink())
				 .param("wsName", workSpaceVO.getWsName())
				 .param("project", workSpaceVO.getProject())
				 .param("regId", workSpaceVO.getRegId())
				 .param("regDt", workSpaceVO.getRegDt());
		
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
		WorkSpaceVO workSpaceVO = workSpaces.get(0);
		workSpaceVO.setWsLink(2+"");
		LOG.debug("workSpaces.get(0):"+workSpaceVO);
		
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.post("/workspace/doDelete.do")
				 .param("wsLink", workSpaceVO.getWsLink()+"");
		
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
	
	@After
	public void tearDown() throws Exception {
	}
	
	//성공
	@Test
	public void doSelectOne() throws Exception {
		WorkSpaceVO workSpaceVO = workSpaces.get(0);
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.get("/workspace/doSelectOne.do")
				 .param("wsLink", workSpaceVO.getWsLink());
		
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
