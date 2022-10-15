package com.project.sj.service;

import com.project.sj.SingletonService;
import com.project.sj.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class UserServiceTest {
    UserService userService = SingletonService.getInstance().userService();

    @Test
    public void selectTest(){
        Optional<User> result = userService.getUSer("user1");
        User user = result.get();
        Assertions.assertNotNull(user);
    }

    @Test
    public void loginTest1(){
        boolean login = userService.login("user1", "user1");
        Assertions.assertTrue(login);
    }

    @Test
    public void loginTest2(){
        boolean login = userService.login("user2", "user2");
        Assertions.assertTrue(login);
    }
}
