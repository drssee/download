package org.zerock.demo2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.demo2.dto.PageRequestDTO;
import org.zerock.demo2.dto.PageResponseDTO;
import org.zerock.demo2.dto.TodoDTO;

import java.util.Date;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {
    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister(){
        TodoDTO todoDTO = TodoDTO.builder()
                .title("Test....")
                .dueDate(new Date())
                .writer("user1")
                .build();
        todoService.register(todoDTO);
    }

    @Test
    public void testGetList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO
                .builder()
                .page(101)
                .size(10)
                .build();
        PageResponseDTO<TodoDTO> pageResponseDTO = todoService.getList(pageRequestDTO);//2 10
        log.info(pageResponseDTO);
        log.info("dtdtdt"+pageResponseDTO.isPrev());
        log.info(pageResponseDTO.isPrev()?"[PREV]":"");
        pageResponseDTO.getDtoList().stream().forEach(dto-> log.info(dto));
        log.info(pageResponseDTO.isNext()?"[NEXT]":"");
    }
}
