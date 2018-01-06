package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.ConnectionManager;
import exception.DataAccessException;

public class JdbcTemplate {

	public void update(String sql, PreparedStatementSetter pss) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			if (pss != null)
				pss.setValues(pstmt);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	public void update(String sql, Object... values) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			PreparedStatementSetter pss = createPreparedStatementSetter(values);
			
			if (pss != null)
				pss.setValues(pstmt);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public Object queryForObject(String sql, RowMapper rowMapper, PreparedStatementSetter pss) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			if (pss != null)
				pss.setValues(pstmt);

			return rowMapper.mapRow(pstmt.executeQuery());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Object queryForObject(String sql, RowMapper rowMapper, Object... values) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			PreparedStatementSetter pss = createPreparedStatementSetter(values);
			
			if (pss != null)
				pss.setValues(pstmt);

			return rowMapper.mapRow(pstmt.executeQuery());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public List query(String sql, RowMapper rowMapper, PreparedStatementSetter pss) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			if (pss != null)
				pss.setValues(pstmt);

			return (List) rowMapper.mapRow(pstmt.executeQuery());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List query(String sql, RowMapper rowMapper, Object... values) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			PreparedStatementSetter pss = createPreparedStatementSetter(values);
			
			if (pss != null)
				pss.setValues(pstmt);

			return (List) rowMapper.mapRow(pstmt.executeQuery());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	public PreparedStatementSetter createPreparedStatementSetter(Object... values) {
		return new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
		};
	}

}
