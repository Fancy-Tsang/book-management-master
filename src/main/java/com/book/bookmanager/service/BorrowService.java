package com.book.bookmanager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.bookmanager.dto.BorrowDto;
import com.book.bookmanager.entity.Book;
import com.book.bookmanager.entity.Borrow;

public interface BorrowService extends BaseService<Borrow> {

    Book borrow(Borrow borrow);

    Book returnBook(Borrow borrow);

    Page<BorrowDto> getListByUser(Page<Borrow> page, String userId, int type);
}
