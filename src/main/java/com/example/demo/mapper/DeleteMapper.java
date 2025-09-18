package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Employee;

@Mapper
public interface DeleteMapper {
	@Delete("DELETE FROM employee WHERE id = #{id}")
	void delete(int id);

	@Select("SELECT * FROM employee WHERE id = #{id}")
	Employee findById(int id);
}
