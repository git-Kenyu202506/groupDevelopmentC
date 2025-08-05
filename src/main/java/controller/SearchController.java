package controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dto.SearchCondition;
import service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private HttpSession session;
	
	
	//検索機能
	@RequestMapping("/employee/serch")
	public String ConditionSearch(RedirectAttributes ra,
			                      SearchCondition condition) {
		
		
		
		return "searchEmployee";
	}
}
