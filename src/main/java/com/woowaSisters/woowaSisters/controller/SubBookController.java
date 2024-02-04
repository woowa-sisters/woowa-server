package com.woowaSisters.woowaSisters.controller;

import com.woowaSisters.woowaSisters.domain.subBook.SubBook;
import com.woowaSisters.woowaSisters.dto.SubBookListDTO;
import com.woowaSisters.woowaSisters.dto.SubBookSaveDTO;
import com.woowaSisters.woowaSisters.service.SubBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4000")

@RestController
@Lazy
@RequestMapping("/subbook")
public class SubBookController {

    @Resource
    private final SubBookService subBookService;

    @Autowired
    public SubBookController(SubBookService subBookService) {
        this.subBookService = subBookService;
    }

    @GetMapping("/test")
    public String test(){
        return "test!";
    }

    @GetMapping("/{userId}/list")
    public List<SubBookListDTO> getSubBookListByUserId(@PathVariable("userId") Long userId){
        return subBookService.findByUserId(userId);
    }

    @PostMapping("/save")
    public SubBook subscribeBook(@RequestBody SubBookSaveDTO subBook){
        return subBookService.saveSubBook(subBook);

    }

    /*@PostMapping("/save")
    public */

}
