package next.dao;

import java.util.ArrayList;
import java.util.List;

import exception.DataAccessException;
import next.model.User;

public class UserDao {
	public void insert(User user) throws DataAccessException {
		PreparedStatementSetter pss = (pstmt) -> {
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
		};

		new JdbcTemplate().update("INSERT INTO USERS VALUES (?, ?, ?, ?)", pss);
	}

	public void update(User user) throws DataAccessException {
		PreparedStatementSetter pss = (pstmt) -> {
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUserId());
		};

		new JdbcTemplate().update("UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?", pss);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() throws DataAccessException {
		RowMapper<List<User>> rowMapper = (rs) -> {
			List<User> userList = new ArrayList<User>();
			while (rs.next()) {
				userList.add(
						new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email")));
			}
			return userList;
		};

		return new JdbcTemplate().query("SELECT userId, password, name, email FROM USERS", rowMapper);
	}

	public User findByUserId(String userId) throws DataAccessException {
		PreparedStatementSetter pss = (pstmt) -> {
			pstmt.setString(1, userId);
		};

		RowMapper<User> rowMapper = (rs) -> {
			User user = null;
			if (rs.next()) {
				return user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}
			return user;
		};

		return (User) new JdbcTemplate().queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?",
				rowMapper, pss);
	}

}
