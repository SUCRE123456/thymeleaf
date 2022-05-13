package com.example.demo.users.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.users.UserVO;
import com.example.demo.users.mapper.UserMapper;

@CrossOrigin(origins = {"*"}, maxAge = 3600) //해당 포트로만 접속허용.
@Controller 
public class UserController {

	@Autowired UserMapper mapper;
	
	@GetMapping("/users")
	public String userSelect(Model model) {
		
		model.addAttribute("users", mapper.find());
		
		return "users";
	}

	@GetMapping("/users/{id}")
	public UserVO userSelectList(@PathVariable String id, UserVO vo) {
		vo.setId(id);
		return mapper.findById(vo);
	}

	@PostMapping("/users") //파라미터 queryString, jsonString(@requestbody 입력시 jsontype 받음.)
	public UserVO userInsert(@RequestBody UserVO vo) {
		 mapper.persist(vo);
		 return vo;
	}

	@PutMapping("/users")
	public UserVO userUpdate(@RequestBody UserVO vo) {
		 mapper.merge(vo);
		 return vo;
	}	
	@DeleteMapping("/users/{id}")
	public UserVO userDelete(@PathVariable String id, UserVO vo) {
		//System.out.println(vo.getId());
		//System.out.println(vo.getName());

		 vo.setId(id);
		 System.out.println(vo.getName()); //null
		 mapper.remove(vo);
		 return vo;
	}		
}