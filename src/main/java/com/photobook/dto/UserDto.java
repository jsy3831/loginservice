package com.photobook.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto implements Serializable {

	private int userId;

	@NotBlank(message = "아이디를 입력하세요.")
	private String id;

	@NotBlank(message = "비밀번호를 입력하세요.")
	private String password;

	@NotBlank(message = "이름을 입력하세요.")
	private String name;

	@NotBlank(message = "이메일을 입력하세요.")
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	private String email;

	@NotNull(message = "생년월일을 입력하세요.")
	@Past(message = "날짜가 올바르지 않습니다.")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate birth;

	private String profileImageName;

	private String profileImagePath;

	private String profileMessage;

}
