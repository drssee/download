package org.zerock.demo2.domain;

import lombok.*;

import java.util.Date;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoVO {
    private long tno;
    private String title;
    private Date dueDate;
    private String writer;
    private boolean finished;
}
