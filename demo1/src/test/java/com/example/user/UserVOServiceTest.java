package com.example.user;

import com.example.domain.UserVO;
import com.example.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.example.common.SingletonProvideUtil.SINGLETON_UTIL;

public class UserVOServiceTest {

    UserService userService = SINGLETON_UTIL.userService();

    @Test
    @DisplayName("유저 조회")
    public void selectTest(){
        Optional<UserVO> result = userService.getUSer("user1");
        UserVO userVO = result.get();
        System.out.println(userVO);
        Assertions.assertNotNull(userVO);
    }

    @Test
    @DisplayName("로그인 성공")
    public void loginTest1(){
        boolean login = userService.login("user1", "user1");
        Assertions.assertTrue(login);
    }

    @Test
    @DisplayName("로그인 실패")
    public void loginTest2(){
        Assertions.assertThrows(Exception.class,
                ()->userService.login("user2", "user2"));
    }
}
