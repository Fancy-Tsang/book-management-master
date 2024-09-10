package com.book.bookmanager.mapper;


import com.book.bookmanager.entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends ICrudMapper<Book> {

}



