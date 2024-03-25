package com.study.library.Service;

import com.study.library.Repository.OptionsMapper;
import com.study.library.entity.BookType;
import com.study.library.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionsService {

    @Autowired
    private OptionsMapper optionsMapper;

    public List<BookType> getAllBookTypes() {
        return optionsMapper.getAllBookTypes();
    }
    public List<Category> getAllCategories() {
        return optionsMapper.getAllCategories();
    }
}
