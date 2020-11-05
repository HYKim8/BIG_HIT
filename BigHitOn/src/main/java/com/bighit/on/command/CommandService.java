package com.bighit.on.command;

import java.util.List;

public interface CommandService {
	
	int doInsert(CommandVO commandVO);
	
	int doDelete(CommandVO commandVO);
	
	List<CommandVO> doSelectList(CommandVO commandVO);
	
	int doUpdate(CommandVO commandVO);
}
