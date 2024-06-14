package com.lotto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lotto.dto.UserDTO;
import com.lotto.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper umapper;
	
	public UserDTO userLogin(String id, String pw) {
		return umapper.getUser(id, pw);
	}
}
