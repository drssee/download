package org.zerock.demo2.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TimeMapperTests {
    @Autowired(required = false) //mapper 인터페이스 구현객체를 주입받지 못했는데 스프링 자체로는 안되지만 mybatis와 연동을 해서
    //mybatis용 어노테이션이 있는 클래스 인터페이스(자동구현객체까지) 는 자동으로 생성 해줌
    private TimeMapper timeMapper;

    @Autowired(required = false)
    private TimeMapper2 timeMapper2;

    @Test
    public void testGetTime(){
        log.info(timeMapper.getTime());
    }

    @Test
    public void testGetTime2() {
        log.info(timeMapper2.getNow());
    }
}
