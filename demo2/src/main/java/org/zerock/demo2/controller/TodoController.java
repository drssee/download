package org.zerock.demo2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.demo2.dto.PageRequestDTO;
import org.zerock.demo2.dto.TodoDTO;
import org.zerock.demo2.service.TodoService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor//like @autowired
public class TodoController {

    private final TodoService todoService;

//    @InitBinder //input type date : string -> date @DateTimeFormat으로 대체
//    public void toDate(WebDataBinder binder) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
//    }

    @RequestMapping("/list")
    //@min @max @positive 안에 @valid 정보 있음
    //get방식으로 오는 page size를 pagerequestDTO가 받음
    public void list(@Valid PageRequestDTO pageRequestDTO,BindingResult bindingResult,Model model){
        log.info("todo list ......");
        log.info("dtdt pageRequestDTO:"+pageRequestDTO);

        if(bindingResult.hasErrors()){
            log.info("todo list error .......");
            pageRequestDTO=PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO",todoService.getList(pageRequestDTO));
        //모델엔 파라미터에 없는 자바빈형식 객체만 전달
    }

//    @RequestMapping(value="/register", method = RequestMethod.GET)
    @GetMapping("/register")
    public void register(){
        log.info("todo register (get) .......");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult br, RedirectAttributes redirectAttributes) throws ParseException {
        log.info("todo register (post) .......");
        if(br.hasErrors()){
            log.info("has errors.........");
            redirectAttributes.addFlashAttribute("errors",br.getAllErrors());
            return "redirect:/todo/register";
        }
        log.info(todoDTO);
        todoService.register(todoDTO);
        return "redirect:/todo/list";
    }

    @GetMapping({"/read","/modify"})
    public void read(@Valid PageRequestDTO pageRequestDTO, Long tno ,Model model) throws IOException {
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info("readmodi:"+todoDTO+",prprprdto"+pageRequestDTO);
        model.addAttribute("dto",todoDTO);
//        model.addAttribute("pageRequestDTO",pageRequestDTO); //자바빈 형식의 클래스면 모델로 등록안해도 파라미터로 들어와있으면 전달됨
    }

    @PostMapping("/remove")
    public String remove(Long tno,PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
        log.info("-------------remove------------");
        log.info("tno:"+tno);
        todoService.remove(tno);
//        redirectAttributes.addAttribute("page",1);
//        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "redirect:/todo/list?"+pageRequestDTO.getLink();
    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO, BindingResult br,PageRequestDTO pageRequestDTO, Model model ,RedirectAttributes redirectAttributes){

        if(br.hasErrors()){//valid 에러가 있으면
            log.info("has errors........");
            redirectAttributes.addFlashAttribute("errors",br.getAllErrors());
            redirectAttributes.addAttribute("tno",todoDTO.getTno());
            return "redirect:/todo/modify";
        }
        log.info("modimodi:"+todoDTO);
        todoService.modify(todoDTO);
//        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
//        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());

        //**todocontroller tno->todocontroller read tno받아서 todoDTO정보 가져오고 m(dto,todoDTO) -> read.jsp 에서 el로 처리 **
//        model.addAttribute("tno",todoDTO.getTno());

        //** modify(post)에서 수정성공후 read(get)으로 갈떄 검색/필터링 조건쿼리가 사라짐
        //**pagerequestDTO가 넘어가긴 하는데(자바빈 형식이라 자동으로 넘어감) 링크로 그값을 쿼리로 넘겨준게 아니라 값이 날라감?

        return "redirect:/todo/read?tno="+todoDTO.getTno();
//        return "redirect:/todo/read";
    }
}
