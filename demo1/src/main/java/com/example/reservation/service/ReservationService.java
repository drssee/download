package com.example.reservation.service;

import com.example.domain.CultureVO;

import java.text.ParseException;

public interface ReservationService {
    void reservation(CultureVO culture, String id, String res_dt)
            throws IllegalStateException, ParseException;
}
