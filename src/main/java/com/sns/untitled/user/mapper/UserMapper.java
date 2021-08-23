package com.sns.untitled.user.mapper;

import com.sns.untitled.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    UserDto findUserByIdAndPassword(@Param("id") String id, @Param("password") String password);

    UserDto findUserById(@Param("id") String id);

}
