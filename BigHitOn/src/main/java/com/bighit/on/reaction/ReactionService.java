package com.bighit.on.reaction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;
@Service
public class ReactionService {
	final static Logger LOG = LoggerFactory.getLogger(ReactionService.class);
	@Autowired
	ReactionDaoImpl reactionDaoImpl;
	/**
	 * 중복 있을시 삭제, 없으면 삽입 
	 * @param reactionVO
	 * @return
	 */
	public int toggling(ReactionVO reactionVO) {
		return reactionDaoImpl.doCheck(reactionVO) ?
				reactionDaoImpl.doDelete(reactionVO) : reactionDaoImpl.doInsert(reactionVO);
	}
	public List<ReactionVO> doSelectList(ReactionVO inVO){
		return reactionDaoImpl.doSelectList(inVO);		
	}
	public List<ThreadVO> doSelectList(UsersVO inVO)
	{
		return reactionDaoImpl.doSelectList(inVO);
	}
	public ReactionVO doSelectCnt(ReactionVO reactionVO) {
		return reactionDaoImpl.doSelectCnt(reactionVO);
	}
	public int doInsert(ReactionVO reactionVO) {
		return reactionDaoImpl.doInsert(reactionVO);
	}
}
