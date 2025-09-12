package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Employee;
import com.example.demo.service.DeleteService;

// http://localhost:8080/employee/deleteForm

@Controller
@RequestMapping("/employee")
public class DeleteController {

	@Autowired
	private DeleteService service;
	@Autowired
	private HttpSession session;

	//削除するID確認
	@PostMapping("/delete_check")
	public String delete_check(@Validated Employee employee,
			BindingResult bindingResult, Model model) {
		//入力エラーがあれば入力フォームに転移
		if (bindingResult.hasErrors()) {
			return "deleteForm";
		}
		// 入力されたIDが存在しないエラー

		//		if (!employee.isIdPatternValid()) {
		//			model.addAttribute("idFormError", "入力されたIDの情報が存在しません");
		//			return "deleteForm";
		//		}
		// ログインしているユーザーの情報は削除できない ログイン中のid情報を取得し
		// if( lodinしてるUserのインスタンス != null &&  !employee.isDeleteLodinUserError()){
		//		model.addAttribute("delete_usererror", "ログイン中のユーザーは削除できません");
		//		return "deleteForm";
		//}

		model.addAttribute("Employee", employee);
		return "delete_check";
	}

	// 削除完了画面に転移
	@PostMapping("/delete")
	public String deleteEmployee(Model model, @RequestParam("id") String id) {
		int numId = Integer.parseInt(id);
		service.delete(numId);
		model.addAttribute("msg", "社員情報の削除が完了しました");
		return "delete_result";
	}

}
