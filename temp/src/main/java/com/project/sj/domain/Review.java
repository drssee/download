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
public class Review {
	private Long re_no;//pk
	private String id;//유저아이디
	private Long cno;//행사번호
	private String content;
	private Date regDate;
}
