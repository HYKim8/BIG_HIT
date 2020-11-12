package com.bighit.on.user.dao;

import java.util.List;
import com.bighit.on.channel.ChannelVO;
import com.bighit.on.workspace.WorkSpaceVO;

public interface UsersService {
	/**
	 * 워크스페이스 이름 생성 길이 제한
	 * @param workSpaceVO
	 * @return
	 */
	int workSpaceNameCK(WorkSpaceVO workSpaceVO);
	/**
	 * 추가
	 * @param usersVO
	 * @return int
	 */
	int doInsert(UsersVO usersVO);
	
	/**
	 * 삭제
	 * @param usersVO
	 * @return int
	 */
	int doDelete(UsersVO usersVO);
	
	/**
	 * 수정
	 * @param usersVO
	 * @return int
	 */
	int doUpdate(UsersVO usersVO);
	
	/**
	 * 
	 * @param userSerial
	 * @return UsersVO
	 */
	UsersVO doSelectOne(String userSerial) ;
	
	/**
	 * 채널에 있는 유저 목록조회
	 * @param channelVO
	 * @return List<UsersVO>
	 */
	List<UsersVO> doSelectList(ChannelVO channelVO);
	
	/**
	 * 워크스페이스에 있는 유저 목록조회
	 * @param workSpaceVO
	 * @return List<UsersVO>
	 */
	List<UsersVO> doSelectList(WorkSpaceVO workSpaceVO);
}
