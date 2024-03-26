package com.study.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SearchBooksReqDto {
    private int page;
    private int count;
    private int bookTypeId;
    private int categoryId;
    private int searchTypeId;
    private String searchText;
}
