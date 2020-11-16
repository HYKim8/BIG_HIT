package com.bighit.on.workspace;

import java.util.List;

import com.bighit.on.cmn.Message;
import com.bighit.on.email.EmailVO;
import com.bighit.on.user.dao.UsersVO;

public interface WorkSpaceService {
	
	public Message doInsert(WorkSpaceVO workSpaceVO);
	
	public int doDelete(WorkSpaceVO workSpaceVO);
	
	public WorkSpaceVO doSelectOne(WorkSpaceVO wsLink);
	
	public int workSpaceLinkCK(WorkSpaceVO workSpaceVO);
	
	public int workSpaceNameCK(WorkSpaceVO workSpaceVO);
	
	public int workSpacePCK(WorkSpaceVO workSpaceVO);
	
	//public List<WorkSpaceVO> doSelectList (WorkSpaceVO workSpaceVO);
	
	public void sendEmail(EmailVO emailVO);
	
	public List<WorkSpaceVO> doSelectList(UsersVO usersVO);
}
