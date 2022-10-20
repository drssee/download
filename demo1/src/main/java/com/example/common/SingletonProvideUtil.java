package com.example.common;


import com.example.AppConfig;
import com.example.culture.service.CultureService;
import com.example.user.service.UserService;

public class SingletonProvideUtil {

    public static final SingletonProvideUtil SINGLETON_UTIL
            = new SingletonProvideUtil();

    private final AppConfig appConfig;

    private SingletonProvideUtil() {
        appConfig = new AppConfig();
    }

    ///////////////////user

    //userService
    public UserService userService() {
        return appConfig.userService();
    }

    ///////////////////culture

    //cultureService
    public CultureService cultureService() {
        return appConfig.cultureService();
    }

}
