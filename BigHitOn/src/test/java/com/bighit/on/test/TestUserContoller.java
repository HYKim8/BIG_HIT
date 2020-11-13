package com.bighit.on.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.bighit.on.channel.ChannelVO;
import com.bighit.on.cmn.Message;
import com.bighit.on.user.dao.UsersServiceImpl;
import com.bighit.on.user.dao.UsersVO;
import com.bighit.on.workspace.WorkSpaceVO;
import com.google.gson.Gson;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class TestUserContoller {
	final static Logger LOG = LoggerFactory.getLogger(TestUserContoller.class);
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	UsersServiceImpl usersServiceImpl;
	
	MockMvc mockMvc;
	
	UsersVO users01;
	UsersVO users02;
	UsersVO users03;
	WorkSpaceVO ws;
	ChannelVO ch;
	
	@Before
	public void setUp() throws Exception {
		users01=new UsersVO("0", "1", "bighit@naver.com", "1234", "김영은", "JOY", "", "", "01012345678", 12, 1, 1, "", "","");
    	users02=new UsersVO("U353MAVOI13", "1", "bighit@naver.com", "1234", "김영은", "JOY", "", "", "01012345678", 12, 1, 1, "", "","");
    	users03=new UsersVO("3333", "1", "bighit@naver.com", "1234", "김영은", "JOY", "", "", "01012345678", 12, 1, 1, "", "","");
    	ws = new WorkSpaceVO();
    	ws.setWsLink("1");
    	ch = new ChannelVO();
    	ch.setChLink("CI73WPL782T");
    	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	LOG.debug("=mockMvc=" + mockMvc);
		assertThat(mockMvc, is(notNullValue()));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
//	@Ignore
	public void doInsert() throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.post("/users/doInsert.do")
				 .param("user_serial", users01.getUser_serial())
				 .param("ws_link", users01.getWs_link())
				 .param("email", users01.getEmail())
				 .param("password", users01.getPassword())
				 .param("name", users01.getName())
				 .param("nickname", users01.getNickname())
				 .param("profile_img", users01.getProfile_img())
				 .param("position", users01.getPosition())
				 .param("phone_num", users01.getPhone_num())
				 .param("country", users01.getCountry()+"")
				 .param("state", users01.getState()+"")
				 .param("online_state", users01.getOnline_state()+"")
				 .param("reg_id", users01.getReg_id())
				 .param("thumb", users01.getThumb())
				 ;
		
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().is2xxSuccessful());	
	
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===========================");
		LOG.debug("=result=" + result);
		LOG.debug("===========================");
		
		
	}
	/**
	 * 유저시리얼 직접 알아와야 실행 가능 
	 * @throws Exception
	 */
	@Test
	@Ignore 
	public void doDelete() throws Exception{
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.post("/users/doDelete.do")
				 .param("user_serial", users02.getUser_serial());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().is2xxSuccessful());	
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===========================");
		LOG.debug("=result=" + result);
		LOG.debug("===========================");
		
	}
	/** 
	 * 워크스페이스에 소속된 유저 검색 
	 * @throws Exception
	 */
	@Test
	public void doSelectListFromWs() throws Exception{
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.get("/users/doSelectListFromWs.do")
				 .param("wsLink", ws.getWsLink());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().is2xxSuccessful());	
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===========================");
		LOG.debug("=result=" + result);
		LOG.debug("===========================");
	}
	@Test
	public void doSelectListFromCh() throws Exception{
		MockHttpServletRequestBuilder createMessage = 
				 MockMvcRequestBuilders.get("/users/doSelectListFromCh.do")
				 .param("chLink", ch.getChLink());
		ResultActions resultActions = mockMvc.perform(createMessage)
				.andExpect(status().is2xxSuccessful());	
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("===========================");
		LOG.debug("=result=" + result);
		LOG.debug("===========================");
		
	}
}
