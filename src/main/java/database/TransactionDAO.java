package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.step3.model.Email;

public class TransactionDAO {
	
	JdbcTemplate template;
	PlatformTransactionManager transactionManager;
	public JdbcTemplate getTemplate() {
		return template;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public void insertEmail(final Email email){
		System.out.println("insertEmail");
		
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try{
			
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					// TODO Auto-generated method stub
					String query = "insert into emailvalid values(?,?,?)";
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setString(1, email.getEmail());
					pstmt.setString(2, email.getHashcode());
					pstmt.setInt(3, email.getChecked());
					return pstmt;
				}
			});
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
