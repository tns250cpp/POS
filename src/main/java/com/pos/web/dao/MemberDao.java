package com.pos.web.dao;

import java.sql.ResultSet;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pos.web.entity.Member;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(Member member) {
		String sql = "INSERT INTO member (id, password, role) VALUES (?, ?, ?)";
		
		jdbcTemplate.update(sql, member.getId(), member.getPassword(), member.getRole());
	}

	public Member selectById(String id) {
		String sql = "SELECT * FROM member WHERE id = ?";
		
		List<Member> results = jdbcTemplate.query(sql, (ResultSet rs, int rownum) -> {
			Member member = new Member(rs.getString("id"), 
																rs.getString("password"), 
																rs.getString("role"));
			return member;
		}, id);
		return results.isEmpty() ? null : results.get(0);
	}

}
