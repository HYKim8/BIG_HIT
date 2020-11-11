package com.bighit.on.workspace;

import java.util.List;

public interface WorkSpaceService {
	
	public int doInsert(WorkSpaceVO workSpaceVO);
	
	public int doDelete(WorkSpaceVO workSpaceVO);
	
	public WorkSpaceVO doSelectOne(WorkSpaceVO wsLink);
	
	public int workSpaceLinkCK(WorkSpaceVO workSpaceVO);
	
	public int workSpaceNameCK(WorkSpaceVO workSpaceVO);
	
	public int workSpacePCK(WorkSpaceVO workSpaceVO);
	
	public List<WorkSpaceVO> doSelectList (WorkSpaceVO workSpaceVO);
}
