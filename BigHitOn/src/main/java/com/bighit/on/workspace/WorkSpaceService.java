package com.bighit.on.workspace;

public interface WorkSpaceService {
	
	public int doInsert(WorkSpaceVO workSpaceVO);
	
	public int doDelete(WorkSpaceVO workSpaceVO);
	
	public WorkSpaceVO doSelectOne(WorkSpaceVO wsLink);
	
	public int workSpaceLinkCK(WorkSpaceVO workSpaceVO);
	
	public int workSpaceNameCK(WorkSpaceVO workSpaceVO);
	
	public int workSpacePCK(WorkSpaceVO workSpaceVO);
}
