package com.bighit.on.channel;

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

@Repository("ChannelDaoImpl")
public class ChannelDaoImpl {
	final static Logger   LOG = LoggerFactory.getLogger(ChannelDaoImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	RowMapper rowMapper = new RowMapper<ChannelVO>() {
		@Override
		public ChannelVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			ChannelVO outVO = new ChannelVO();
			outVO.setChLink(rs.getString("ch_link"));
			outVO.setWsLink(rs.getString("ws_link"));
			outVO.setChName(rs.getString("ch_name"));
			outVO.setTopic(rs.getString("topic"));
			outVO.setChDescription(rs.getString("ch_description"));
			outVO.setChAccess(rs.getString("ch_access"));
			outVO.setRegId(rs.getString("reg_id"));
			outVO.setRegDt(rs.getString("reg_dt"));
			
			return outVO;
			
		}
	};
	
	/**
	 * 채널 생성
	 * @param channelVO
	 * @return
	 */
	public int doInsert(ChannelVO channelVO) {
		int flag=0;
		Object[] args = {channelVO.getChLink(),
					 	 channelVO.getWsLink(),
					 	 channelVO.getChName(),
					 	 channelVO.getTopic(),
					 	 channelVO.getChDescription(),
					 	 channelVO.getChAccess(),
					 	 channelVO.getRegId()
				
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO channels ( \n");
		sb.append("     ch_link,           \n");
		sb.append("     ws_link,           \n");
		sb.append("     ch_name,           \n");
		sb.append("     topic,             \n");
		sb.append("     ch_description,    \n");
		sb.append("     ch_access,         \n");
		sb.append("     reg_id,            \n");
		sb.append("     reg_dt             \n");
		sb.append(" ) VALUES (             \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     sysdate            \n");
		sb.append(" )                      \n");
		
		LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("=param ==="+channelVO);
		
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("-doInsert flag=" + flag);
		return flag;
	}
	
	/**
	 * 채널 삭제
	 * @param channelVO
	 * @return
	 */
	public int doDelete(ChannelVO channelVO) {
		int flag=0;
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM channels  \n");
		sb.append("WHERE ch_link = ?     \n");
		
		LOG.debug("=sql=\n"+sb.toString());
		LOG.debug("-param==" + channelVO);
		
		Object[] args = {channelVO.getChLink()};
		LOG.debug("-doDelete flag==" + flag);
		flag = this.jdbcTemplate.update(sb.toString(), args);
		return flag;
	}
	
	/**
	 * 채널 단건조회
	 * @param chLink
	 * @return
	 */
	public ChannelVO doSelectOne(ChannelVO channel) {
		ChannelVO outVO = null;
		
		StringBuilder  sb=new StringBuilder();
		sb.append("SELECT             \n");
		sb.append("    ch_link,       \n");
		sb.append("    ws_link,       \n");
		sb.append("    ch_name,       \n");
		sb.append("    topic,         \n");
		sb.append("    ch_description,\n");
		sb.append("    ch_access,     \n");
		sb.append("    reg_id,        \n");
		sb.append("    reg_dt         \n");
		sb.append("FROM channels      \n");
		sb.append("WHERE ch_link = ?  \n");
		
		LOG.debug("=sql="+sb.toString());
		LOG.debug("=param=="+channel);
		
		Object args[] = {channel.getChLink()};
		outVO = (ChannelVO) this.jdbcTemplate.queryForObject(sb.toString(), args,rowMapper);
		LOG.debug("=doSelectOne outVO="+outVO);
		
		return outVO;
		
	}
	
	/**
	 * 채널 리스트
	 * @param channelVO
	 * @return
	 */
	public List<ChannelVO> doSelectList(ChannelVO channelVO) {
		List<ChannelVO> list = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT             \n");
		sb.append("    ch_link,       \n");
		sb.append("    ws_link,       \n");
		sb.append("    ch_name,       \n");
		sb.append("    topic,         \n");
		sb.append("    ch_description,\n");
		sb.append("    ch_access,     \n");
		sb.append("    reg_id,        \n");
		sb.append("    reg_dt         \n");
		sb.append("FROM channels      \n");
		sb.append("WHERE ws_link = ?  \n");
		
		LOG.debug("---------------------------");
		LOG.debug("-sql-\n" + sb.toString());
		LOG.debug("-param-\n" + channelVO);
		LOG.debug("---------------------------");
		
		list = this.jdbcTemplate.query(sb.toString(),  new Object[] {"%"+channelVO.getWsLink()+"%"}, rowMapper);
		
		for(ChannelVO vo : list) {
			LOG.debug("===========================");
			LOG.debug("=doSelectList vo="+vo);
			LOG.debug("===========================");
		}
		
		return list;
		
	}
	
}
