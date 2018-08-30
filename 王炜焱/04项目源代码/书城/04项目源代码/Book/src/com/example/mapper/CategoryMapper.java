package com.example.mapper;

import java.util.List;

import com.example.model.Book;

public interface CategoryMapper {
	List<Book> findAllBook(int page);
	List<Book> findTypeBook(String type);
}
