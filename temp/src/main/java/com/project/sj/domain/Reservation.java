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
public class Reservation {
	private Long rno;//pk
	private String id;//유저아이디
	private Long cno;//행사번호
	private Date regDate;
	private Date user_res_schedule;//예약한 날짜
}
