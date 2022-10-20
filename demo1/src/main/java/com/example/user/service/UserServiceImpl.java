package com.example.user.service;


import com.example.domain.UserVO;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


//    public UserServiceImpl(UserRepository userRepository) {
//        super();
//        this.userRepository = userRepository;
//    }

    @Override
    public void join(UserVO userVO) {
        userRepository.insert(userVO);
    }

    @Override
    public boolean login(String id, String pwd) {
        //입력된 id로 유저 객체를 받아옴
        //유저 정보를 가져오는데 실패하면 예외를 던짐
        UserVO userVO = getUSer(id).orElseThrow();
        if (userVO.getId() == "" || userVO.getId() == null ||
                userVO.getPwd() =="" || userVO.getPwd() == null) {
            return false;
        }
        //id==pwd 일치하는지
		return pwd.equals(userVO.getPwd());
	}

    @Override
    public Optional<UserVO> getUSer(String id) {
        return userRepository.getById(id);
    }
}
