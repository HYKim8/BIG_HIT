package com.bighit.on.save;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bighit.on.thread.ThreadVO;
import com.bighit.on.user.dao.UsersVO;
@Service
public class SaveThrService {
	@Autowired
	SaveThrDaoImpl saveThrDaoImpl;
	public boolean doCheck(SaveThrVO saveThrVO)
	{
		return saveThrDaoImpl.doCheck(saveThrVO);
	}
	public int doInsert(SaveThrVO saveThrVO) {
		return saveThrDaoImpl.doInsert(saveThrVO);
	}
	public int doDelete(SaveThrVO saveThrVO)
	{
		return saveThrDaoImpl.doDelete(saveThrVO);
	}
	public List<SaveThrVO> doSelectList(SaveThrVO inVO){
		return saveThrDaoImpl.doSelectList(inVO);
	}
	public List<ThreadVO> doSelectList(UsersVO inVO){
		return saveThrDaoImpl.doSelectList(inVO);
	}
}
