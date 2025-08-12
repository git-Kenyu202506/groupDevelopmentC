package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.SearchCondition;
import com.example.demo.entity.Employee;
import com.example.demo.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping("/employee/search")
	public String ConditionSearch(@ModelAttribute SearchCondition condition, Model m) {
		
		//入力チェック
		String errorMessage = searchService.validationCheck(condition);
		if(errorMessage != null) {
			m.addAttribute("errorMessage", errorMessage);
			m.addAttribute("searchCondition", condition); //入力内容を保持
			
			return "searchEmployee";
		}
		
		//検索機能
		List<Employee> search = searchService.searchEmployee(condition);
		m.addAttribute("search", search);
		m.addAttribute("searchCondition", condition); //入力内容を保持
		
		
		//セッションのユーザー情報を取得（ログインのコントローラーができたら追加）
		
		
		return "searchEmployee";
	}
}
