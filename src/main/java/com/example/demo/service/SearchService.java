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
	
	//バリデーションチェック
	public String validationCheck(SearchCondition condition) {
		
		//IDの数値チェック(入力されたidがnullではない且つ数値が0未満のとき)
		if(condition.getId() != null && condition.getId() < 0) {
			return "社員IDは正の数値を入力してください";
		}
		
		
		//年齢の数値チェック（入力されたminAgeとmaxAgeがnullではない且つ数値が0未満のとき）
		if(condition.getMinAge() != null && condition.getMinAge() < 0) {
			return "年齢は正の数値を入力してください";
		}
		
		
		if(condition.getMaxAge() != null && condition.getMaxAge() < 0) {
			return "年齢は正の数値を入力してください";
		}
		
		
		//年齢の範囲チェック（入力されたminAgeとmaxAgeがnullではない且つminAge>maxAgeのとき）
		if(condition.getMinAge() != null && condition.getMaxAge() != null && 
		   condition.getMinAge() > condition.getMaxAge()) {
			return "年齢の最小値が最大値を上回らないようにしてください";
		}
		
		
		//開始日の範囲チェック（入力されたstartmMinDateとstartMaxDateがnullではない且つstartmMinDate>startMaxDateのとき）
		if(condition.getMinStartDate() != null && condition.getMaxStartDate() != null &&
		   condition.getMinStartDate().isAfter(condition.getMaxStartDate())) {
			return "開始日の最小値が最大値を上回らないようにしてください";
		}
		
		
		//終了日の範囲チェック（入力されたendmMinDateとendMaxDateがnullではない且つendMinDate>endMaxDateのとき）
		if(condition.getMinEndDate() != null && condition.getMaxEndDate() != null &&
		   condition.getMinEndDate().isAfter(condition.getMaxEndDate())) {
			return "終了日の最小値が最大値が上回らないようにしてください";
		}
		
		
		//エラーなし
		return null;
	}
	
	
	//検索処理
	public List<Employee> searchEmployee(SearchCondition condition) {
		return searchMapper.searchEmployee(condition);
	}
	
	
	//条件検索
	public Employee selectById(int id) {
		return searchMapper.selectById(id);
	}
}
