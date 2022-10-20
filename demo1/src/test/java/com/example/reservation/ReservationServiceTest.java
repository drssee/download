package com.example.reservation;

import com.example.common.SingletonProvideUtil;
import com.example.culture.service.CultureService;
import com.example.reservation.service.ReservationService;
import com.example.reservation.service.ReservationServiceImpl;
import org.junit.jupiter.api.Test;

public class ReservationServiceTest {

    ReservationService reservationService = new ReservationServiceImpl();
    CultureService cultureService = SingletonProvideUtil.SINGLETON_UTIL.cultureService();

    @Test
    void test(){
//        reservationService.reservation(cultureService.getCulture(1L),"user1","2022-10-29");

    }
}
