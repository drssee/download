package com.example.reservation.service;

import com.example.domain.CultureVO;
import com.example.domain.ReservationVO;
import com.example.reservation.repository.JdbcReservationRepository;
import com.example.reservation.repository.ReservationRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationServiceImpl implements ReservationService{
    //임시
    ReservationRepository reservationRepository = new JdbcReservationRepository();

    @Override
    public void reservation(CultureVO culture, String id, String res_dt) throws IllegalStateException {
        try {
            ////////////////////////////////////////////////넘어온 예약정보 검증
            //1.예약 신청 가능날짜 (cno로 예매가능날짜 가져옴)
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date from = df.parse(culture.getRcpt_bgn_dt());
            Date to = df.parse(culture.getRcpt_end_dt());
            Date res = df.parse(res_dt);

            //2.잘못된 예약 날짜 선택
            if(res.before(from)||res.after(to)){
                throw new IllegalStateException("잘못된 날짜 예약");
            }
            //3.같은예약(내 예약의 cno가 같거나 svc_nm이 같으면) 신청시 신청불가(미구현)

            //4.예약 검증(해당 cno예약 수>culture.capacity 예약불가)(미구현)

            //4 유료결제(미구현)
            // reservation_basic의 res_price에 ++)
            //////////////////////////////////////////////////검증완료

            // 검증을 통과하면 reservation 작성후 insert
            //  예약에 성공하면 다시 상세페이지로(msg)
            ReservationVO reservation = ReservationVO.builder()
                    .id(id)
                    .cno(culture.getCno())
                    .res_price(culture.getPrice())
                    .user_res_schedule(res)
                    .build();
            reservationRepository.insert(reservation);

            //컨트롤러로 검증 예외를 던짐(이게맞나)
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
