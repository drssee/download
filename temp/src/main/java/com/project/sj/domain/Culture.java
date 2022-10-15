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
public class Culture {
	private Long cno;//pk
	private String name;
	private String location;
	private String phone;
	private Date regDate;
	private String target;//행사 대상자 연령그룹
	private String select_way;//대상자 선별방법
	private String res_way;//예약 방법
	private Integer capacity;//인원제한
	private Integer price;
	private Integer limit;//한 아이디당 예약가능한 제한횟수
	private Date c_duration_from;//행사기간
	private Date c_duration_to;//행사기간
	private Date r_duration_from;//예약기간
	private Date r_duration_to;//예약기간
	private Date rc_duration_from;//예약취기간
	private Date rc_duration_to;//예약취소기간
}
