package org.zerock.demo2.service;

import org.zerock.demo2.dto.PageRequestDTO;
import org.zerock.demo2.dto.PageResponseDTO;
import org.zerock.demo2.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);
//    List<TodoDTO> getAll();
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);
    TodoDTO getOne(Long tno);
    void remove(Long tno);
    void modify(TodoDTO todoDTO);
}
