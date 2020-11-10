package com.bighit.on.save;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
}
