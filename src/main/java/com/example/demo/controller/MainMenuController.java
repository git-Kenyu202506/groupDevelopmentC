package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Employee;

@Controller
public class MainMenuController {

	@Autowired
	private HttpSession session;
	
	@RequestMapping("/employee/mainMenu")
	public String showMainMenu(Model m) {
		
		//ログイン情報のセッションを取得
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");
		
		m.addAttribute("id", id);
		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		
		return "mainMenu";
	}
	
	
	//社員情報登録画面へ遷移
	@RequestMapping("/insertForm")
	public String insertForm(Model model) {
		model.addAttribute("Employee", new Employee());
		return "insertForm";
	}
	
	//社員情報削除（入力）画面へ遷移
	@GetMapping("/employee/deleteInsert")
	public String showDelete() {
		return "deleteInsert";
	}
	
	//社員情報更新（社員ID入力）画面
	@GetMapping("/employee/update")
	public String showUpdate() {
		return "updateInsert";
	}
//		// 検索画面に転移(菅原さんファイル参照)
//		@GetMapping("/searchEmployee")
//		public String ConditionSearch(HttpSession session, Model m) {
//		// ダミー検索画面に転移
//	 
//	 		//ログイン情報のセッションを取得
//	 		String name = (String) session.getAttribute("name");
//	 		
//	 		m.addAttribute("name", name);
//	 		m.addAttribute("loginDateTime", loginDateTime);
//	 
//			return "searchEmployee";
//			return "searchDummyEmployee";


	
}
