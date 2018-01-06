package exception;

import java.sql.SQLException;

public class DataAccessException extends RuntimeException {

	public DataAccessException(SQLException e) {
		super(e);
	}

	private static final long serialVersionUID = 1L;
	
}
