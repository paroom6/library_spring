package com.study.library.Repository;

import com.study.library.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    public int saveBook(Book book);
    public List<Book> findBooks(
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("bookTypeId") int bookTypeId,
            @Param("categoryId") int categoryId,
            @Param("searchTypeId") int searchTypeId,
            @Param("searchText") String searchText);
}
