package com.example.mapper;

import java.util.List;

import com.example.model.Admin;
import com.example.model.Book;
import com.example.model.Category;
import com.example.model.Messages;
import com.example.model.Users;

public interface AdminMapper {
List<Book> findAllBook();
List<Category> findAllType();
List<Category> findByType(String typename);
Category findOneType(int id);
int updateType(Category categoyr);
int deleteType(int typeId);
int updateBook(Book book);
int inserteBook(Book book);
int inserteBookType(Category categoyr);
int deleteBook(int bookId);
Book findOneBook(int bookId);

}
