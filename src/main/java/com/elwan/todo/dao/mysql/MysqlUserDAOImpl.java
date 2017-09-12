package com.elwan.todo.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import com.elwan.todo.dao.UserDAO;
import com.elwan.todo.dao.mysql.mapper.UserMapper;
import com.elwan.todo.model.User;

@Component
public class MysqlUserDAOImpl extends AbstractMysqlDAO implements UserDAO {
	
	private static final String GET = "select * from users where username = ? and encrypted_password = ?";
	
	private static final String INSERT = "insert into users(name, email, username, encrypted_password, unique_id, salt) values (?, ?, ?, ?, ?, ?)";
	
	@Override
	public List<User> all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User get(String username, String password) {
		User u = null;
		try {
			u = jdbcTemplateObject.queryForObject(GET, new Object[]{username, password}, new UserMapper());
		} catch(EmptyResultDataAccessException e) {
			if(! e.getMessage().equals("Incorrect result size: expected 1, actual 0")) {
				throw e;
			}
		}
		return u;
	}

	@Override
	public long create(final User u) {
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplateObject.update(new PreparedStatementCreator() {
		    @Override
		    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
		    	int index = 1;
		        PreparedStatement ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		        ps.setString(index++, u.getName());
		        ps.setString(index++, u.getEmail());
		        ps.setString(index++, u.getUsername());
		        ps.setString(index++, u.getPassword());
		        ps.setString(index++, UUID.randomUUID().toString().substring(0,10));
		        ps.setString(index++, UUID.randomUUID().toString().substring(0,10));
		        return ps;
		    }
		}, holder);

		return holder.getKey().longValue();
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	
}
