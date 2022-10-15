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
public class QnA_A {
	private Long qano;//pk
	private Long qqno;//QnA_Q번호
	private Date regDate;
	private String title;
	private String content;
}
