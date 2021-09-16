package com.photobook.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserDto implements Serializable {

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
