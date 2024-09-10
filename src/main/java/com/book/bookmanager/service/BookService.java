package com.book.bookmanager.service;

import com.book.bookmanager.entity.Book;
import com.book.bookmanager.util.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService extends BaseService<Book> {

    int deleteById(String id);

    List<Book> getOne(String id);

    List<Book> getByTagId(Integer tagId, boolean more);

    List<Book> hot();

    List<Book> search(String search);

    Result upload(MultipartFile file);
}
