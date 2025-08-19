package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.example.demo.dto.SearchCondition;
import com.example.demo.entity.Employee;

@Mapper
public interface SearchMapper {
	
	@SelectProvider(type = EmployeeSqlProvider.class, method = "buildSearchQuery")
	List<Employee> searchEmployee(@Param("condition") SearchCondition condition);
	
	
	@SelectProvider(type = EmployeeSqlProvider.class, method = "searchCondition")
	Employee selectById(@Param("id") int id);
}
