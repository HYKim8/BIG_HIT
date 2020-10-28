package com.bighit.on.reminder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bighit.on.cmn.DTO;
import com.bighit.on.cmn.DaoInterface;

@Repository("ReminderDaoImpl")
public class ReminderDaoImpl implements DaoInterface {
	final static Logger LOG = LoggerFactory.getLogger(ReminderDaoImpl.class);
	
	@Autowired
	private JdbcTemplate JdbcTemplate;
	
	public ReminderDaoImpl() {}
	
	
	@Override
	public int doInsert(DTO dto) {
		int flag = 0;
		ReminderVO inVO = new ReminderVO();
		inVO = (ReminderVO) dto;
		Object[] args = {
			inVO.getThrKey(),
			inVO.getRemindTime(),
			inVO.getRegId()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO reminder (remind_id,                    \n");
		sb.append("						thr_key,                       \n");
		sb.append("						remind_time,                   \n");
		sb.append("						reg_id,                        \n");
		sb.append("						reg_dt) VALUES                 \n");
		sb.append("					(remind_seq.nextVal,?,?,?,sysdate) \n");
		
		LOG.debug("-------------------------");
		LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("=param=\n"+inVO);
		LOG.debug("-------------------------");
		
		flag = this.JdbcTemplate.update(sb.toString(), args);
		LOG.debug("-flag-"+flag);
		
		return flag;
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
