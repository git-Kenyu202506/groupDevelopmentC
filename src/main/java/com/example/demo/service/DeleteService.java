package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.mapper.DeleteMapper;

@Service
public class DeleteService {
	@Autowired
	private DeleteMapper mapper;

	public void delete(int id) {
		mapper.delete(id);
	}

	public boolean nullDeleteError(Employee employee) {
		//変更予定
		return employee.getId() > 0;
	}

	public boolean recordEmptyDeleteError(Employee employee) {
		//テーブルの中に一致するものを探してnullならエラー 
		if (employee == null || employee.getId() == 0) {
			return true;
		}
		return false;
	}

	//
	public boolean loginUserDeleteError(int employeeId, int sessionUserId) {
		//sessionにある値と照合し一致すればfalseを返す
			return employeeId == sessionUserId;
		
	}
}
