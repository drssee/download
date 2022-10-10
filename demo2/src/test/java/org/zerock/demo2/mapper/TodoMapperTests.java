package org.zerock.demo2.mapper;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.demo2.domain.TodoVO;
import org.zerock.demo2.dto.PageRequestDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {
    @Autowired(required = false)
    private TodoMapper todoMapper;//mybatis라 주입이 되나?

    @Test
    public void testGetTime(){
        log.info(todoMapper.getTime());
    }

    @Test
    public void insertTest() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        TodoVO todoVO = TodoVO.builder()
                .title("스프링 테스트")
                .dueDate(df.parse("2022-10-10"))
                .writer("user00")
                .build();
        todoMapper.insert(todoVO);
    }
    @Test
    public void selectAllTest() throws Exception {
        List<TodoVO> voList = todoMapper.selectAll();
        voList.forEach(vo->log.info(vo));
    }

    @Test
    public void selectOneTest() throws Exception{
        TodoVO vo = todoMapper.selectOne(Long.valueOf(6));
        log.info(vo);
        Assert.isTrue(vo!=null);
    }

    @Test
    public void deleteTest() throws Exception{
        todoMapper.delete(Long.valueOf(6));
    }

    @Test
    public void selectListTest() throws Exception{
        PageRequestDTO pageRequestDTO = PageRequestDTO
                .builder()
                .page(2)
                .size(10)
                .build();

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        voList.forEach(vo-> log.info(vo));
    }

    @Test
    public void testSelectSearch() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = df.parse("2021-12-01");
        Date date2 = df.parse("2022-12-31");
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[]{"t","w"})
                .keyword("스프링")
                .finished(false)
                .from(date1)
                .to(date2)
                .build();
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        voList.forEach(vo->log.info("dfdfdf:"+vo));

        log.info("dfdfdfcount:"+todoMapper.getCount(pageRequestDTO));
    }

}
