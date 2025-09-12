package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.DeleteMapper;

@Service
public class DeleteService {
	@Autowired
	private DeleteMapper mapper;

	public void delete(int id) {
		mapper.delete(id);
	}
}
