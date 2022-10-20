package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
public class ReservationVO {
    private Long rno;//pk
    private String id;//유저아이디
    private Long cno;//행사번호
    private Integer res_price;
    private Date user_res_schedule;//예약희망 날짜
    private Date regDate;//예약한 날짜
}
