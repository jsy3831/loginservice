package com.photobook.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto implements Serializable {

	private int userId;

	@NotBlank
	private String id;

	@NotBlank
	private String password;

	@NotBlank
	private String name;

	@NotBlank
	@Email
	private String email;

	@NotNull
	@Past
	private LocalDate birth;

	private String profileImageName;

	private String profileImagePath;

	private String profileMessage;

}
