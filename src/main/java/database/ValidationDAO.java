package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class ValidationDAO {

	public String getTime(DataSource ds) throws Exception {

		Connection con = ds.getConnection();
		PreparedStatement stmt = con.prepareStatement("select now()");
		ResultSet result = stmt.executeQuery();
		result.next();
		con.close();
		return result.getString(1);
	}

	public String insertEmail(DataSource ds, String eMail, String encVal) throws SQLException {
		String query = "insert into emailvalid (email, hashcode, checked) values (?,?,?)";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(query);
			stmt.setString(1, eMail);
			stmt.setString(2, encVal);
			stmt.setInt(3, 0);
			stmt.executeUpdate();
			return "success";
		} catch (MySQLIntegrityConstraintViolationException e) {
			return "fail";
		} finally {
			stmt.close();
			con.close();
		}
	}

	public String checkEmail(DataSource ds, String hash) throws SQLException {
		String query = "SELECT * FROM emailvalid WHERE hashcode = ? AND checked = 0";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement(query);
			stmt.setString(1, hash);
			result = stmt.executeQuery();
			result.next();
			String eMail = result.getString(1);
			stmt.close();
			query = "UPDATE emailvalid SET checked = 1 where hashcode = ?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, hash);
			stmt.executeUpdate();
			return eMail;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
			result.close();
		}
		return "fail";

	}
}
