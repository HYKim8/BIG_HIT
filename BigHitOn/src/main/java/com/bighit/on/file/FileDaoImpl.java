package com.bighit.on.file;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bighit.on.cmn.DTO;
import com.bighit.on.reminder.ReminderDaoImpl;
import com.bighit.on.reminder.ReminderVO;

@Repository("FileDaoImpl")
public class FileDaoImpl {
	final static Logger LOG = LoggerFactory.getLogger(ReminderDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public FileDaoImpl() {
		super();
	}

	RowMapper rowMapper = new RowMapper<FileVO>() {
		@Override
		public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			FileVO outVO = new FileVO();
			
			
			
			return outVO;
		}

	};
	
	/**
	 * doInsert
	 * @param dto
	 * @return
	 */
	public int doInsert(DTO dto) {
		int flag = 0;
		FileVO inVO = (FileVO) dto;
		Object[] args = {
				inVO.getFileName(),
				inVO.getThrKey(),
				inVO.getRegId()
		};
		
		StringBuilder sb = new StringBuilder();

		LOG.debug("---------------------------");
		LOG.debug("-sql-\n" + sb.toString());
		LOG.debug("-param-\n" + inVO);
		LOG.debug("---------------------------");

		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("---------------------------");
		LOG.debug("-doInsert flag-" + flag);
		LOG.debug("---------------------------");

		return flag;
	}

	/**
	 * doDelete
	 * @param dto
	 * @return
	 */
	public int doDelete(DTO dto) {
		int flag = 0;
		FileVO inVO = (FileVO) dto;
		Object[] args = {
				inVO.getFileName(),
				inVO.getThrKey()
		};
		
		StringBuilder sb = new StringBuilder();
		
		LOG.debug("---------------------------");
		LOG.debug("-sql-\n" + sb.toString());
		LOG.debug("-param-\n" + inVO);
		LOG.debug("---------------------------");

		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("---------------------------");
		LOG.debug("-doDelete flag-" + flag);
		LOG.debug("---------------------------");
		
		return flag;
	}

	/**
	 * doUpdate
	 * @param dto
	 * @return
	 */
	public int doUpdate(DTO dto) {
		int flag = 0;
		FileVO inVO = (FileVO) dto;
		Object[] args = {
				
		};
		
		StringBuilder sb = new StringBuilder();
		
		LOG.debug("---------------------------");
		LOG.debug("-sql-\n" + sb.toString());
		LOG.debug("-param-\n" + inVO);
		LOG.debug("---------------------------");
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("---------------------------");
		LOG.debug("-doUpdate flag-" + flag);
		LOG.debug("---------------------------");
		
		return flag;
	}

	/**
	 * doSelectOne
	 * @param dto
	 * @return
	 */
	public DTO doSelectOne(DTO dto) {
		FileVO inVO = (FileVO) dto;
		FileVO outVO = null;
		Object[] args = {
				
		};
		
		StringBuilder sb = new StringBuilder();
		
		LOG.debug("---------------------------");
		LOG.debug("-sql-\n" + sb.toString());
		LOG.debug("-param-\n" + inVO);
		LOG.debug("---------------------------");

		outVO = (FileVO) this.jdbcTemplate.queryForObject(sb.toString(), 
				args, rowMapper);

		LOG.debug("---------------------------");
		LOG.debug("-doSelectOne outVO-" + outVO);
		LOG.debug("---------------------------");
		
		return outVO;
	}

	/**
	 * doSelectList
	 * @param dto
	 * @return
	 */
	public List<FileVO> doSelectList(DTO dto) {
		FileVO inVO = (FileVO) dto;
		List<FileVO> outList = null;
		Object[] args = {
				
		};
		
		StringBuilder sb = new StringBuilder();
		
		LOG.debug("---------------------------");
		LOG.debug("-sql-\n" + sb.toString());
		LOG.debug("-param-\n" + inVO);
		LOG.debug("---------------------------");
		
		outList = this.jdbcTemplate.query(sb.toString(), args, rowMapper);
		
		for(FileVO vo : outList) {
			LOG.debug("---------------------------");
			LOG.debug("-doSelectList outVO-" + vo);
			LOG.debug("---------------------------");
		}
		
		return outList;
	}
	
	
	
	
}

