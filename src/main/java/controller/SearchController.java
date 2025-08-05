package controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.SearchCondition;
import entity.Employee;
import service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private HttpSession session;
	
	
	//検索機能
	@RequestMapping("/employee/serch")
	public String ConditionSearch(Model m,
			                      SearchCondition condition) {
		
		List<Employee> search = searchService.searchEmployee(condition);
		m.addAttribute("search", search);
		
		return "searchEmployee";
	}
}
