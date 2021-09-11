package com.photobook.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class UserDto {

    private int userId;

    private String id;

    private String password;

    private String name;

    private String email;

    private LocalDate birth;

    private String profileImageName;

    private String profileImagePath;

    private String profileMessage;

}
