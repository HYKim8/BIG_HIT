package com.bighit.on.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandServiceImpl implements CommandService {
	
	@Autowired
	CommandDao cmdDao;
	
	@Override
	public int doInsert(CommandVO commandVO) {
		return cmdDao.doInsert(commandVO);
	}

	@Override
	public int doDelete(CommandVO commandVO) {
		return cmdDao.doDelete(commandVO);
	}

	@Override
	public List<CommandVO> doSelectList(CommandVO commandVO) {
		return cmdDao.doSelectList(commandVO);
	}

	@Override
	public int doUpdate(CommandVO commandVO) {
		return cmdDao.doUpdate(commandVO);
	}

}
