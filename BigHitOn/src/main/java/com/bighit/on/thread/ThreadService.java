package com.bighit.on.thread;

import java.util.List;

public interface ThreadService {
	
	int doInsert(ThreadVO threadVO);
	
	int doDelete(ThreadVO threadVO);
	
	public List<ThreadVO> doSelectAll(ThreadVO threadVO);
	
	int doUpdate(ThreadVO threadVO);
}
