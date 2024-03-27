package com.study.library.Service;

import com.study.library.Repository.BookMapper;
import com.study.library.dto.RegisterBookReqDto;
import com.study.library.dto.SearchBookCountRespDto;
import com.study.library.dto.SearchBookRespDto;
import com.study.library.dto.SearchBooksReqDto;
import com.study.library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Transactional(rollbackFor = Exception.class)
    public void saveBook(RegisterBookReqDto registerBookReqDto) {
        bookMapper.saveBook(registerBookReqDto.toEntity());
    }
    public List<SearchBookRespDto> searchBooks (SearchBooksReqDto searchBooksReqDto) {
        int startIndex = (searchBooksReqDto.getPage() - 1) * searchBooksReqDto.getCount();
        List<Book> books = bookMapper.findBooks(
                startIndex,
                searchBooksReqDto.getCount(),
                searchBooksReqDto.getBookTypeId(),
                searchBooksReqDto.getCategoryId(),
                searchBooksReqDto.getSearchTypeId(),
                searchBooksReqDto.getSearchText()
        );
        return books.stream().map(Book::toSearchBookRespDto).collect(Collectors.toList());
    }
    public SearchBookCountRespDto getBookCount(SearchBooksReqDto searchBooksReqDto) {
        int bookCount = bookMapper.getBookCount(
                searchBooksReqDto.getBookTypeId(),
                searchBooksReqDto.getCategoryId(),
                searchBooksReqDto.getSearchTypeId(),
                searchBooksReqDto.getSearchText()
        );
        int MaxPageNumber = (int)Math.ceil(((double) bookCount) / searchBooksReqDto.getCount());
        return SearchBookCountRespDto.builder()
                .totalCount(bookCount)
                .maxPageNumber(MaxPageNumber)
                .build();

    }
}
