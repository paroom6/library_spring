package com.study.library.controller;

import com.study.library.Service.BookService;
import com.study.library.aop.annotation.ParamsPrintAspect;
import com.study.library.aop.annotation.ValidAspect;
import com.study.library.dto.RegisterBookReqDto;
import com.study.library.dto.SearchBookRespDto;
import com.study.library.dto.SearchBooksReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminBookController {

    @Autowired
    private BookService bookService;
    @ParamsPrintAspect
    @ValidAspect
    @PostMapping("/book")
    public ResponseEntity<?> saveBook(@Valid @RequestBody RegisterBookReqDto registerBookReqDto, BindingResult bindingResult) {
        bookService.saveBook(registerBookReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @GetMapping("/books")
    public ResponseEntity<?> searchBooks(SearchBooksReqDto searchBooksReqDto) {
        return ResponseEntity.ok(bookService.searchBooks(searchBooksReqDto));
    }

    @GetMapping("/books/count")
    public ResponseEntity<?> getCount(SearchBooksReqDto searchBooksReqDto) {
        return ResponseEntity.ok(bookService.getBookCount(searchBooksReqDto));
    }
}
