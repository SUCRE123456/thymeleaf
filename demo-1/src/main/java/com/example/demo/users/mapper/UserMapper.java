package com.example.demo.users.mapper;

import java.util.List;
import java.util.Map;

import com.example.demo.users.UserVO;

public interface UserMapper {
	public List<UserVO> find();
	public List<Map> findByName(UserVO vo);
	public UserVO findById(UserVO vo);
	public void persist(UserVO vo);
	public void merge(UserVO vo);
	public void remove(UserVO vo);
} 