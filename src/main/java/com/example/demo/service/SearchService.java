package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.SearchCondition;
import entity.Employee;
import mapper.SearchMapper;

@Service
public class SearchService {
	
	@Autowired
	private SearchMapper searchMapper;
	
	public List<Employee> searchEmployee(SearchCondition condition) {
		return searchMapper.searchEmployee(condition);
	}
}
