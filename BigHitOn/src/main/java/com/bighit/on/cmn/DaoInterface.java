package com.bighit.on.cmn;

import java.util.List;

public interface DaoInterface {

	public int doInsert(DTO dto);
	
	public int doDelete(DTO dto);
	
	public int doUpdate(DTO dto);
	
	public DTO doSelectOne(DTO dto);
	
	public List<DTO> doSelectList(DTO dto);
	
}
