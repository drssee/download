//package org.zerock.demo2.controller.formatter;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component //                                 안된다 ... 왜안되지? 컨버터와 포매터 차이는?? 검색해보자
//public class StringToDateConverter
//        implements Converter<String, Date> {
//
//    @Override
//    public Date convert(String source) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyyy-MM-dd");
//        try {
//            return format.parse(source);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}