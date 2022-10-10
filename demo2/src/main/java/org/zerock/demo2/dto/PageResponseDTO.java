package org.zerock.demo2.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter//외부조작 불가능하게 막아둠
@ToString
public class PageResponseDTO<E> {
    private int page;//현재 페이지
    private int size;//페이지당 게시물수
    private int total;//총 게시물수

    private int start; //시작페이지번호
    private int end; //끝페이지번호

    private int last; //총 페이지의 끝

    private boolean prev;//이전페이지 존재여부
    private boolean next;//다음페이지 존재여부

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO,List<E> dtoList,int total) {
        this.page=pageRequestDTO.getPage();
        this.size=pageRequestDTO.getSize();

        this.total=total;
        this.dtoList=dtoList;

        this.last=(int)(Math.ceil(total/(double)size));//게시판의 마지막 끝 페이지

        this.start=((page-1)/10*10)+1;
        this.end=(start+9)<last?(start+9):last;

        this.prev = start!=1;
        this.next = end!=last;
    }
}
