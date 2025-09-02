package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

public class RegisterService {

	@Autowired
	private RegisterMapper mapper;
	
	
	Employee employee = new Employee();

	public void insert(Employee employee) {
		mapper.insert(employee);
	}
	
	public void setPassword_check(String password_check) {
		employee.password_check = password_check;
	}

	public boolean isPasswordMatching() {
		return password != null && password.equals(password_check);
	}

	public boolean isPasswordPatternValid() {
		if (password == null) {
			return false;
		}
		//  1つずつ数字を含む半角英数字のみ
		return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$");
	}
}
