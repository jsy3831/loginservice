package com.photobook.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.photobook.dto.UserDto;

@Mapper
public interface UserMapper {

	UserDto getUserInfoById(@Param("id") String id);

	void createUser(UserDto userDto);

	boolean checkUserId(@Param("id") String id);

	void deleteUser(String id);

}
