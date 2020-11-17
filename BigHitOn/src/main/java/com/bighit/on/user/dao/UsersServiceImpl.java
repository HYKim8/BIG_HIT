package com.bighit.on.user.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bighit.on.channel.ChannelDaoImpl;
import com.bighit.on.channel.ChannelVO;
import com.bighit.on.channelusers.ChannelUsersDao;
import com.bighit.on.channelusers.ChannelUsersVO;
import com.bighit.on.workspace.WorkSpaceVO;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;

@Service
public class UsersServiceImpl implements UsersService {
	final static Logger LOG = LoggerFactory.getLogger(UsersServiceImpl.class);

	@Autowired
	UsersDaoImpl usersDaoImpl;
	@Autowired
	ChannelUsersDao chUserDao;
	@Autowired
	ChannelDaoImpl chDao;
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
	/**
	 * 유저가 워크스페이스에 소속된 순간에 
	 * 워크스페이스내의 유저들과 DM channel을 생성 
	 * 새로운 유저가 생길때 넣어주면 됌 
	 * @param userVO
	 * @return
	 */
	public int makeDMchannel(UsersVO userVO) {
		List<UsersVO> list = usersDaoImpl.doSelectList(new WorkSpaceVO(userVO.getWsLink(), "", "", "", ""));
		int flag = 1;
		for(int i=0;i<list.size();i++) {
			ChannelVO ch = new ChannelVO("", userVO.getWsLink(), list.get(i).getName() +"/"+ userVO.getName() ,"DM" , "1:1대화", "0", userVO.getUser_serial(), "");
			String chkey = chDao.doGetKey();
			flag &=chDao.doInsert(ch);
			if(flag == 1) LOG.debug("DM 채널 개설 성공 ");
			ChannelUsersVO cuVO = new ChannelUsersVO(chkey, list.get(i).getUser_serial(), 1);
			flag &= chUserDao.doInsert(cuVO);
			if(flag == 1) LOG.debug(String.format( "%s 채널에 %s 데이터 삽입 성공", cuVO.getChLink() ,cuVO.getUserSerial()));
			cuVO.setUserSerial(userVO.getUser_serial());
			flag &= chUserDao.doInsert(cuVO);
			if(flag == 1) LOG.debug(String.format( "%s 채널에 %s 데이터 삽입 성공", cuVO.getChLink() ,cuVO.getUserSerial()));
		}
		return flag;
	}

	@Override
	public int emailCheck(UsersVO usersVO) {
		return usersDaoImpl.emailCheck(usersVO);
	}

	@Override
	public UsersVO doSelectOne(String ws_link, String email) {
		
		return usersDaoImpl.doSelectOne(ws_link, email);
	}
	
}
