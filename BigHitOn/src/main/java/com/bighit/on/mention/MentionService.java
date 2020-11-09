package com.bighit.on.mention;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;

public class MentionService {
	final static Logger LOG = LoggerFactory.getLogger(MentionService.class);
	@Autowired
	MentionDaoImpl mentionDaoImpl;
	/**
	 * 중복 체크 후 있으면 추가X 없으면 추가 쿼리
	 * @param mentionVO
	 * @return
	 */
	public int doInsert(MentionVO mentionVO) {
		return mentionDaoImpl.doCheck(mentionVO) ? 0 : mentionDaoImpl.doInsert(mentionVO);
		
	}
	public int doDelete(MentionVO mentionVO)
	{
		return mentionDaoImpl.doDelete(mentionVO);
	}
	public List<MentionVO> doSelectList(MentionVO inVO)
	{
		return mentionDaoImpl.doSelectList(inVO);
	}
	public List<ThreadVO> doSelectList(UsersVO inVO)
	{
		return mentionDaoImpl.doSelectList(inVO);
	}
}
