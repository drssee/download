package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemonController {
    //요청 올때마다 요청의 생명주기를 가지는 request scope 빈을 만들어서
    //uuid로 로그를 구분함


    private final LogDemoService logDemoService;
    private final MyLogger myLogger; // 호출 시점에 빈을 요청

    @RequestMapping("log-demo")
    @ResponseBody //뷰템플릿->html/jsp 가 아니라 바로 응답바디로
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass()); //@configuration마냥 cglib으로 가짜 프록시 객체를 주입해줌
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
