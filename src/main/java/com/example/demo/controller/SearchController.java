package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.dto.SearchCondition;
import com.example.demo.entity.Employee;
import com.example.demo.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private HttpSession session;
	
	
	//検索機能
	@GetMapping("/employee/search")
	public String ConditionSearch(@ModelAttribute SearchCondition condition, Model m) {
		
		//年齢の範囲チェック
		if(condition.getMinAge() != null && condition.getMaxAge() != null && 
		   condition.getMinAge() > condition.getMaxAge()) {
		   m.addAttribute("errorMessage", "年齢の最大値が最小値を超えないようにしてください");
		}
		
		//開始日の範囲チェック
		if(condition.getMinStartDate() != null && condition.getMaxStartDate() != null &&
		   condition.getMinEndDate().isAfter(condition.getMaxEndDate())) {
			m.addAttribute("errorMessage", "開始日の最大値が最小値を超えないようにしてください");
		}
		
		//終了日の範囲チェック
		if(condition.getMinEndDate() != null && condition.getMaxEndDate() != null &&
		   condition.getMinEndDate().isAfter(condition.getMaxEndDate())) {
			m.addAttribute("errorMessage", "終了日の最大値が最小値を超えないようにしてください");
		}
		
		
		List<Employee> search = searchService.searchEmployee(condition);
		m.addAttribute("search", search);
		
		
		//セッションのユーザー情報を取得（ログインのコントローラーができたら追加）
		
		
		return "searchEmployee";
	}
}
