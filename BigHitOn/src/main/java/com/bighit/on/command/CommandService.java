package com.bighit.on.command;

import java.util.List;

public interface CommandService {
	
    int doInsert(CommandVO commandVO);
	
	int doDelete(CommandVO commandVO);
	
	CommandVO doSelectOne(CommandVO commandVO);
	
	List<CommandVO> doSelectList(CommandVO commandVO);
	
	List<ComChLinkVO> doSelectListChLink(ComChLinkVO comChLinkVO);
	
	int doUpdate(CommandVO commandVO);
}
