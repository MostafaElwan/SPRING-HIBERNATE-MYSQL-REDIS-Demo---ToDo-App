package com.elwan.todo.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import com.elwan.todo.dao.TodoDAO;
import com.elwan.todo.dao.mysql.mapper.TodoMapper;
import com.elwan.todo.model.Todo;
import com.elwan.todo.model.User;

@Component
public class MysqlTodoDAOImpl extends AbstractMysqlDAO implements TodoDAO {
	
	private static final String ALL_OWNER = "select * from todo_list where todo_owner_id = ?";
	
	private static final String INSERT = "insert into todo_list (todo_title, todo_desc, todo_owner_id) values (?, ?, ?)";
	
	private static final String UPDATE = "update todo_list set todo_title = ?, todo_desc = ? where todo_id = ?"; 
	
	@Override
	public List<Todo> all() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Todo get(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public long create(final Todo t) {
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplateObject.update(new PreparedStatementCreator() {
		    @Override
		    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
		    	int index = 1;
		        PreparedStatement ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		        ps.setString(index++, t.getTitle());
		        ps.setString(index++, t.getDescription());
		        ps.setLong(index++, t.getUser().getId());
		        return ps;
		    }
		}, holder);

		return holder.getKey().longValue();
	}

	@Override
	public void update(Todo t) {
		jdbcTemplateObject.update(UPDATE, new Object[]{t.getTitle(), t.getDescription(), t.getId()});
	}

	@Override
	public void delete(Todo todo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Todo> all(User user) {
		List<Todo> todoList = null;
		try {
			todoList = jdbcTemplateObject.query(ALL_OWNER, new Object[]{user.getId()}, new TodoMapper());
		} catch(EmptyResultDataAccessException e) {
			if(! e.getMessage().equals("Incorrect result size: expected 1, actual 0")) {
				throw e;
			}
		}
		return todoList;
	}
}
