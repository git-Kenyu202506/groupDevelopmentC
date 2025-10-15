package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Employee;

@Mapper
public interface RegisterMapper {

	@Insert("INSERT INTO employee (id, name, password,age,startDate,endDate) VALUES (#{id}, #{name}, #{password},#{age},#{startDate},#{endDate})")
	void insert(Employee employee);

}
