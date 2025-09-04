package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.mapper.RegisterMapper;

@Service
public class RegisterService {

	@Autowired
	private RegisterMapper mapper;
	
	Employee employee = new Employee();

	public void insert(Employee employee) {
		mapper.insert(employee);
	}

	public boolean isPasswordMatching(Employee employee) {
		return employee.getPassword() != null && employee.getPassword().equals(employee.getPassword_check());
	}

	public boolean isPasswordPatternValid(Employee employee) {
		if (employee.getPassword() == null) {
			return false;
		}
		//  1つずつ数字を含む半角英数字のみ
		return employee.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$");
	}
}
