package com.bighit.on.user.dao;

import java.util.List;
import com.bighit.on.channel.ChannelVO;
import com.bighit.on.workspace.WorkSpaceVO;

public interface UsersService {
	
	UsersVO doSelectOne(String ws_link, String email);
	
	/**
	 * 비밀번호 확인
	 * @param usersVO
	 * @return 1(성공)/0(실패)
	 */
	public int pwCheck(UsersVO usersVO);
	/**
	 * 이메일 확인
	 * @param usersVO
	 * @return 1(성공)/0(실패)
	 */
	public int emailCheck(UsersVO usersVO);
	/**
	 * 닉네임 조건 
	 * @param workSpaceVO
	 * @return
	 */
	int nickNameCK(UsersVO usersVO);
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
	
	/**
	 * 아직 사용되지 않은 배정될 키 리턴 
	 * @return
	 */
	public String doGetKey() ;
}
