package com.photobook.mapper;

import com.photobook.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    UserDto getUserInfoByIdAndPassword(@Param("id") String id, @Param("password") String password);

    UserDto getUserInfoById(@Param("id") String id);

}
