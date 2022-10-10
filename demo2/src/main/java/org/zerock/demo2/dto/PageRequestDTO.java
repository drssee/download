package org.zerock.demo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    @Min(value=1)
    @Positive
    private int page = 1;//현재 페이지의 번호

    @Builder.Default
    @Min(value=10)
    @Max(value=100)
    @Positive
    private int size = 10;//한 페이지당 보여주는 데이터의 수

    private String link;

    private String[] types;
    private String keyword;
    private boolean finished;
    @DateTimeFormat(pattern = "yyyy-MM-dd") // form으로 입력값 받아올떄 까먹지 말자
    @Builder.Default
    private Date from=getDate(2022,01,01);

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Builder.Default
    private Date to=getDate(2022,12,31);

    public String getFrom(){//getter 커스텀
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//저장할때만이 아니라 읽어올때도 패턴을 지정
        return df.format(this.from);
    }
    public String getTo(){//getter 커스텀
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//저장할때만이 아니라 읽어올때도 패턴을 지정
        return df.format(this.to);
    }

    //cal->날짜입력->date 변환
    public static Date getDate(int year, int month, int date){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, date);
        return new Date(cal.getTimeInMillis());
    }

    public int getSkip(){//offset 구하는 메서드
        return (page-1)*size;
    }

//    public String getLink(){//getter방식 이름 설정
//        if(link==null){
//            StringBuilder sb = new StringBuilder();
//            sb.append("page="+this.page);
//            sb.append("&size="+this.size);
//            link=sb.toString();
//        }
//        return link;
//    }

    public String getLink(){
        StringBuilder builder = new StringBuilder();
        builder.append("page="+this.page);
        builder.append("&size="+this.size);

        if(finished){
            builder.append("&finished=on");
        }

        if(types!=null && types.length >0) {
            for(int i=0;i<types.length;i++){
                builder.append("&types=" + types[i]);
            }
        }

        if(keyword != null){
            try {
                builder.append("&keyword="+ URLEncoder.encode(keyword,"UTF-8")); //한글일수도 있으니 인코딩?
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if(from != null){
            builder.append("&from"+getFrom());
        }
        if(to!=null){
            builder.append("&to="+getTo());
        }

        return builder.toString();
    }

    public boolean checkType(String type){
        if(types==null||types.length==0){
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }

    public String getLink2(Integer page){
        StringBuilder builder = new StringBuilder();
        builder.append("page="+page);
        builder.append("&size="+this.size);

        if(finished){
            builder.append("&finished=on");
        }

        if(types!=null && types.length >0) {
            for(int i=0;i<types.length;i++){
                builder.append("&types=" + types[i]);
            }
        }

        if(keyword != null){
            try {
                builder.append("&keyword="+ URLEncoder.encode(keyword,"UTF-8")); //한글일수도 있으니 인코딩?
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if(from != null){
            builder.append("&from"+getFrom());
        }
        if(to!=null){
            builder.append("&to="+getTo());
        }

        return builder.toString();
    }
}
