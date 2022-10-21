package org.zerock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board")
@Slf4j
public class BoardController {

    @GetMapping("list")
    public String boardList() {
        log.info("/board/list ... get");
        return "";
    }
}
