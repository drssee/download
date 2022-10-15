package com.example.api_test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class API {
    String key = "6653645678736b6139317441527257";
    String result = "";
    URL url;
    Integer list_total_count;
    List<Culture> cultrueList = new ArrayList<>();

    public static void main(String[] args) {
        API api = new API();
        api.openApi();
    }
    public String openApi(){
        {
            try {
                //전체 api 리스트 카운트
                getTotalCnt();
                //해당 api url을 지정하고
                url = new URL("http://openapi.seoul.go.kr:8088/"+key+"/json/ListPublicReservationCulture/1/"+list_total_count+"/문화행사");
                //url에서 시스템으로 가져오는 스트림을 열어주고 result에 읽어온다
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
                result = br.readLine();
                br.close();
                //얻어온 JSON 정리
                JsonParser jsonParser = new JsonParser();
                //jsonObject 타입으로 파싱
                JsonObject resultByJson = (JsonObject) jsonParser.parse(result);
                //파싱된 result에서 필요한 ListPublicReservationCulture의 value만 추출
                JsonObject ListPublicReservationCulture = (JsonObject) resultByJson.get("ListPublicReservationCulture");

                //ListPublicReservationCulture의 row만 추출
                JsonArray row = (JsonArray) ListPublicReservationCulture.get("row");

                //추출한 row에서 필요한 정보만 culture 객체로 저장
                for(int i = 0 ; i<row.size();i++){
                    JsonObject element = (JsonObject) row.get(i);
                    Culture culture = Culture.builder()
                            .svc_nm(String.valueOf(element.get("SVCNM")))
                            .area_nm(String.valueOf(element.get("AREANM")))
                            .place_nm(String.valueOf(element.get("PLACENM")))
                            .tel_no(String.valueOf(element.get("TELNO")))
                            .pay_ay_nm(String.valueOf(element.get("PAYATNM")))
                            .use_tgt_info(String.valueOf(element.get("USETGTINFO")))
                            .svc_url(String.valueOf(element.get("SVCURL")))
                            .img_url(String.valueOf(element.get("IMGURL")))
                            .dtlcont(String.valueOf(element.get("DTLCONT")))
                            .svc_opn_bgn_dt(String.valueOf(element.get("SVCOPNBGNDT")))
                            .svc_opn_end_dt(String.valueOf(element.get("SVCOPNENDDT")))
                            .v_min(String.valueOf(element.get("V_MIN")))
                            .v_max(String.valueOf(element.get("V_MAX")))
                            .rcpt_bgn_dt(String.valueOf(element.get("RCPTBGNDT")))
                            .rcpt_end_dt(String.valueOf(element.get("RCPTENDDT")))
                            .revstd_day_nm(String.valueOf(element.get("REVSTDDAYNM")))
                            .revstd_day(String.valueOf(element.get("REVSTDDAY")))
                            .build();
                    cultrueList.add(culture);
                }
                System.out.println(cultrueList.get(3));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public void getTotalCnt() {
        try {
            //해당 api url을 지정하고
            url = new URL("http://openapi.seoul.go.kr:8088/"+key+"/json/ListPublicReservationCulture/1/1/문화행사");
            //url에서 시스템으로 가져오는 스트림을 열어주고 result에 읽어온다
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
            result = br.readLine();
            br.close();
            //얻어온 JSON 정리
            JsonParser jsonParser = new JsonParser();
            //jsonObject 타입으로 파싱
            JsonObject resultByJson = (JsonObject) jsonParser.parse(result);
            //파싱된 result에서 필요한 ListPublicReservationCulture의 value만 추출
            JsonObject ListPublicReservationCulture = (JsonObject) resultByJson.get("ListPublicReservationCulture");
            //전체 리스트 숫자
            list_total_count = ListPublicReservationCulture.get("list_total_count").getAsInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
