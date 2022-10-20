package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
public class QnA_A_VO {
    private Long qano;//pk
    private Long qqno;//QnA_Q번호
    private Date regDate;
    private String title;
    private String content;
}
