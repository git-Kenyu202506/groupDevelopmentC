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
		
		//年齢の範囲チェック
		if(condition.getMinAge() != null && condition.getMaxAge() != null && 
		   condition.getMinAge() > condition.getMaxAge()) {
			throw new IllegalArgumentException("年齢の下限は上限以下にしてください");
		}
		
		//開始日の範囲チェック
		if(condition.getMinStartDate() != null && condition.getMaxStartDate() != null &&
		   condition.getMinEndDate().isAfter(condition.getMaxEndDate())) {
			throw new IllegalArgumentException("開始日の下限は上限以下にしてください");
		}
		
		//終了日の範囲チェック
		if(condition.getMinEndDate() != null && condition.getMaxEndDate() != null &&
		   condition.getMinEndDate().isAfter(condition.getMaxEndDate())) {
			throw new IllegalArgumentException("終了日の下限は上限以下にしてください");
		}
		
		return searchMapper.searchEmployee(condition);
	}
}
