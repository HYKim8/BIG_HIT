package com.bighit.on.user.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bighit.on.channel.ChannelVO;
import com.bighit.on.channelusers.ChannelUsersDao;
import com.bighit.on.workspace.WorkSpaceVO;

@Service
public class UsersServiceImpl implements UsersService {
	final static Logger LOG = LoggerFactory.getLogger(UsersServiceImpl.class);

	@Autowired
	UsersDaoImpl usersDaoImpl;
	@Autowired
	ChannelUsersDao chUserDao;
	
	
	@Override
	public int doInsert(UsersVO usersVO) {
		String key = usersDaoImpl.doGetKey();
		LOG.debug("keyvalue:"+key);
		usersVO.setUser_serial(key);
		return usersDaoImpl.doInsert(usersVO) ==1 && 
				chUserDao.doWorkSpaceInsert(usersVO)==1 ? 1:0;
		
	}

	@Override
	public int doDelete(UsersVO usersVO) {
		chUserDao.doDeleteAll(usersVO);
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
	public List<UsersVO> doSelectList(WorkSpaceVO workSpaceVO) {
		
		return usersDaoImpl.doSelectList(workSpaceVO);
	}
	
	@Override
	public int nickNameCK(UsersVO usersVO) {
		if(usersVO.getNickname().length() == 0 || usersVO.getNickname().equals("") ) {
			LOG.debug("usersVO.getNickname().length():"+usersVO.getNickname().length());
			LOG.debug("닉네임을 입력해주세요.");
			return 1;
		}else if(usersVO.getNickname().length() >= 80) {
			LOG.debug("usersVO.getNickname().length():"+usersVO.getNickname().length());
			LOG.debug("닉네임은 80자를 넘을 수 없습니다. 다시 입력해주세요.");
			return 1;
		}else {
		LOG.debug("usersVO.getNickname().length():"+usersVO.getNickname().length());
		LOG.debug("닉네임 생성 완료");
		return 0;
		}
	}
	
}
