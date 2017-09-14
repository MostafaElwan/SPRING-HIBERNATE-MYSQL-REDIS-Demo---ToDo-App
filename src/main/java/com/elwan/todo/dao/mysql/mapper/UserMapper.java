package com.elwan.todo.dao.mysql.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.elwan.todo.model.User;

public class UserMapper implements RowMapper<User> {
	
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("user_id"));
		u.setUsername(rs.getString("username"));
		u.setPassword(rs.getString("password"));
		u.setEmail(rs.getString("email"));
		u.setName(rs.getString("fullname"));
		return u;
	}
}
