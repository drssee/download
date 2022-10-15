package com.example.api_test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Culture {
    //서비스명 말고 다 null가능하게

    String svc_nm="";//서비스명 SVCNM

    //지역명+장소명 = 주소
    String area_nm="";//지역명 AREANM
    String place_nm="";//장소명 PLACENM

    String tel_no="";//전화번호 TELNO

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    String pay_ay_nm="";//결제방법 PAYATNM
    String use_tgt_info="";//서비스대상타겟 USETGTINFO

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    String svc_url="";//서비스 바로가기 url SVCURL
    String img_url="";//이미지경로 url IMGURL
    String dtlcont="";//상세내용(html태그) DTLCONT //text타입으로 저장

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    String svc_opn_bgn_dt="";//서비스개시시작일시 SVCOPNBGNDT
    String svc_opn_end_dt="";//서비스개시종료일시 SVCOPNENDDT
    String v_min="";//서비스이용 시작시간 V_MIN
    String v_max="";//서비스이용 종료시간 V_MAX

    String rcpt_bgn_dt="";//접수시작일시 RCPTBGNDT
    String rcpt_end_dt="";//접수종료일시 RCPTENDDT

    String revstd_day_nm="";//취소기간기준정보?? REVSTDDAYNM
    String revstd_day="";//취소기간 기준일까지 REVSTDDAY

}
//        7	SVCNM	서비스명
//        8	PAYATNM	결제방법
//        9	PLACENM	장소명
//        10	USETGTINFO	서비스대상
//        11	SVCURL	바로가기URL
//        12	X	장소X좌표
//        13	Y	장소Y좌표
//        14	SVCOPNBGNDT	서비스개시시작일시
//        15	SVCOPNENDDT	서비스개시종료일시
//        16	RCPTBGNDT	접수시작일시
//        17	RCPTENDDT	접수종료일시
//        18	AREANM	지역명
//        20	IMGURL	이미지경로
//        21	DTLCONT	상세내용
//        22	TELNO	전화번호
//        23	V_MIN	서비스이용 시작시간
//        24	V_MAX	서비스이용 종료시간
//        25	REVSTDDAYNM	취소기간 기준정보
//        26	REVSTDDAY	취소기간 기준일까지