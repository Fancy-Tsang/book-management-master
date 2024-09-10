package com.book.bookmanager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.bookmanager.dto.CommentDto;
import com.book.bookmanager.entity.Comment;

public interface CommentService extends BaseService<Comment> {

    Page<CommentDto> getList(Page<Comment> page);
}
