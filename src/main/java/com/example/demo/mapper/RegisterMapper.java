package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Employee;

@Mapper
public interface RegisterMapper {
	
	@Insert("INSERT INTO employee (name,age,password,startDate,endDate) VALUES (#{name},#{age},#{password},#{startDate},#{endDate})")
	void insert(Employee employee);

}
