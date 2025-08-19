package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		//ログイン情報のセッションを取得
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");
		
		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		
		//全ての検索条件がnullか判定
		boolean isFirstAccess = (condition.getId() == null &&
				                 condition.getName() == null &&
				                 condition.getMinAge() == null &&
				                 condition.getMaxAge() == null &&
				                 condition.getMinStartDate() == null &&
				                 condition.getMaxStartDate() == null &&
				                 condition.getMinEndDate() == null &&
				                 condition.getMaxEndDate() == null);
		
		m.addAttribute("searchCondition", condition); //入力内容を保持
		
		if(isFirstAccess) {
			m.addAttribute("resultCount", 0);
			return "searchEmployee";
		}
		
		
		//入力チェック
		String errorMessage = searchService.validationCheck(condition);
		if(errorMessage != null) {
			m.addAttribute("errorMessage", errorMessage);
			m.addAttribute("resultCount", 0); //件数を0件表示にする
			
			return "searchEmployee";
		}
		
		
		//検索機能
		List<Employee> search = searchService.searchEmployee(condition);
		
		//件数が0件のとき
		if(search.isEmpty()) {
			m.addAttribute("resultCount", 0);
			m.addAttribute("errorMessage", "該当するデータはありません");
		}else {
			//件数がある場合のみ一覧を渡す
			m.addAttribute("search", search);
			m.addAttribute("resultCount", search.size()); //件数を取得
		}
		
		return "searchEmployee";
	}
	
	
	//社員情報更新画面へIDを渡して遷移する機能
	@PostMapping("/employee/update/{id}")
	public String passId(@PathVariable("id") int id, Model m) {
		Employee update = searchService.selectById(id);
		
		m.addAttribute("update", update);
		
		return "updateEmployee";
	}
	
	
	//社員情報削除（確認）画面へ遷移
	@PostMapping("/employee/deleteConfirm")
	public String passDlete(@RequestParam List<Integer> ids, Model m) {
		
		m.addAttribute("ids", ids);
		
		return "deleteConfirm";
	}
	
	
}
