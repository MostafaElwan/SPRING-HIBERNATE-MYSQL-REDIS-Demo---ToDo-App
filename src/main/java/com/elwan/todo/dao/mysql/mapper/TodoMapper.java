package com.elwan.todo.dao.mysql.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.elwan.todo.model.Todo;

public class TodoMapper implements RowMapper<Todo> {
	
	public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Todo td = new Todo();
		td.setId(rs.getInt("todo_id"));
		td.setTitle(rs.getString("todo_title"));
		td.setDescription(rs.getString("todo_desc"));
		return td;
	}
}
