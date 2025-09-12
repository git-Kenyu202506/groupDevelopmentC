package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeleteMapper {
	@Delete("DELETE FROM employee WHERE id = #{id}")
	void delete (int id);
}
