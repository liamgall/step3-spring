package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.example.step3.model.User;

public class RegisterDAO {
	
	public String insertDB(DataSource ds, User userForm, String file) throws SQLException{

		String query = "insert into user values(?,?,?,?,?,?,?,?)";
//		String query = "insert into user values(1,2,3,4,5,6,7,8)";
		Connection con = null;
		PreparedStatement stmt = null;
		try{
			con = ds.getConnection();
			stmt = con.prepareStatement(query);
			stmt.setString(1, userForm.getEmail().toString());
			stmt.setString(2, userForm.getPostcode5().toString());
			stmt.setString(3, userForm.getAddress().toString());
			stmt.setString(4, userForm.getDetails().toString());
			stmt.setString(5, userForm.getExtra_info().toString());
			stmt.setString(6, userForm.getPhoneNumber().toString());
			stmt.setString(7, userForm.getPassword().toString());
			stmt.setString(8, "/resources/attatchments/"+ file);
			stmt.executeUpdate();
			return "success";
		}catch(Exception e){
			return "fail";
		}finally{
			con.close();
			stmt.close();
		}
	}
}
