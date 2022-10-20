package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
public class ReviewVO {
    private Long re_no;//pk
    private String id;//유저아이디
    private Long cno;//행사번호
    private String content;
    private Date regDate;
}
