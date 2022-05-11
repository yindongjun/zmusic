package com.example.zmusic.repository;

import com.example.zmusic.entity.User;
import com.example.zmusic.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findByUsername() {
        User userToSave = User.builder()
                .username("jzheng")
                .nickname("jzheng")
                .gender(Gender.MALE)
                .password("123456")
                .lastLoginIp("127.0.0.1")
                .lastLoginTime(LocalDateTime.now())
                .locked(false)
                .enabled(true)
                .build();

        userRepository.save(userToSave);

        User foundUser = userRepository.getByUsername("jzheng");
        System.out.println(foundUser);
    }
}