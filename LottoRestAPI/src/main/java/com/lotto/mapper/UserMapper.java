package com.lotto.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lotto.dto.UserDTO;

@Mapper
public interface UserMapper {
	int insertUser();
	// Mybatis는 파라미터를 하나만 전달해줄 수 있다. -> Param을 사용하여 Map(key, value)으로  전달.
	UserDTO getUser(@Param("id")String id, @Param("pw")String pw); 
}
