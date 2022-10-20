package com.example;


import com.example.api.ApiProvider;
import com.example.api.ApiRatePolicy;
import com.example.api.CultureJsonApiProvider;
import com.example.api.ProjectApiRatePolicy;
import com.example.culture.repository.CultureRepository;
import com.example.culture.repository.JdbcCultureRepository;
import com.example.culture.service.CultureService;
import com.example.culture.service.CultureServiceImpl;
import com.example.domain.CultureVO;
import com.example.user.repository.JdbcUserRepository;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;
import com.example.user.service.UserServiceImpl;

import javax.sql.DataSource;

public class AppConfig {

    ///////////////////////ds

    //dataSource
    public DataSource dataSource() {
        return new HikariDsConfig().config();
    }

    ///////////////////////user

    //userService
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    //userRepository
    public UserRepository userRepository() {
        return new JdbcUserRepository();
    }


    ///////////////////////api

    //apiProvider
    public ApiProvider apiProvider(){
        return new CultureJsonApiProvider
                ("6653645678736b6139317441527257","문화행사");
    }

    //apiRatePolicy
    public ApiRatePolicy apiRatePolicy(){
        return new ProjectApiRatePolicy();
    }

    ///////////////////////culture

    //cultureService
    public CultureService cultureService(){
            return new CultureServiceImpl(apiProvider(),apiRatePolicy(),cultureRepository());
    }

    //cultureRepository
    public CultureRepository<CultureVO> cultureRepository() {
        return new JdbcCultureRepository();
    }

}
