package com.example.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
public class PageRequest {
    //limit skip , 6
    //festivalList로 상세정보 제외한 나머지 정보 보내고
    //festivalDetail로 상세정보 보내는건 다른 컨트롤러에서
    @Builder.Default
    @Positive
    @Size(min=1)
    private int page=1;
    @Builder.Default
    @Positive
    @Size(min=12,max=120)
    private int size=12;
    public int getSkip(){
        return (page-1)*size;
    }
}
