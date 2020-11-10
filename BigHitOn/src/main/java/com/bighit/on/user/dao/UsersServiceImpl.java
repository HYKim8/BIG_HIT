package com.bighit.on.user.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bighit.on.channel.ChannelVO;
import com.bighit.on.workspace.WorkSpaceVO;

@Service("usersServiceImpl")
public class UsersServiceImpl implements UsersService {
	final static Logger LOG = LoggerFactory.getLogger(UsersServiceImpl.class);

	@Autowired
	UsersDaoImpl usersDaoImpl;
	
	@Override
	public int doInsert(UsersVO usersVO) {

		return usersDaoImpl.doInsert(usersVO);
	}

	@Override
	public int doDelete(UsersVO usersVO) {
		
		return usersDaoImpl.doDelete(usersVO);
	}

	@Override
	public int doUpdate(UsersVO usersVO) {
		
		return usersDaoImpl.doUpdate(usersVO);
	}

	@Override
	public UsersVO doSelectOne(String userSerial) {
		
		return usersDaoImpl.doSelectOne(userSerial);
	}

	@Override
	public List<UsersVO> doSelectList(ChannelVO channelVO) {
		
		return usersDaoImpl.doSelectList(channelVO);
	}

	@Override
	public List<UsersVO> doSelectList2(WorkSpaceVO workSpaceVO) {
		
		return usersDaoImpl.doSelectList2(workSpaceVO);
	}

}
