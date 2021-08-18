package com.sns.untitled.user.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {

    private int userId;

    private String id;

    private String password;

    private String name;

    private String email;

    private Date birth;

    private String profileImageName;
    private String profileImagePath;
    private String profileMessage;

}
