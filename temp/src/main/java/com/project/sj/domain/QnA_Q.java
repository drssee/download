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
public class QnA_Q {
	private Long qqno;//QnA_Q pk
	private String id;//유저아이디
	private Date regDate;
	private String title;
	private String content;
}
