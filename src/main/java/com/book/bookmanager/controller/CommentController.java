package com.book.bookmanager.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.bookmanager.entity.Comment;
import com.book.bookmanager.service.CommentService;
import com.book.bookmanager.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
/*
在下面的示例中，@RestController注解表示这是一个RESTful控制器，
@RequestMapping注解表示这个控制器处理的所有HTTP请求都是以“/comment”为前缀的。
@GetMapping注解指定了一个处理HTTP GET请求的方法，该方法的URL路径是“/getList”，并且返回Result.success(commentService.getList(page))。
*/
@CrossOrigin
@RestController
@RequestMapping("comment")
public class CommentController extends BaseController<CommentService, Comment> {


    @Resource
    private CommentService commentService;

    @GetMapping("getList")
    public Result getList(Page<Comment> page) {
        return Result.success(commentService.getList(page));
    }


    @Override
    public void saveBefore(Comment entity) {
        entity.setCreateTime(LocalDateTime.now());
    }
}

