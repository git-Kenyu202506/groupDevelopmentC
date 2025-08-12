package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SearchCondition;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.SearchMapper;

@Service
public class SearchService {
	
	@Autowired
	private SearchMapper searchMapper;
	
	public List<Employee> searchEmployee(SearchCondition condition) {
		return searchMapper.searchEmployee(condition);
	}
}
