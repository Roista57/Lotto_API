package com.lotto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lotto.dto.UserDTO;
import com.lotto.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin("*")
@Tag(name = "유저 정보", description = "유저와 관련된 정보를 다루는 컨트롤러")
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService uservice;
	
	@PostMapping("/login")
	public UserDTO login(@RequestBody UserDTO getUser) {
		System.out.println(getUser.getId()+", "+getUser.getPw());
		UserDTO user = uservice.userLogin(getUser.getId(), getUser.getPw());
		System.out.println(user.getId()+", "+user.getPw());
		return user;
	}
}
