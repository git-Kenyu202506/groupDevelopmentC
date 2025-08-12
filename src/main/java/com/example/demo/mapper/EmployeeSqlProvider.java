package com.example.demo.mapper;

import java.util.Map;

import com.example.demo.dto.SearchCondition;

public class EmployeeSqlProvider {
	public static String buildSearchQuery(Map<String, Object> params) {
		SearchCondition condition = (SearchCondition) params.get("condition");
		StringBuilder sql = new StringBuilder("SELECT * FROM employee WHERE 1=1");
		
		//社員IDの検索クエリ
		if(condition.getId() != null) {
			sql.append("AND id = #{condition.id}");
		}
		
		//社員名の部分一致検索クエリ
		if(condition.getName() != null && !condition.getName().isEmpty()) {
			sql.append("AND name LIKE CONCAT('%', #{condition.name}, '%')");
		}
		
		//年齢の範囲検索クエリ
		if(condition.getMinAge() != null) {
			sql.append("AND age >= #{condition.minAge}");
		}
		
		if(condition.getMaxAge() != null) {
			sql.append("AND age <= #{condition.maxAge}");
		}
		
		//開始日の範囲検索クエリ
		if(condition.getMinStartDate() != null) {
			sql.append("AND startDate >= #{condition.minStartDate}");
		}
		
		if(condition.getMaxStartDate() != null) {
			sql.append("AND startDate <= #{condition.maxStartDate}");
		}
		
		//終了日の範囲検索クエリ
		if(condition.getMinEndDate() != null) {
			sql.append("AND endDate >= #{condition.minEndDate}");
		}
		
		if(condition.getMaxEndDate() != null) {
			sql.append("AND endDate <= #{condition.maxEndDate}");
		}
		
		return sql.toString();
	}
}
