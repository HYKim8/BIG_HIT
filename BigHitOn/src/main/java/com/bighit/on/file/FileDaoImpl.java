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
			
			outVO.setFileId(rs.getInt("file_id"));
			outVO.setFileName(rs.getString("file_name"));
			outVO.setThrKey(rs.getString("thr_key"));
			outVO.setFileUrl(rs.getString("file_url"));
			outVO.setRegDt(rs.getString("reg_dt"));
			outVO.setRegId(rs.getString("reg_id"));
			outVO.setChLink(rs.getString("ch_link"));
						
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
				inVO.getFileUrl(),
				inVO.getRegId(),
				inVO.getChLink()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO file_thr (     \n");
		sb.append("    file_id,               \n");
		sb.append("    file_name,             \n");
		sb.append("    thr_key,               \n");
		sb.append("    file_url,              \n");
		sb.append("    reg_id,                \n");
		sb.append("    reg_dt,                \n");
		sb.append("    ch_link                \n");
		sb.append(") VALUES (                 \n");
		sb.append("    file_seq.nextVal,      \n");
		sb.append("    ?,                     \n");
		sb.append("    ?,                     \n");
		sb.append("    ?,                     \n");
		sb.append("    ?,                     \n");
		sb.append("    SYSDATE,               \n");
		sb.append("    ?                      \n");
		sb.append(")                          \n");
		
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
			inVO.getFileId(),
			inVO.getRegId()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM file_thr       \n");
		sb.append("WHERE                      \n");
		sb.append("        file_id = ? and    \n");
		sb.append("        reg_id = ?         \n");
		
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
			inVO.getFileName(),
			inVO.getFileId(),
			inVO.getRegId()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE file_thr           \n");
		sb.append("SET                       \n");
		sb.append("    file_name = ?         \n");
		sb.append("WHERE                     \n");
		sb.append("        file_id = ?       \n");
		sb.append("    AND reg_id = ?        \n");
		
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
			inVO.getFileId()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT                    \n");
		sb.append("    file_id,              \n");
		sb.append("    file_name,            \n");
		sb.append("    thr_key,              \n");
		sb.append("    file_url,             \n");
		sb.append("    reg_id,               \n");
		sb.append("    reg_dt,               \n");
		sb.append("    ch_link               \n");
		sb.append("FROM                      \n");
		sb.append("    file_thr              \n");
		sb.append("WHERE                     \n");
		sb.append("    file_id = ?           \n");
		
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
	public List<FileVO> doSelectListThrKey(DTO dto) {
		FileVO inVO = (FileVO) dto;
		List<FileVO> outList = null;
		Object[] args = {
			inVO.getThrKey()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT                     \n");
		sb.append("    file_id,               \n");
		sb.append("    file_name,             \n");
		sb.append("    thr_key,               \n");
		sb.append("    file_url,              \n");
		sb.append("    reg_id,                \n");
		sb.append("    reg_dt,                \n");
		sb.append("    ch_link                \n");
		sb.append("FROM                       \n");
		sb.append("    file_thr               \n");
		sb.append("WHERE                      \n");
		sb.append("    thr_key = ?            \n");
		sb.append("order by file_id           \n");
		
		LOG.debug("---------------------------");
		LOG.debug("-sql-\n" + sb.toString());
		LOG.debug("-param-\n" + inVO);
		LOG.debug("---------------------------");
		
		outList = this.jdbcTemplate.query(sb.toString(), args, rowMapper);
		
		LOG.debug("---------------------------");
		for(FileVO vo : outList) {
			LOG.debug("-doSelectList outVO-" + vo);
		}
		LOG.debug("---------------------------");
		
		return outList;
	}
	
	public List<FileVO> doSelectListChLink(DTO dto) {
		FileVO inVO = (FileVO) dto;
		List<FileVO> outList = null;
		Object[] args = {
			inVO.getChLink()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT                     \n");
		sb.append("    file_id,               \n");
		sb.append("    file_name,             \n");
		sb.append("    thr_key,               \n");
		sb.append("    file_url,              \n");
		sb.append("    reg_id,                \n");
		sb.append("    reg_dt,                \n");
		sb.append("    ch_link                \n");
		sb.append("FROM                       \n");
		sb.append("    file_thr               \n");
		sb.append("WHERE                      \n");
		sb.append("    ch_link = ?            \n");
		sb.append("order by file_id           \n");
		
		LOG.debug("---------------------------");
		LOG.debug("-sql-\n" + sb.toString());
		LOG.debug("-param-\n" + inVO);
		LOG.debug("---------------------------");
		
		outList = this.jdbcTemplate.query(sb.toString(), args, rowMapper);
		
		LOG.debug("---------------------------");
		for(FileVO vo : outList) {
			LOG.debug("-doSelectList outVO-" + vo);
		}
		LOG.debug("---------------------------");
		
		return outList;
	}
	
	
	
	
}

