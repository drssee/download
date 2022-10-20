package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
public class QnA_Q_VO {
    private Long qqno;//QnA_Q pk
    private String id;//유저아이디
    private Date regDate;
    private String title;
    private String content;
}
