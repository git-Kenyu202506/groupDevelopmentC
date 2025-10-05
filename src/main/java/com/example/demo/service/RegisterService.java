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

	public boolean isNameInputValid(Employee employee) {
		String name = employee.getName();
		return name != null && !name.trim().isEmpty();
	}

	public boolean isAgeInputValid(Employee employee) {
		return employee.getAge() > 0;
	}

	public boolean isPasswordInputValid(Employee employee) {
		return employee.getPassword() != null && !employee.getPassword().trim().isEmpty();
	}

	public boolean isPasswordCheckInputValid(Employee employee) {
		return employee.getPassword_check() != null && !employee.getPassword_check().trim().isEmpty();
	}

	public boolean isPasswordMatching(Employee employee) {
		if (employee.getPassword() == null || employee.getPassword_check() == null) {
			return false;
		}
		return employee.getPassword().equals(employee.getPassword_check());
	}

	public boolean isPasswordPatternValid(Employee employee) {
		String pass = employee.getPassword();
		if (pass == null)
			return false;
		//  1つずつ数字を含む8文字以上の半角英数字のみ
		return pass.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
	}

}
