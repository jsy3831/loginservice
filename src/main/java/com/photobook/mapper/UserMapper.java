package com.photobook.mapper;

import com.photobook.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    UserDto getUserInfoById(@Param("id") String id);

    void createUser(UserDto userDto);

    boolean checkUserId(@Param("id") String id);

    void deleteUser(String id);

}
