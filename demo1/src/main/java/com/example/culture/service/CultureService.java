package com.example.culture.service;

import com.example.domain.CultureVO;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;

public interface CultureService {

    //api 등록 기능
    void register();
    //전체조회 list(limit skip , size)
    PageResponse<CultureVO> getCultures(PageRequest pageRequest);
    //개별조회
    CultureVO getCulture(Long cno);
    //수정
    //삭제
    //전부삭제
    void removeAll();
}
