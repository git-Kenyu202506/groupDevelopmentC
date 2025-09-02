package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Employee;
import com.example.demo.mapper.RegisterMapper;

public class RegisterService {

	@Autowired
	private RegisterMapper mapper;
	
	Employee employee = new Employee();

	public void insert(Employee employee) {
		mapper.insert(employee);
	}

	public boolean isPasswordMatching() {
		return employee.getPassword() != null && employee.getPassword().equals(employee.getPassword_check());
	}

	public boolean isPasswordPatternValid() {
		if (employee.getPassword() == null) {
			return false;
		}
		//  1つずつ数字を含む半角英数字のみ
		return employee.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$");
	}
}
