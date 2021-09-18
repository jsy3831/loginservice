package com.photobook.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PasswordEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("패스워드 암호화 테스트")
    void passwordEncode() {
        //given
        String rawPwd = "1234";

        //when
        String encodedPwd = passwordEncoder.encode(rawPwd);
        System.out.println(encodedPwd);

        //then
        assertAll(
                () -> assertNotEquals(rawPwd, encodedPwd),
                () -> assertTrue(passwordEncoder.matches(rawPwd, encodedPwd))
        );
    }

}
