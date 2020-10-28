package com.bighit.on.reminder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bighit.on.cmn.DTO;
import com.bighit.on.cmn.DaoInterface;

@Repository("reminderDao")
public class ReminderDaoImpl implements DaoInterface {
	final static Logger LOG = LoggerFactory.getLogger(ReminderDaoImpl.class);
	
	@Autowired
	private JdbcTemplate JdbcTemplate;
	
	public ReminderDaoImpl() {}
	
	
	@Override
	public int doInsert(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DTO doSelectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTO> doSelectList(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
