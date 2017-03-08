package com.example.step3.DAO;

import com.example.step3.model.Email;

public interface EmailDAO {
	public int insertDAO(Email email);
	public void deleteDAO(int hashcode);
	public String findDAO(int hashcode);
	
}
