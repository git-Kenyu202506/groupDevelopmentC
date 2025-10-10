package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.mapper.RegisterMapper;

@Service
public class RegisterService {

	@Autowired
	private RegisterMapper mapper;

	public void insert(Employee employee) {
		mapper.insert(employee);
	}

	//社員名の入力チェック
	public boolean isNameInputValid(Employee employee) {
		String name = employee.getName();
		return name != null && !name.trim().isEmpty();
	}

	//年齢の入力チェック(Entityでint型で定義のため 0以下の数字をnullとして扱う)
	public boolean isAgeInputValid(Employee employee) {
		return employee.getAge() > 0;
	}

	//パスワードの入力チェック
	public boolean isPasswordInputValid(Employee employee) {
		String pass = employee.getPassword();
		return pass != null && !pass.trim().isEmpty();
	}

	//確認用パスワードの入力チェック
	public boolean isPasswordCheckInputValid(Employee employee) {
		String passCheck = employee.getPassword_check();
		return passCheck != null && !passCheck.trim().isEmpty();
	}

	// パスワードの一致確認
	public boolean isPasswordMatching(Employee employee) {
		String pass = employee.getPassword();
		String passCheck = employee.getPassword_check();
		return pass != null && pass.equals(passCheck);
	}

	//  1つずつ数字を含む8文字以上の半角英数字のみ(英語大文字必須)
	public boolean isPasswordPatternValid(Employee employee) {
		String pass = employee.getPassword();
		return pass != null && pass.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
	}

}
