package com.project.sj.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String id; // pk
	private String pwd;
	private String name;
	private Date regDate;
	private String email;
	private String phone;
	private Integer age;
	private Integer gender;
}
