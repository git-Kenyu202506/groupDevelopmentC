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

	//入力がない場合のエラー(Entityがint型のため 0以下の場合 nullとする)
	public boolean nullDeleteError(Employee employee) {
		return employee.getId() > 0;
	}

	//該当のIDがない場合のエラー
	public boolean recordEmptyDeleteError(Employee employee) {
		if (employee == null || employee.getId() == 0) {
			return false;
		}
		Employee dbEmployee = mapper.findById(employee.getId());
		return dbEmployee != null;
	}

	//ログイン中のユーザーの削除エラー
	public boolean loginUserDeleteError(int employeeId, int sessionUserId) {
		//sessionにある値と照合し一致すればfalseを返す
		return employeeId == sessionUserId;
	}
}
