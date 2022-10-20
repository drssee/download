package com.example.culture.repository;

import com.example.domain.CultureVO;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;

import java.util.Optional;

public interface CultureRepository<T> {
    void insert(T t);
    PageResponse<CultureVO> selectAll(PageRequest pageRequest);
    Optional<CultureVO> selectOne(Long cno);
    int selectCount();
    void deleteAll();
}
