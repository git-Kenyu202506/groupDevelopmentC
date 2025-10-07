package com.example.demo.controller;

import java.time.LocalDate;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Employee;
import com.example.demo.service.RegisterService;

// http://localhost:8080/insertForm

@Controller
@RequestMapping("/employee")
public class RegisterController {
	@Autowired
	private RegisterService service;
	//フィールドに@Autowiredするとnullになりやすい

	// 社員情報登録(入力)へ転移
	@RequestMapping("/insertForm")
	public String insertForm(Model m, HttpSession session) {

		//Integer id = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		m.addAttribute("Employee", new Employee());
		return "insertForm";
	}

	//入力された情報の確認画面
	@PostMapping("/insert_check")
	public String insert_check(
			@ModelAttribute("Employee") @Validated Employee employee,
			BindingResult bindingResult,
			HttpSession session,
			Model m) {

		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		m.addAttribute("Employee", employee);

		//社員名の入力チェック
		if (!service.isNameInputValid(employee)) {
			m.addAttribute("nameNull", "社員名を入力してください");
			return "insertForm";
		}

		if (!service.isAgeInputValid(employee)) {
			m.addAttribute("ageNull", "有効な年齢を入力してください");
			return "insertForm";
		}
		if (!service.isPasswordInputValid(employee)) {
			m.addAttribute("passwordNull", "パスワードを入力してください");
			return "insertForm";
		}
		// パスワードのフォーマット確認
		if (!service.isPasswordPatternValid(employee)) {
			m.addAttribute("passwordFormError", "パスワードは半角英数字を含めた8文字以上で入力してください");
			return "insertForm";
		}
		if (!service.isPasswordCheckInputValid(employee)) {
			m.addAttribute("password_checkNull", "確認用のパスワードを入力してください");
			return "insertForm";
		}
		// パスワードの一致確認
		if (!service.isPasswordMatching(employee)) {
			m.addAttribute("passwordUnmatch", "パスワードが一致しません。");
			return "insertForm";
		}

		return "insert_check";
	}

	// 登録完了画面へ転移(登録した日を取得)
	@PostMapping("/insert")
	public String insertEmployeee(@ModelAttribute Employee employee, HttpSession session, Model m) {
		if (employee.getStartDate() == null) {
			employee.setStartDate(LocalDate.now());
		}

		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		service.insert(employee);
		return "insert_result";
	}

	// メインメニュー画面に転移(菅原さんファイル参照)
	@GetMapping("/backToMainMenu")
	public String backToMainMenu(Model m, HttpSession session) {

		//ログイン情報のセッションを取得
		Integer id = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		return "mainMenu";
	}

	@PostMapping("/backInsertForm")
	public String backInsertForm(@ModelAttribute("Employee") Employee employee, HttpSession session, Model m) {

		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		m.addAttribute("Employee", employee);
		return "insertForm";
	}

	// メインメニュー画面に転移(菅原さんファイル参照)
	@RequestMapping("/backMainMenu")
	public String backMainMenu(Model m, HttpSession session) {

		//ログイン情報のセッションを取得
		Integer id = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		return "mainMenu";
	}

	// ダミー検索画面に転移
	@GetMapping("/searchDummyEmployee")
	public String showSearchForm(HttpSession session, Model m) {

		//ログイン情報のセッションを取得
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);

		return "searchDummyEmployee";
	}
}
