package com.example.culture;

import com.example.culture.service.CultureService;
import com.example.domain.CultureVO;
import com.example.domain.PageRequest;
import com.example.domain.PageResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.common.SingletonProvideUtil.SINGLETON_UTIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CultureVOServiceTest {
    CultureService cultureService = SINGLETON_UTIL.cultureService();

    @Test
    @DisplayName("culture list 조회1")
    void getCulturesTest1(){
        PageRequest pageRequest = PageRequest.builder()
                .size(5)
                .build();
        PageResponse<CultureVO> pageResponse = cultureService.getCultures(pageRequest);
        List<CultureVO> cultureVOS = pageResponse.getPageList();
        System.out.println("cultures = " + cultureVOS);
        assertEquals(5, cultureVOS.size());
    }
    @Test
    @DisplayName("culture list 조회2")
    void getCulturesTest2(){
        CultureVO cultureVO = cultureService.getCulture(1L);
        System.out.println("culture = " + cultureVO);
        assertNotNull(cultureVO);
    }

    @Test
    @DisplayName("culture list 페이징 테스트")
    void getCulturesTest3(){
        PageRequest pageRequest = PageRequest.builder()
                .size(5)
                .build();
        PageResponse<CultureVO> pageResponse = cultureService.getCultures(pageRequest);
        //페이징 테스트
        if(pageResponse.isShowPrev()){
            System.out.println("[PREV]");
        }
        System.out.println(pageResponse.getPageList().size());
        if(pageResponse.isShowNext()){
            System.out.println("[NEXT]");
        }
        System.out.println("pageResponse.getTotal() = " + pageResponse.getTotal());
        System.out.println("pageResponse.getLast() = " + pageResponse.getLast());
        System.out.println("pageResponse.getEnd() = " + pageResponse.getEnd());
    }
}
