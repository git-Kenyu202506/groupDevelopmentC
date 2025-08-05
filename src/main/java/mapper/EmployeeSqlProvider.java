package mapper;

import java.util.Map;

public class EmployeeSqlProvider {
	public static String buildSearchQuery(Map<String, Object> params) {
		StringBuilder sql = new StringBuilder("SELECT * FROM employee WHERE 1=1");
		
		//社員IDの検索クエリ
		if(params.get("id") != null) {
			sql.append("AND id = #{id}");
		}
		
		//社員名の部分一致検索クエリ
		if(params.get("name") != null && !params.get("name").toString().isEmpty()) {
			sql.append("AND name LIKE CONCAT('%', #{name}, '%')");
		}
		
		//年齢の範囲検索クエリ
		if(params.get("minAge") != null) {
			sql.append("AND age >= #{minAge}");
		}
		
		if(params.get("maxAge") != null) {
			sql.append("AND age <= #{maxAge}");
		}
		
		//開始日の範囲検索クエリ
		if(params.get("minStartDate") != null) {
			sql.append("AND startDate >= #{minStartDate}");
		}
		
		if(params.get("maxStartDate") != null) {
			sql.append("AND startDate <= #{maxStartDate}");
		}
		
		//終了日の範囲検索クエリ
		if(params.get("minEndDate") != null) {
			sql.append("AND endDate >= #{minEndDate}");
		}
		
		if(params.get("maxEndDate") != null) {
			sql.append("AND endDate <= #{maxEndDate}");
		}
		
		return sql.toString();
	}
}
