package com.example.user.repository;


import com.example.domain.UserVO;

import java.util.Optional;

public interface UserRepository {
    void insert(UserVO userVO);

    Optional<UserVO> getById(String id);
}
