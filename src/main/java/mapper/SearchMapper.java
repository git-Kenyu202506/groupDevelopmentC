package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import dto.SearchCondition;
import entity.Employee;

@Mapper
public interface SearchMapper {
	
	@SelectProvider(type = EmployeeSqlProvider.class, method = "buildSearchQuery")
	List<Employee> searchEmployee(SearchCondition condition);
	
}
