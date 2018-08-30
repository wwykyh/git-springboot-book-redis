package com.example.mapper;

import java.util.List;

import com.example.model.Book;

public interface BookMapper {
List<Book> findspecilBook();
Book findOneBook(int id);

List<Book> findNewAllBook();
List<Book> selectByTypeId(int typeId,int bookId);
List<Book> selectByDiscount();
List<Book> selectAllByDiscount();
List<Book> selectByName(String bookname);
void updateBook(int bookid,int number);
}
