package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
public class UserVO {
    //유효성검사 @valid 추가해야함
    private String id; // pk
    private String pwd;
    private String name;
    private Date regDate;
    private String email;
    private String phone;
    private Integer age;
    private Integer gender;
}
