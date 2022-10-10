package org.zerock.demo2.dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    private Long tno;
    @NotEmpty //notnull+not""
    private String title;
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;//만료시간이라 미래로
    private boolean finished;
    @NotEmpty
    private String writer;

    public String getDueDate(){//getter 커스텀
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//저장할때만이 아니라 읽어올때도 패턴을 지정
        return df.format(this.dueDate);
    }
}
