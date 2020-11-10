package com.bighit.on.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
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

import com.bighit.on.user.dao.UsersVO;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class TestMenAndReaController {
	final static Logger LOG = LoggerFactory.getLogger(TestMenAndReaController.class);
	@Autowired
	WebApplicationContext webApplicationContext;
	
	MockMvc mockMvc;
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		assertThat(mockMvc, is(notNullValue()));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test()  throws Exception {
		UsersVO user = new UsersVO();
		user.setUser_serial("yeo");
		MockHttpServletRequestBuilder createMessage =
				MockMvcRequestBuilders.get("/MenAndRea/doSelectList.do")
				.param("user_serial",user.getUser_serial());
		ResultActions resultActions =mockMvc.perform(createMessage)		
				  .andExpect(status().is2xxSuccessful());
				
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("----------------------------");
		LOG.debug("-result-"+result);
		LOG.debug("----------------------------");
	}

}
