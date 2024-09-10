package com.book.bookmanager.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.bookmanager.dto.CommentDto;
import com.book.bookmanager.entity.Comment;
import com.book.bookmanager.mapper.UserMapper;
import com.book.bookmanager.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Page<CommentDto> getList(Page<Comment> page) {
        Page<Comment> commentPage = this.page(page);
        Page<CommentDto> dtoPage = new Page<>();
        BeanUtils.copyProperties(commentPage, dtoPage);
        List<CommentDto> records = new ArrayList<>();
        commentPage.getRecords().forEach(comment -> {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            commentDto.setUsername(userMapper.selectById(comment.getUserId()).getUsername());
            records.add(commentDto);
        });
        dtoPage.setRecords(records);
        return dtoPage;
    }
}
